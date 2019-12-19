package com.css.cloud.quartz.zhjc.jsq.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.css.cloud.quartz.zhjc.jsq.common.DictionaryUtils;
import com.css.cloud.quartz.zhjc.jsq.dao.*;
import com.css.cloud.quartz.zhjc.jsq.entity.TBaseStatistics;
import com.css.cloud.quartz.zhjc.jsq.entity.TCaseInfoStatistics;
import com.css.cloud.quartz.zhjc.jsq.entity.THandleOfEveryday;
import com.css.cloud.quartz.zhjc.jsq.service.CaseStatisticService;
import com.css.cloud.quartz.zhjc.jsq.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CaseStatisticServiceImpl implements CaseStatisticService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private InfoApprovalCaseMapper infoCaseMapper;

    @Autowired
    private TBaseStatisticsMapper tBaseStatisticsMapper;
    @Autowired
    private TCaseInfoStatisticsMapper tCaseInfoStatisticsMapper;
    @Autowired
    private THandleOfEverydayMapper tHandleOfEverydayMapper;
    @Autowired
    private TMatterStatisticsMapper tMatterStatisticsMapper;

    @Override
    @Async("asyncServiceExecutor")
    public void  saveRedisData(String key,Object value){
        ValueOperations valueOperations=redisTemplate.opsForValue();
        valueOperations.set(key,value);

    }

    //保留小数n位
    public double  decimalRound(double num,int n){
        BigDecimal b = new BigDecimal(num);
        double res = b.setScale(n,BigDecimal.ROUND_HALF_UP).doubleValue();
        return  res;
    }

    @Override
    public void putDbBaseData() {
        Date todate=new Date();
        String today= DateUtil.getDateStr(todate).replace("-","");


        //  分别获取五个库的新增数据量
        Map<String,Object> allData=infoCaseMapper.getNumByDate(today);

        //Redis
        String key="baseStatistics"+today;
        List<Map> mapList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        String chineseDate=df.format(todate);
        //   封成前台需要的数据
        String[] dbNames = new String[]{"事项库","法人库","人口库","电子证照库","电子档案库"};
        for(int i=0;i<5;i++) {
            Map<String,Object> temp=new HashMap();
            temp.put("name",dbNames[i]);
            temp.put("date",chineseDate);
            temp.put("sum",allData.get("SUM"+(i+1)));
            temp.put("daily",allData.get("DAILY"+(i+1)));
            temp.put("code",i);
            mapList.add(temp);
        }
        String jsonStr=JSONObject.toJSONString(mapList);

        this.saveRedisData(key,jsonStr);

        // 存到统计表
        List<TBaseStatistics> baseStatisticsList = new ArrayList<>();
        //统计库
        for(int j=0;j<5;j++) {
            TBaseStatistics tBaseCase = new TBaseStatistics();
            tBaseCase.setId(UUID.randomUUID().toString().replace("-", ""));
            switch (j) {
                case 0:tBaseCase.setDataType(DictionaryUtils.DB_CASEINFO);
                case 1:tBaseCase.setDataType(DictionaryUtils.DB_COMPANYINFO);
                case 2:tBaseCase.setDataType(DictionaryUtils.DB_PERSONINFO);
                case 3:tBaseCase.setDataType(DictionaryUtils.DB_CERTINFO);
                case 4:tBaseCase.setDataType(DictionaryUtils.DB_ARCHIVEINFO);
            }
            tBaseCase.setTotalCont(((BigDecimal) allData.get("SUM"+(j+1))).longValue());
            tBaseCase.setUpdateCount(((BigDecimal) allData.get("DAILY"+(j+1))).longValue());
            tBaseCase.setUpdatetime(todate);
            baseStatisticsList.add(tBaseCase);
        }


        tBaseStatisticsMapper.insertAll(baseStatisticsList);

    }

    @Override
    public void putHandleDelayData() {



    }

    @Override
    public void putEverydayCondition() {
        Date todate=new Date();
        String today=DateUtil.getDateStr(todate).replace("-","");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(todate);
        // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
        calendar.add(Calendar.DATE, -7);
        Date oldDate = calendar.getTime();
        String oldDay = DateUtil.getDateStr(oldDate).replace("-","");
        //每日工作量
        Map<String,Object>  dailyMap=infoCaseMapper.getDailyIncrease(today);
        //平均工作量
        Map<String,Object> avgMap = infoCaseMapper.getAvgWeekly(today,oldDay);
        dailyMap.putAll(avgMap);

        //存入统计表
        THandleOfEveryday tDailyEntity = new THandleOfEveryday();
        tDailyEntity.setId(UUID.randomUUID().toString().replace("-",""));
        tDailyEntity.setDoeHandleNum(new BigDecimal(dailyMap.get("RECCASE").toString()));//受理
        tDailyEntity.setDoeOverNum(new BigDecimal(dailyMap.get("FINCASE").toString()));//办结
        tDailyEntity.setDoeAvgHandle(new BigDecimal(dailyMap.get("AVGRECCASE").toString()).doubleValue());//平均受理
        tDailyEntity.setDoeAvgOver(new BigDecimal(dailyMap.get("AVGFINCASE").toString()).doubleValue());//平均办结
        tDailyEntity.setDoeDate(todate);

       long count = tHandleOfEverydayMapper.countByDate(today);
        if(count==0) {
            tHandleOfEverydayMapper.insert(tDailyEntity);
        }else if(count==1){
            tHandleOfEverydayMapper.updateByDate(tDailyEntity);
        }else{
            System.out.println("重复统计数据");
        };
    }
    @Override
    public void getEverydayCondition() {
        Date todate=new Date();
        String today=DateUtil.getDateStr(todate).replace("-","");
//        String today="20180815";
//        String chineseDate ="2018年08月15日";
        THandleOfEveryday tDailyEntity = tHandleOfEverydayMapper.getByDate(today);
        //Redis
        String key="daily"+today;
        List<Map> mapList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        String chineseDate=df.format(todate);

        String[] lineNames = new String[]{"受理量","办结量"};
//        "平均受理量","平均办结量"
        //平均值两位小数
        Object[] values=new Object[]{tDailyEntity.getDoeHandleNum(),tDailyEntity.getDoeOverNum()};
//        decimalRound(tDailyEntity.getDoeAvgHandle(),2),decimalRound(tDailyEntity.getDoeAvgOver(),2)
        for(int i=0;i<2;i++) {
            Map<String,Object> temp=new HashMap<String,Object>();
            temp.put("name",lineNames[i]);
            temp.put("date",chineseDate);
            temp.put("data",values[i]);
            temp.put("code",i);
            mapList.add(temp);
        }

        String jsonStr=JSONObject.toJSONString(mapList);
        this.saveRedisData(key,jsonStr);
    }

    @Override
    public void putWorkAmount() {
        //不同来源
        Map amountNetMap=infoCaseMapper.getAmountBySource(DictionaryUtils.LX_NET);
        Map amountLocalMap=infoCaseMapper.getAmountBySource(DictionaryUtils.LX_LOCAL);
        // 办事深度
        Map depthMap=infoCaseMapper.getDepthCase();

        Date todate=new Date();
        String today=DateUtil.getDateStr(todate).replace("-","");
        //每日工作量
        Map dailyMap=infoCaseMapper.getDailyIncrease(today);
        // 存到统计表
        TCaseInfoStatistics tCaseInfo = new TCaseInfoStatistics();
        tCaseInfo.setId(UUID.randomUUID().toString().replace("-",""));
        tCaseInfo.setUpdatetime(todate);
        tCaseInfo.setCaseinfoNetsave(new BigDecimal(amountNetMap.get("REGISTER").toString()));//网上登记
        tCaseInfo.setCaseinfoNetaccept(new BigDecimal(amountNetMap.get("RECIPE").toString()));//网上受理
        tCaseInfo.setCaseinfoNetfinal(new BigDecimal(amountNetMap.get("FINISH").toString()));//网上办结
        tCaseInfo.setCaseinfoFrontsave(new BigDecimal(amountLocalMap.get("REGISTER").toString()));//窗口登记
        tCaseInfo.setCaseinfoFront(new BigDecimal(amountLocalMap.get("RECIPE").toString()));//窗口受理
        tCaseInfo.setCaseinfoFrontfinal(new BigDecimal(amountLocalMap.get("FINISH").toString()));//窗口办结

        tCaseInfo.setCaseinfoOnline(new BigDecimal(depthMap.get("WSB").toString()));//一次办
        tCaseInfo.setCaseinfoOnce(new BigDecimal(depthMap.get("YCB").toString()));//全程办
        tCaseInfo.setCaseinfoAll(new BigDecimal(depthMap.get("DCB").toString()));//多次办

//        tCaseInfo.setCaseinfoPost();//邮寄量
        tCaseInfo.setCaseinfoTotal(new BigDecimal(dailyMap.get("TOTAL").toString()));//今日总量
        tCaseInfo.setCaseinfoAccept(new BigDecimal(dailyMap.get("RECCASE").toString()));//今日受理量
        tCaseInfo.setCaseinfoFinal(new BigDecimal(dailyMap.get("FINCASE").toString()));//今日办结量
        //今日是否有数据
        long count = tCaseInfoStatisticsMapper.selectByDate(today);
        if(count==0) {
            tCaseInfoStatisticsMapper.insert(tCaseInfo);
        }else if(count==1){
            tCaseInfoStatisticsMapper.updateByDate(tCaseInfo);
        }else{
            System.out.println("重复统计数据");
        }

    }

    @Override
    public void getWorkAmount() {
        Date todate=new Date();
        //取数据
        TCaseInfoStatistics tCaseInfo =  tCaseInfoStatisticsMapper.getDataByDate(todate);
        //redis
        String key = "amountOnline";
        //数据封装
        List<Map> mapList = new ArrayList<>();
        String[] flagNames=new String[]{"预审量","受理量","办结量"};
        Object[] values=new Object[]{tCaseInfo.getCaseinfoNetsave(),tCaseInfo.getCaseinfoNetaccept(),tCaseInfo.getCaseinfoNetfinal()};
        for(int i=0;i<3;i++) {
            Map<String,Object> temp=new HashMap<String,Object>();
            temp.put("name",flagNames[i]);
            temp.put("data",values[i]);
            temp.put("title","网上办事大厅综合信息");
            mapList.add(temp);
        }
        String jsonStr=JSONObject.toJSONString(mapList);
        this.saveRedisData(key,jsonStr);

        //redis
        String keyLocal = "amountLocal";
        //数据封装
        List<Map> mapLocalList = new ArrayList<>();
        String[] localflagNames=new String[]{"受理量","在办量","办结量"};
        Object[] localValues=new Object[]{tCaseInfo.getCaseinfoFrontsave(),tCaseInfo.getCaseinfoFront(),tCaseInfo.getCaseinfoFrontfinal()};
        for(int i=0;i<3;i++) {
            Map<String,Object> temp=new HashMap<String,Object>();
            temp.put("name",localflagNames[i]);
            temp.put("data",localValues[i]);
            temp.put("title","市民之家办事综合信息");
            mapLocalList.add(temp);
        }
        String jsonStrLocal=JSONObject.toJSONString(mapLocalList);
        this.saveRedisData(keyLocal,jsonStrLocal);

        //redis
        String depthkey = "amountDepth";
        //数据封装
        List<Map> depthList = new ArrayList<>();
        String[] depthNames=new String[]{"一次办","网上办","马上办"};
        Object[] depthValues=new Object[]{tCaseInfo.getCaseinfoOnce(),tCaseInfo.getCaseinfoOnline(),tCaseInfo.getCaseinfoAll()};
        for(int i=0;i<3;i++) {
            Map<String,Object> temp=new HashMap<String,Object>();
            temp.put("name",depthNames[i]);
            temp.put("data",depthValues[i]);
            temp.put("title","网办深度统计");
            depthList.add(temp);
        }
        String jsonDepthStr=JSONObject.toJSONString(depthList);
        this.saveRedisData(depthkey,jsonDepthStr);
    }


    @Override
    public void putHotItem() {
//        获取热门数据
        List itemList=infoCaseMapper.getMostItem();
        String key = "itemList";
        String jsonStr=JSONObject.toJSONString(itemList);
        //redis/
        this.saveRedisData(key,jsonStr);

    }

    @Override
    public void putMatterReceive(){
        Date todate=new Date();
        String thisMonday=DateUtil.getDateStr(DateUtil.geLastWeekMonday(todate));
        String nextMonday=DateUtil.getDateStr(DateUtil.getThisWeekMonday(todate));

        //统计认领事务量
        Map countAllMap = tMatterStatisticsMapper.countRecieveMatter(thisMonday,nextMonday);
        //格式处理
       String[] name = new String[]{"已认领事项数","应认领事项数"};
       String[] keynames = new String[]{"DONENUM","SHOULDNUM"};
        List<Map> mapList = new ArrayList<>();
        if(!countAllMap.isEmpty()) {
            for (int i = 0; i < 2; i++) {
                Map<String, Object> temp = new HashMap<String, Object>();
                temp.put("name", name[i]);
                temp.put("data", countAllMap.get(keynames[i]));
                mapList.add(temp);
            }

        //redis
        String jsonStr=JSONObject.toJSONString(mapList);
        String key = "claimCase";
        this.saveRedisData(key,jsonStr);
        }else{
            System.out.println("本周无数据");
        }

    }
}

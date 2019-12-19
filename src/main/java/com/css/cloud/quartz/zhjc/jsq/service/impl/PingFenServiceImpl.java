package com.css.cloud.quartz.zhjc.jsq.service.impl;

import com.alibaba.fastjson.JSON;
import com.css.cloud.quartz.zhjc.jsq.dao.InfoScoreStatisticsMapper;
import com.css.cloud.quartz.zhjc.jsq.dao.TDeftAreaOrderMapper;
import com.css.cloud.quartz.zhjc.jsq.dao.TDeftScoreStatisticsMapper;
import com.css.cloud.quartz.zhjc.jsq.entity.TDeftAreaOrder;
import com.css.cloud.quartz.zhjc.jsq.service.PingFenService;
import com.css.cloud.quartz.zhjc.jsq.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wang.wei on2018/8/7
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class PingFenServiceImpl implements PingFenService {

    @Autowired
    private TDeftScoreStatisticsMapper tDeftScoreStatisticsMapper;
    @Autowired
    private TDeftAreaOrderMapper tDeftAreaOrderMapper;
    @Autowired
    private InfoScoreStatisticsMapper infoScoreStatisticsMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private static final long TIME_OUT = 1800L;

    /**
     * 雷达图处理
     */
    @Override
    public void syncData() {
        log.info("向redis中注入雷达图数据：开始...");
        String lastSun = "2018/08/05";//   DateUtil.getLastSunday();
        String sun = "2018/08/12";// DateUtil.getCurrentSunday();
        String mon = "2018/08/06";// DateUtil.getCurrentMonday();
        List<Map<String, String>> list = tDeftScoreStatisticsMapper.readRecorcByTime(mon, sun);
        List<Map<String, String>> deptList = new ArrayList<>();
        List<Map<String, String>> regionList = new ArrayList<>();
        for (Map<String, String> tDeftScoreStatistics : list) {
            if (tDeftScoreStatistics.get("bmfl".toUpperCase()).equals("1")) {
                deptList.add(tDeftScoreStatistics);
            } else {
                regionList.add(tDeftScoreStatistics);
            }
        }
        //部门雷达图
        String jsonDept = JSON.toJSONString(deptList);
        this.savePFData2Redis("deptKey", jsonDept);
        //区划雷达图
        String jsonRegion = JSON.toJSONString(regionList);
        this.savePFData2Redis("regionKey", jsonRegion);
        log.info("向redis中注入雷达图数据：结束...");
    }

    /**
     * 柱状图处理 以及排名
     */
    @Override
    public void syncData4ZZT() {
        log.info("向redis中注入竖向柱状图、排名所需数据：开始...");
        String lastSun = "2018/08/05";//   DateUtil.getLastSunday();
        String sun = "2018/08/12";// DateUtil.getCurrentSunday();
        String mon = "2018/08/06";// DateUtil.getCurrentMonday();
        List<Map<String, String>> list = tDeftAreaOrderMapper.readRecorcByJgAndRegin(mon, sun);
        List<Map<String, String>> deptList = new ArrayList<>();
        List<Map<String, String>> regionList = new ArrayList<>();
        for (Map<String, String> map : list) {
            if (map.get("lx".toUpperCase()).equals("2")) {
                deptList.add(map);
            } else {
                regionList.add(map);
            }
        }
        //部门柱状图考核
        String jsonDept = JSON.toJSONString(deptList);
        this.savePFData2Redis("deptkh", jsonDept);
        //区划柱状图考核
        String jsonRegion = JSON.toJSONString(regionList);
        this.savePFData2Redis("regionkh", jsonRegion);
        log.info("向redis中注入竖向柱状图、排名所需数据：结束...");
    }

    /**
     * 考核指标 横向柱状图，以小项展示
     */
    @Override
    public void syncData4KHZB() {
        log.info("向redis中注入横向柱状图（小项）数据：开始...syncData4KHZB");
        String lastSun = "2018/08/05";//   DateUtil.getLastSunday();
        String sun = "2018/08/12";// DateUtil.getCurrentSunday();
        String mon = "2018/08/06";// DateUtil.getCurrentMonday();
        //考核指标按机构集合
        List<Map<String, String>> dataList = infoScoreStatisticsMapper.readKhbzDataByDate(lastSun, sun);
        //各指标最高分
        List<Map<String, String>> dictList = infoScoreStatisticsMapper.readDictByby1();
        Map dictMap = new HashMap();
        for (Map dict : dictList) {
            dictMap.put(dict.get("CODE"), dict.get("NAME"));
        }
        String dictJson = JSON.toJSONString(dictMap);
        //每次放入最新的评分标准
        this.savePFData2Redis("dictForPfzb", dictJson);

        //循环将每个机构或者区划的评分记录放入缓存中
        for (Map data : dataList) {
            String key = "khzb";
            if (data.get("TYPE").equals("2")) {
                key = key + data.get("ORG");
            } else {
                key = key + data.get("AREA");
            }
            this.savePFData2Redis(key, JSON.toJSONString(data));
        }
        log.info("向redis中注入横向柱状图（小项）数据：结束...syncData4KHZB");
    }

    /**
     * 统计所有部门
     * 区划的评分，按照八大类展示
     * 柱状图  每隔柱状图分8类或者5类
     * 饼状图（一张网）
     */
    @Override
    public void syncAllData() {
        log.info("向redis中注入柱状图（每个柱状图分8类或者5类）数据：开始...syncAllData");
        String lastSun = "2018/08/05";//   DateUtil.getLastSunday();
        String sun = "2018/08/12";// DateUtil.getCurrentSunday();
        String mon = "2018/08/06";// DateUtil.getCurrentMonday();
        List<Map<String, String>> dataList = infoScoreStatisticsMapper.readAllDataByDate(lastSun, sun);
        //分离 部门和区划的数据
        List<Map<String, String>> deptList = new ArrayList<>();
        List<Map<String, String>> regionList = new ArrayList<>();
        for (Map map : dataList) {
            if (map.get("TYPE") == null) {
                continue;//如何没有type类型，不能区分部门 or  区划，直接舍弃
            }
            if (map.get("TYPE").equals("2")) {//部门数据
                deptList.add(map);
                //向redis中注入部门 一张网数据
                this.savePFData2Redis("yizhangwang" + map.get("ORG"), JSON.toJSONString(map));
            } else {
                regionList.add(map);
                //向redis中注入区划 一张网数据
                this.savePFData2Redis("yizhangwang" + map.get("ORG"), JSON.toJSONString(map));
            }
        }
        //将数据以机构维度 放入redis
        this.savePFData2Redis("allDeptData", JSON.toJSONString(deptList));
        this.savePFData2Redis("allRegionData", JSON.toJSONString(regionList));

        //将八大类型的最高分存入redis
        List<Map<String, String>> dictList = infoScoreStatisticsMapper.readDictNamedPGXM();
//        List<Map<String,String>> dictList1 = infoScoreStatisticsMapper.readDictByby1();
        Map dictMap = new HashMap();
        for (Map dict : dictList) {
            dictMap.put(dict.get("CODE"), dict.get("NAME"));
        }
        String dictJson = JSON.toJSONString(dictMap);
        stringRedisTemplate.opsForHash().putAll("dictNamedPGXM", dictMap);

        //为一张网 存入组织机构代码
        List<Map<String, String>> zzjgList = infoScoreStatisticsMapper.readDeptAndxqzhByPid();
        this.savePFData2Redis("yzwDeptList", JSON.toJSONString(zzjgList));
        //this.savePFData2Redis("dictNamedPGXM",JSON.toJSONString(dictJson));
        log.info("向redis中注入柱状图（每个柱状图分8类或者5类）数据：结束...syncAllData");
    }

    /**
     * 每个机构近六周的数据 以及和其他机构的对比
     */
    @Override
    public void syncWeekData() {
        String lastSun = "2018/08/05";//   DateUtil.getLastSunday();
        String sun = "2018/08/12";// DateUtil.getCurrentSunday();
        String mon = "2018/08/06";// DateUtil.getCurrentMonday();
        //近6周的所有数据
        List<Map> allData = infoScoreStatisticsMapper.readWeekData();
        //部门记录数
        int[] deptCount = {0, 0, 0, 0, 0};
        //区划记录数
        int[] reginCount = {0, 0, 0, 0, 0};
        //部门总分
        double[] deptTotal = {0, 0, 0, 0, 0};
        //区域总分
        double[] reginTotal = {0, 0, 0, 0, 0};
        //装载部门数据
        List<Map> deptData = new ArrayList<>();
        //装载区划数据
        List<Map> reginData = new ArrayList<>();
        for (Map map : allData) {
            String rn = map.get("RN").toString();
            if (map.get("TYPE") != null && map.get("TYPE").equals("2")) {
                deptData.add(map);
                if (rn.equals("1")) {
                    deptTotal[0] = deptTotal[0] + Double.parseDouble(map.get("TOTAL").toString());
                    deptCount[0] = deptCount[0] +1;
                } else if (rn.equals("2")) {
                    deptTotal[1] = deptTotal[1] + Double.parseDouble(map.get("TOTAL").toString());
                    deptCount[1] = deptCount[1] +1;
                } else if (rn.equals("3")) {
                    deptTotal[2] = deptTotal[2] + Double.parseDouble(map.get("TOTAL").toString());
                    deptCount[2] = deptCount[2] +1;
                } else if (rn.equals("4")) {
                    deptTotal[3] = deptTotal[3] + Double.parseDouble(map.get("TOTAL").toString());
                    deptCount[3] = deptCount[3] +1;
                } else if (rn.equals("5")) {
                    deptTotal[4] = deptTotal[4] + Double.parseDouble(map.get("TOTAL").toString());
                    deptCount[4] = deptCount[4] +1;
                } else if (rn.equals("6")) {
                    deptTotal[5] = deptTotal[5] + Double.parseDouble(map.get("TOTAL").toString());
                    deptCount[5] = deptCount[5] +1;
                }
            } else {
                reginData.add(map);
                if (rn.equals("1")) {
                    reginTotal[0] = reginTotal[0] + Double.parseDouble(map.get("TOTAL").toString());
                    reginCount[0] = reginCount[0] +1;
                } else if (rn.equals("2")) {
                    reginTotal[1] = reginTotal[1] + Double.parseDouble(map.get("TOTAL").toString());
                    reginCount[1] = reginCount[1] +1;
                } else if (rn.equals("3")) {
                    reginTotal[2] = reginTotal[2] + Double.parseDouble(map.get("TOTAL").toString());
                    reginCount[2] = reginCount[2] +1;
                } else if (rn.equals("4")) {
                    reginTotal[3] = reginTotal[3] + Double.parseDouble(map.get("TOTAL").toString());
                    reginCount[3] = reginCount[3] +1;
                } else if (rn.equals("5")) {
                    reginTotal[4] = reginTotal[4] + Double.parseDouble(map.get("TOTAL").toString());
                    reginCount[4] = reginCount[4] +1;
                } else if (rn.equals("6")) {
                    reginTotal[5] = reginTotal[5] + Double.parseDouble(map.get("TOTAL").toString());
                    reginCount[5] = reginCount[5] +1;
                }
            }
        }
        //循环机构部门 向redis中注入数据
        List<Map> willToRedisData = new ArrayList<>();
        String orgCode = "";
        int lenth = deptData.size();
        int m =1;
        int n =1;
        for (int i = 0; i <lenth; i++) {
            Map newMap = new HashMap();
            Map po = deptData.get(i);
            newMap.put("name", po.get("RN"));
            newMap.put("score", po.get("TOTAL"));

            newMap.put("code", po.get("ORG"));
            if(willToRedisData.size()==0){
                newMap.put("average", deptTotal[0]/ deptCount[0]);
                willToRedisData.add(newMap);
                orgCode = po.get("ORG").toString();
            }
            else if (willToRedisData.size() != 0 &&  po.get("ORG").toString().equals(orgCode)) {
                newMap.put("average", deptTotal[m]/ deptCount[n]);
                m++;
                n++;
                willToRedisData.add(newMap);
            } else {
                this.savePFData2Redis("comparLineData" + orgCode, JSON.toJSONString(willToRedisData));
                willToRedisData = new ArrayList<>();
                newMap.put("average", deptTotal[0]/ deptCount[0]);
                willToRedisData.add(newMap);
                orgCode=  po.get("ORG").toString();
                m=1;
                n=1;
            }

        }
        willToRedisData = new ArrayList<>();
        orgCode = "";
        m=1;
        n=1;
        int lenth1 = reginData.size();
        for (int j = 0; j <lenth1; j++) {
            Map po = reginData.get(j);
            Map newMap = new HashMap();
            newMap.put("name", po.get("RN"));
            newMap.put("score", po.get("TOTAL"));

            newMap.put("code", po.get("ORG"));
            if(willToRedisData.size()==0){
                newMap.put("average", reginTotal[0] / reginCount[0]);
                willToRedisData.add(newMap);
                orgCode = po.get("ORG").toString();
            }
             else if (willToRedisData.size() != 0 &&  po.get("ORG").toString().equals(orgCode)) {
                newMap.put("average", reginTotal[m] / reginCount[n]);
                m++;
                n++;
                willToRedisData.add(newMap);
            } else {
                this.savePFData2Redis("comparLineData" + orgCode, JSON.toJSONString(willToRedisData));
                willToRedisData = new ArrayList<>();
                newMap.put("average", reginTotal[0] / reginCount[0]);
                willToRedisData.add(newMap);
                orgCode=  po.get("ORG").toString();
                m=1;
                n=1;
            }
        }


    }

    /**
     * 维度： 机构、区划
     * 将部门和区划的评分入库
     */
    @Override
    public void saveScoreToDBByOrg() {
        log.info("向DB存入各个机构的总分 数据：开始...saveScoreToDBByOrg");
//        String test1 = DateUtil.getCurrentMonday();
        //todo


        String lastSun =  DateUtil.getLastSunday();//"2018/08/05";//   DateUtil.getLastSunday();
        String sun =  DateUtil.getCurrentSunday();//"2018/08/12";// DateUtil.getCurrentSunday();
        String mon = DateUtil.getCurrentMonday();//"2018/08/06";// DateUtil.getCurrentMonday();
        //查询出统计评分数据
        List<TDeftAreaOrder> dataList = infoScoreStatisticsMapper.readScoreByOrg(lastSun, sun);
        //每条记录生成主key
        for (TDeftAreaOrder tDeftAreaOrder : dataList) {
            if(tDeftAreaOrder==null){
                continue;
            }
            String id = UUID.randomUUID().toString().replace("-", "");
            tDeftAreaOrder.setId(id);
            try {
                tDeftAreaOrder.setDaoBeginTime((new SimpleDateFormat("yyyy/MM/dd")).parse(mon));
                tDeftAreaOrder.setDaoEndTime((new SimpleDateFormat("yyyy/MM/dd")).parse(sun));
            } catch (ParseException e) {
                log.info("saveScoreToDBByOrg：：：：日期格式转换错误");
                e.printStackTrace();
            }
        }
        //  暂时应对方式 插入之前删除对应数据
        if(dataList!=null && dataList.size()!=0){
            int deleteCount = tDeftAreaOrderMapper.deleteByWeek(mon, sun);
            int count = tDeftAreaOrderMapper.insertByPO(dataList);
        }
        //log.info(""+count);
        log.info("向DB存入各个机构的总分 数据：结束...saveScoreToDBByOrg");
    }

    /**
     * 维度 ：类型
     * 将一周的评分存入统计表
     */
    @Override
    public void saveScoreToDBByType() {
        log.info("向DB存入 各个类型的数据 数据：开始...saveScoreToDBByType");
        String lastSun = "2018/07/08";//   DateUtil.getLastSunday();
        String sun = "2018/07/15";// DateUtil.getCurrentSunday();
        String mon = "2018/07/09";// DateUtil.getCurrentMonday();
        //查询出统计评分数据
        List<Map> dataList = infoScoreStatisticsMapper.readScoreByType(lastSun, sun);
        String[] col1 = {"CLAIM", "GUIDE", "DZZW", "WSBS", "ZXBLSD", "SSO", "DZZZ", "QZDD"};
        String[] col2 = {"CLAIM", "GUIDE", "DZZW", "ZXBLSD", "SSO"};
        //用于装在DEPT_SCORE_STATISTICS数据
        List<Map> dssList = new ArrayList<>();
        for (Map map : dataList) {
            if (map.get("SS_TYPE").equals("2")) {//部门
                for (int i = 1; i < 9; i++) {
                    Map dssMap = new HashMap();
                    String id = UUID.randomUUID().toString().replace("-", "");
                    dssMap.put("id", id);
                    dssMap.put("type", i + "");
                    dssMap.put("score", map.get(col1[i - 1]));
                    dssMap.put("deptOrarea", "2");
                    try {
                        dssMap.put("startTime", new SimpleDateFormat("yyyy/MM/dd").parse(mon));
                        dssMap.put("endTime", (new SimpleDateFormat("yyyy/MM/dd")).parse(sun));
                    } catch (ParseException e) {
                        log.info("saveScoreToDBByOrg：：：：日期 格式转换错误");
                        e.printStackTrace();
                    }
                    dssList.add(dssMap);
                }
            } else if (map.get("SS_TYPE").equals("1")) {
                for (int i = 1; i < 6; i++) {
                    Map dssMap = new HashMap();
                    String id = UUID.randomUUID().toString().replace("-", "");
                    dssMap.put("id", id);
                    dssMap.put("type", i + "");
                    dssMap.put("score", map.get(col2[i - 1]));
                    dssMap.put("deptOrarea", "1");
                    try {
                        dssMap.put("startTime", new SimpleDateFormat("yyyy/MM/dd").parse(mon));
                        dssMap.put("endTime", (new SimpleDateFormat("yyyy/MM/dd")).parse(sun));
                    } catch (ParseException e) {
                        log.info("saveScoreToDBByOrg：：：：日期格式转换错误");
                        e.printStackTrace();
                    }
                    dssList.add(dssMap);
                }
            }
        }
        int count = tDeftScoreStatisticsMapper.insertByPO(dssList);
        log.info("向DB存入 各个类型的数据 数据：结束...saveScoreToDBByType");
    }

    private void savePFData2Redis(String key, String value) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(key, value);//暂时不设置过期时间
//        ops.set(key, value,120, TimeUnit.SECONDS);
    }
}

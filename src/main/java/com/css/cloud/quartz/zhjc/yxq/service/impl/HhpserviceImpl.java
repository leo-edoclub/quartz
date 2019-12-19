package com.css.cloud.quartz.zhjc.yxq.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.css.cloud.quartz.common.RedisService;
import com.css.cloud.quartz.zhjc.yxq.dao.TZhjcYxBjtjMapper;
import com.css.cloud.quartz.zhjc.yxq.dao.TZhjcYxHhpMapper;
import com.css.cloud.quartz.zhjc.yxq.service.HhpService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 红黄牌service
 * Created by wang.wei on2018/9/12
 */
@Service
public class HhpserviceImpl implements HhpService {

    @Autowired
    private TZhjcYxHhpMapper hhpMapper;
    @Autowired
    private TZhjcYxBjtjMapper bjtjMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private RestTemplate restTemplate;
    private static final String TOKEN = "7ed9482f-2e4b-4c34-a1fb-ce1a163f51f6";
    //红黄牌redis key 的前缀
    private static final String PREFIX_YELLOW_KEY = "yxq-zhyx-yellow-day-";
    private static final String PREFIX_RED_KEY = "yxq-zhyx-red-day-";

    /**
     * 红黄牌入redis
     */
    @Override
    public void insertByDay() {
        Map redMap = null;
        Map yellowMap = null;
        String today = DateFormatUtils.format(new Date(), "yyyyMMdd");
        //查询当前时间 当天的红黄牌数量
        Map hhpTotal = hhpMapper.selectHhpByDay(today);

        //判断是否有红黄牌
        if (hhpTotal != null && hhpTotal.size() != 0 && (Integer.parseInt(hhpTotal.get("HPS").toString()) != 0 || Integer.parseInt(hhpTotal.get("HPS").toString()) != 0)) {
            //查询当前时间 当天的办件数
            Map bjlTotal = bjtjMapper.selectBjtjByDay(today);

            if (bjlTotal != null && bjlTotal.size() != 0) {
                //数据库没有数据，暂时应对；有数据后删除
                int bjsl = Integer.parseInt(bjlTotal.get("TOTAL") + "") == 0 ? 5 : Integer.parseInt(bjlTotal.get("TOTAL") + "");
                int red = Integer.parseInt(hhpTotal.get("HPS") + "");
                int yellow = Integer.parseInt(hhpTotal.get("HPYJS") + "");
                int percentRed = (int) (red / (double) bjsl * 100);
                int percentYellow = (int) (yellow / (double) bjsl * 100);
                yellowMap = createMap("预警数", yellow, percentYellow);
                redMap = createMap("红牌数", red, percentRed);
            }

        } else {//红黄牌都为0，redis存0
            yellowMap = createMap("预警数", 0, 0);
            redMap = createMap("红牌数", 0, 0);
        }
        //入redis
        redisService.set(PREFIX_YELLOW_KEY + today, yellowMap);
        redisService.set(PREFIX_RED_KEY + today, redMap);
        System.out.println("红黄牌统计");
    }

    @Override
    public void insertByRest() {
        String today = DateFormatUtils.format(new Date(), "yyyyMMdd");
        for (int i = 1; i <= 2; i++) {
            Map map = new HashMap();
            map.put("token", TOKEN);
            map.put("type", i + "");//1预警数 2红牌数

            String url = "http://10.41.215.160:7001/" + "viewItem.jsp?token=" + TOKEN + "&type=" + i;
            try {
                ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, map);
                String body = responseEntity.getBody();
                System.out.println(body);
                JSONObject obj = JSON.parseObject(body);
                if (!obj.get("code").toString().equals("1")) {
                    System.out.println("红黄牌：code值为0，远程调用失败");
                } else {
                    JSONObject objrs = JSON.parseObject(obj.getString("rs"));
                    String num = objrs.getString("num");
                    System.out.println(" wo shi hong huang pai:" + i + num);
                    Map result = new HashMap();
                    if (i == 1) {//预警

                        result = createMap("预警数", Integer.parseInt(num), 2);
                        redisService.set(PREFIX_YELLOW_KEY + today, result);
                    } else {//红牌
                        result = createMap("红牌数", Integer.parseInt(num), 5);
                        redisService.set(PREFIX_RED_KEY + today, result);
                    }
                }
                System.out.println(obj);
            } catch (Exception e) {
                Map yellowMap = createMap("预警数", 0, 0);
                Map redMap = createMap("红牌数", 0, 0);
                redisService.set(PREFIX_YELLOW_KEY + today, yellowMap);
                redisService.set(PREFIX_RED_KEY + today, redMap);
                System.out.println("红黄牌远程调用失败，本次不更新！");
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 组装红黄牌的格式
     *
     * @param name
     * @param value
     * @param percent
     * @return
     */
    public Map createMap(String name, int value, int percent) {
        Map map = new HashMap();
        map.put("name", name);
        map.put("value", String.valueOf(value));
        map.put("percent", String.valueOf(percent));
        return map;
    }
}

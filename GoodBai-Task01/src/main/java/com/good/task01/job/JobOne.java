package com.good.task01.job;


import com.good.task01.dao.ShareChangeDaoImpl;
import com.good.task01.entity.ShareDetailEntity;
import com.good.task01.entity.ShareQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Mg on 2018/1/15.
 */
public class JobOne {

    private String hours;

    @Resource(name="shareChangeDao")
    private ShareChangeDaoImpl shareChangeDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(JobOne.class);

    public  void run(){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date time = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(time);
            int week = calendar.get(Calendar.DAY_OF_WEEK)-1;
            if(week==0 || week==6){
                LOGGER.info("时间:{} 双休日 不做定时任务--- !!",sdf.format(time));
            }else{
                dealMessage(time);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        LOGGER.info(" 定时任务 执行 完成 !!");
    }

    public void Test(){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date time =sdf.parse("2018-03-14 18:00:00");
            dealMessage(time);
        }catch (Exception e){
            e.printStackTrace();
        }
        LOGGER.info(" 定时任务 执行 完成 !!");
    }

    private void dealMessage(Date time) throws  Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int endDay = Integer.valueOf(sdf.format(time));
        int endHour = calendar.get(Calendar.HOUR_OF_DAY);
        LOGGER.info("开始执行定时任务 day:{} hour:{}.....",endDay,endHour);
        int[] before = getIndex(endHour);
        int startHour = before[0];
        int beDay = before[1];
        if(startHour==-1){
            LOGGER.error("未查询到 修改内容");
            return ;
        }
        calendar.add(Calendar.DATE,beDay);
        Date startTime = calendar.getTime();
        int startDay = Integer.valueOf(sdf.format(startTime));
        ShareQuery query = new ShareQuery(startDay,startHour,endDay,endHour);
        int noNum = shareChangeDao.getEndDetail(query);
        if(noNum==0){
            LOGGER.error("未查询到 当前数据");
            return ;
        }
        List<ShareDetailEntity> list = shareChangeDao.getChangeList(query);
        if(list==null || list.size()==0){
            LOGGER.info("2次数据 并未有差异 not change ");
            return ;
        }
        List<Integer> delIds = new ArrayList<>();
        List<Integer> addIds = new ArrayList<>();
        for(ShareDetailEntity entity:list){
            int type = entity.getType();
            if(type>0){
                addIds.add(entity.getId());
            }else if(type<0){
                delIds.add(entity.getId());
            }
        }
        query.setIds(addIds);
        shareChangeDao.updateAddType(query);
        query.setIds(delIds);
        shareChangeDao.updateDelType(query);
    }

    private int[] getIndex(int num){
        int[] hourList = getHourList();
        int[] value = new int[2];
        int len = hourList.length;
        for(int i=0;i<len;i++){
            if(hourList[i]==num){
                if(i==0){
                    value[0] = hourList[len-1];
                    value[1] = -1;
                    return value;
                }else{
                    value[0] = hourList[i-1];
                    value[1] = 0;
                    return value;
                }
            }
        }
        value[0] = -1;
        return value;
    }

    public String getHours() {
        return hours;
    }
    private int[] getHourList(){
        int[] value = {9,12,15,18};
        if(hours==null||hours.equals("")){
            return value;
        }
        String[] init = hours.split(",");
        int[] result = new int[init.length];
        try{
            for(int i=0;i<init.length;i++){
                result[i] = Integer.valueOf(init[i]);
            }
            return result;
        }catch (Exception e){
            return value;
        }

    }
    public void setHours(String hours) {
        this.hours = hours;
    }
}

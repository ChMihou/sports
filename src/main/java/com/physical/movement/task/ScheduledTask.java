package com.physical.movement.task;

import com.physical.movement.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ScheduledTask {

    @Autowired
    CourtService courtService;

    /**
     * 定时插入第二天的订场信息  晚上12点
     * @throws ParseException
     */
    @Scheduled(cron = "0 0 10 * * ?")
    public void scheduledTask() throws ParseException {
        System.out.println("开始插入订场场地信息");
        courtService.addListCourt();
        System.out.println("订场场地信息插入成功");
    }
}


//    @Scheduled(fixedRate = 3000) ：上一次开始执行时间点之后 3 秒再执行（fixedRate 属性：定时任务开始后再次执行定时任务的延时（需等待上次定时任务完成），单位毫秒）
//    @Scheduled(fixedDelay = 3000) ：上一次执行完毕时间点之后 3 秒再执行（fixedDelay 属性：定时任务执行完成后再次执行定时任务的延时（需等待上次定时任务完成），单位毫秒）
//    @Scheduled(initialDelay = 1000, fixedRate = 3000) ：第一次延迟1秒后执行，之后按fixedRate的规则每 3 秒执行一次（initialDelay 属性：第一次执行定时任务的延迟时间，需配合fixedDelay或者fixedRate来使用）
//    @Scheduled(cron="0 0 2 1 * ? *") ：通过cron表达式定义规则

package com.jhj.oss.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduleTask {




    @Scheduled(cron = "0 0 0 * * ?")
    public void doTask() {
        log.info("定时任务-start");

        log.info("定时任务-end");
    }


}

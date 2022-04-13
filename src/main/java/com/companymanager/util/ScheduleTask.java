package com.companymanager.util;

import com.alibaba.druid.util.StringUtils;
import com.companymanager.entity.condition.SalaryOrderTopic;
import com.companymanager.web.controller.HRAdminController;
import com.companymanager.web.service.IAdminService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 计算工资的定时任务类
 *
 * */
@Configuration  //配置类
@EnableScheduling  //开启定时任务
public class ScheduleTask  implements SchedulingConfigurer {

    @Autowired
    private IAdminService adminService;


    private static final Logger LOG = LoggerFactory.getLogger(ScheduleTask.class);


    @Mapper
    public interface CronMapper {
        @Select("select cron from cron limit 1")
        public String getCron();
    }

    @Autowired      //注入mapper
    @SuppressWarnings("all")
    CronMapper cronMapper;

    @Autowired
    private HRAdminController hrAdminController;


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> {//执行任务
                    LOG.info("##########开始定时任务##########");
                    List<SalaryOrderTopic> list = adminService.queryEveryEmpSalary();

                    LOG.info("工资集合大小："+list.size());

                    adminService.saveSalaryRecord(list);

                    LOG.info("保存集合成功");

                    List<SalaryOrderTopic> salaryOrderTopicList = HRAdminController.getSalaryOrderTopicListObject();
                    salaryOrderTopicList = list;

                    LOG.info("salaryOrderTopicList已赋值！");
                    LOG.info("##########定时任务完成##########");
                },
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = cronMapper.getCron();
//                    //2.2 合法性校验.
//                    if (StringUtils.isEmpty(cron)) {
//                       return
//                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );

    }

}

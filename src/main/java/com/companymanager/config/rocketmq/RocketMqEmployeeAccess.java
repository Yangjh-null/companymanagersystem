package com.companymanager.config.rocketmq;

import com.companymanager.util.SendMailUtil;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RocketMQMessageListener(consumerGroup = "${rocketmq.producer.group}", topic = "rocketmq-group-employee-access")
public class RocketMqEmployeeAccess implements RocketMQListener<Map<String,String>> {

    @Autowired
    private SendMailUtil sendMailUtil;


    @Override
    public void onMessage(Map<String, String> map) {

        sendMailUtil.sendAccessAdviceToEmployee(map);

    }
}

package com.companymanager.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Map;

//发送邮件
@Component
public class SendMailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromMail;



    //1. 注册审批通知
    public void sendAccessAdviceToEmployee(Map<String,String> map){

        String toMail = map.get("toMail"); //真实员工邮箱
        //测试邮箱
        toMail = "1747846658@qq.com";
        int access = Integer.parseInt(map.get("status"));
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setTo(toMail);
        String text = "尊敬的"+map.get("empName")+"您好,\n";
        if(access == 1){//审批通过！
            text = text + "您的员工号是"+map.get("empId")+"稍后请使用员工号与身份证号码后六位登录企业管理系统。";
        }else{//审批不通过
            text = text + "很遗憾,由于您填写的信息不符合要求，此次申请被驳回，如有疑问请联系hr  邮箱XXXX@XXX.com。(联系方式:15594791983)";
        }
        simpleMailMessage.setSubject("XX企业管理系统"+"--员工账号审批结果通知");
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }

    //2. 发送工资提醒
}

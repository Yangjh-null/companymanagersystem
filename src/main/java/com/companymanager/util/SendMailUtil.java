package com.companymanager.util;

import com.companymanager.entity.condition.EmployeeCondition;
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
    public void sendAccessAdviceToEmployee(EmployeeCondition emp){

        String toMail = emp.getEmpMail(); //真实员工邮箱
        //测试邮箱
        //toMail = "1747846658@qq.com";
        toMail = "1799785545@qq.com";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setTo(toMail);
        String text = "尊敬的"+emp.getEmpName()+"您好,您的个人信息如下 员工号："+emp.getEmpId()+" 基本工资："+emp.getSarBasic()+
                "绩效工资："+emp.getSarMerits()+"职位："+emp.getEmpPosName()+"稍后请使用员工号与身份证号码后六位登录企业管理系统。";

        simpleMailMessage.setSubject("XX企业管理系统"+"--员工账号审批结果通知");
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }

    //2. 发送工资提醒
}

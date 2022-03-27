package com.companymanager.web.controller;


import com.companymanager.entity.SalaryInfo;
import com.companymanager.entity.TransactionInfo;
import com.companymanager.entity.TransactionInfoSum;
import com.companymanager.entity.condition.TransInfoSumCondition;
import com.companymanager.web.service.ILeaderService;
import com.companymanager.z_resultpackage.Result;
import com.companymanager.z_resultpackage.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("leader")
public class LeaderController {

    @Autowired
    private ILeaderService leaderService;

    //按照部门id 查看考勤
    @RequestMapping("checkOnTime")
    public Result queryCheckOnTimeByDeptId(@RequestBody Map<String,String> map){
        List<TransInfoSumCondition> list = leaderService.queryCheckOnTimeByDeptId(map);
        return Result.successAndData(ResultStatus.SUCCESS,list);
    }

    //设置绩效
    @RequestMapping("empMerits")
    public Result settingMerits(@RequestBody SalaryInfo salaryInfo){
        int row = leaderService.updateMeritsByEmpId(salaryInfo);
        return row == 1 ?Result.successNoData(ResultStatus.SUCCESS) : Result.fail(ResultStatus.FAIL);
    }





}

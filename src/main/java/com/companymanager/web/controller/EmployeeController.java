package com.companymanager.web.controller;

import com.companymanager.entity.Employee;
import com.companymanager.entity.TransactionInfo;
import com.companymanager.entity.TransactionInfoSum;
import com.companymanager.util.RedisUtil;
import com.companymanager.web.service.IEmployeeService;
import com.companymanager.z_resultpackage.Result;
import com.companymanager.z_resultpackage.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.companymanager.web.service.IEmployeeServiceImpl.EMP_KEY;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("register")
    public Result register(@RequestBody Employee employee){

        int row = employeeService.insertIntoNewEmployee(employee);
        return row ==1 ? Result.successNoData(ResultStatus.SUCCESS):Result.fail(ResultStatus.FAIL);
    }

    @RequestMapping("login")
    public Result loginController(@RequestBody Map<String,String> map){
        if(redisUtil.get(EMP_KEY+map.get("empId")) == null){ //未注册 账号
            return Result.successNoData(ResultStatus.USER_NOT_EXIST);//不存在
        }
        Employee emp = employeeService.queryEmployeeByUserNameAndPassword(map);
        return emp == null ? Result.fail(ResultStatus.FAIL):Result.successAndData(ResultStatus.SUCCESS,emp);
    }

    @RequestMapping("updatePassword")
    public Result updatePassword(@RequestBody Map<String,String> map){
        int row = employeeService.updateEmployeePassword(map);
        return row == 1? Result.successNoData(ResultStatus.SUCCESS) : Result.successNoData(ResultStatus.FAIL);
    }
    //修改个人信息
    @RequestMapping("updateEmpInfo")
    public Result updateEmployeeInfo(@RequestBody Employee emp){
        int row  = employeeService.updateEmployeeInfo(emp);
        return row == 1 ?Result.successNoData(ResultStatus.SUCCESS):Result.successNoData(ResultStatus.FAIL);
    }

    //申请事务  请假 加班
    @RequestMapping("insertInfo")
    public Result insertTransactionInfo(@RequestBody TransactionInfo transactionInfo){
       int row = employeeService.insertTransactionInfo(transactionInfo);
        return row == 1?Result.successNoData(ResultStatus.SUCCESS):Result.fail(ResultStatus.FAIL);
    }

    //查看考勤
    @RequestMapping("checkOnTime")
    public Result queryTransactionInfoSum(@RequestBody Map<String,String>map){
        List<TransactionInfoSum> list = employeeService.queryTransactionInfoSumByDate(map);
        return Result.successAndData(ResultStatus.SUCCESS,list);
    }

    //打卡  考勤
    @RequestMapping("clockIn")
    public Result checkOnWorkEveryday(@RequestBody Map<String,String> map){
        int row = employeeService.updateEmpSingEveryDay(map);
        if(row == -1){
            return Result.fail(ResultStatus.FAIL);
        }
        return Result.successNoData(ResultStatus.SUCCESS);
    }




    //查看薪资
    @RequestMapping("checkSalary")
    public Result checkSalary(@RequestBody String empId){


        return null;
    }


}

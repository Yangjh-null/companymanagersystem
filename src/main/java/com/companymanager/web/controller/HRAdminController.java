package com.companymanager.web.controller;

import com.companymanager.entity.Employee;
import com.companymanager.entity.TransactionInfo;
import com.companymanager.entity.UtilInfo;
import com.companymanager.entity.condition.SalaryOrderTopic;
import com.companymanager.web.service.IAdminService;
import com.companymanager.z_resultpackage.Result;
import com.companymanager.z_resultpackage.ResultStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class HRAdminController {

    private static final Logger LOG = LoggerFactory.getLogger(HRAdminController.class);

    @Autowired
    private IAdminService adminService;

    //登录
    @RequestMapping("login")
    public Result adminLogin(@RequestBody Map<String,String> map){
        int row = adminService.queryAdmin(map);
        return  row == 1 ?Result.successNoData(ResultStatus.SUCCESS):Result.fail(ResultStatus.FAIL);
    }

    //员工注册审批

        //1. 查看所有未审批的员工
    @RequestMapping("noAccessEmp")
    public Result accessEmployeeStatus(){
        List<Employee> employeeList = adminService.queryNoAccessEmp();
        return Result.successAndData(ResultStatus.SUCCESS,employeeList);
    }
        //2.修改状态
    @RequestMapping("updateAccessStatus")
    public Result updateEmployeeAccess(@RequestBody Map<String,String> map ){
        if(map.get("status").equals("0")){
            return Result.successNoData(ResultStatus.SUCCESS);
        }
        boolean bool = adminService.updateEmployeeStatus(map);
        return bool ? Result.successNoData(ResultStatus.SUCCESS):Result.fail(ResultStatus.FAIL);
    }

    //查看申请的事务
    @RequestMapping("checkTransInfo")
    public Result checkAllTransactionInfo(@RequestBody Map<String ,String> map){

        List<TransactionInfo> list = adminService.queryTransactionInfo(map);

        return Result.successAndData(ResultStatus.SUCCESS,list);
    }

    //审批事务 修改状态值
    @RequestMapping("updateTranStatus")
    public Result updateTranStatus(@RequestBody Map<String,String>map){
        if(map.get("status").equals('0') ){  //表示驳回
            //加入消息队列 通知
            return Result.successNoData(ResultStatus.SUCCESS);
        }
        boolean bool = adminService.updateTranStatus(map);
        return bool? Result.successNoData(ResultStatus.SUCCESS):Result.fail(ResultStatus.FAIL) ;
    }

    //查看考勤等规则工具表
    @RequestMapping("checkUtilInfo")
    public Result queryUtilInfo(){
        return Result.successAndData(ResultStatus.SUCCESS,adminService.queryUtilInfo());
    }

    //修改考勤规则表
    @RequestMapping("updateUtilInfo")
    public Result updateUtilInfo(@RequestBody UtilInfo utilInfo){
        int row = adminService.updateUtilInfo(utilInfo);
        return row == 1? Result.successNoData(ResultStatus.SUCCESS): Result.fail(ResultStatus.FAIL);
    }

    //查看各部门下的总人数
    @RequestMapping("querySumByDeptId")
    public Result querySumByDeptId(){
        List<Map<String,Integer>> list = adminService.queryDeptSumByDeptId();
        return Result.successAndData(ResultStatus.SUCCESS,list);

    }
    //结算工资
    @RequestMapping("settleAccounts")
    public Result settleAccounts(){
        List<SalaryOrderTopic> list = adminService.queryEveryEmpSalary();
        return Result.successAndData(ResultStatus.SUCCESS,list);
    }



}

package com.companymanager.web.controller;

import com.companymanager.config.rocketmq.RocketMqHelper;
import com.companymanager.entity.DeptInfo;
import com.companymanager.entity.Employee;
import com.companymanager.entity.TransactionInfo;
import com.companymanager.entity.UtilInfo;
import com.companymanager.entity.condition.EmployeeCondition;
import com.companymanager.entity.condition.Position;
import com.companymanager.entity.condition.SalaryOrderTopic;
import com.companymanager.util.RedisUtil;
import com.companymanager.util.ScheduleTask;
import com.companymanager.web.service.IAdminService;
import com.companymanager.z_resultpackage.Result;
import com.companymanager.z_resultpackage.ResultStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("admin")
public class HRAdminController {



    private static  List<SalaryOrderTopic> salaryOrderTopicList = new ArrayList<>();

    private HRAdminController() { }
    //静态工厂方法 返回唯一实例
    public static List<SalaryOrderTopic> getSalaryOrderTopicListObject(){
        return salaryOrderTopicList;
    }

    private static final Logger LOG = LoggerFactory.getLogger(HRAdminController.class);

    public static final String  EMP_KEY = "EMPLOYEE_KEY:";

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RocketMqHelper rocketMqHelper;

    @Autowired
    private IAdminService adminService;

    //登录
    @RequestMapping("login")
    public Result adminLogin(@RequestBody Map<String,String> map){
        int row = adminService.queryAdmin(map);
        return  row == 1 ?Result.successNoData(ResultStatus.SUCCESS):Result.fail(ResultStatus.FAIL);
    }

    //员工注册审批  员工管理 -》 新增员工
    @RequestMapping("insertNewCompany")
    public Result insertNewCompany(@RequestBody EmployeeCondition employeeCondition){
        String empId = UUID.randomUUID().toString().substring(3,8);
        employeeCondition.setEmpId(empId);
        redisUtil.set(EMP_KEY+empId,"1");
        int row = adminService.insertNewCompanyInfo(employeeCondition);
        if(row == 2){
            rocketMqHelper.asyncSend("rocketmq-group-employee-access",MessageBuilder.withPayload(employeeCondition).build());
            return Result.successNoData(ResultStatus.SUCCESS);
        }
        return Result.fail(ResultStatus.FAIL);
    }

    //修改员工信息
    @RequestMapping("updateEmpInfo")
    public Result updateEmployeeInfo(@RequestBody  EmployeeCondition emp){
        boolean bool = adminService.updateEmpInfo(emp);
        return bool ? Result.successNoData(ResultStatus.SUCCESS): Result.fail(ResultStatus.FAIL) ;
    }

        //1. 查看所有未审批的员工
//    @RequestMapping("noAccessEmp")
//    public Result accessEmployeeStatus(){
//        List<Employee> employeeList = adminService.queryNoAccessEmp();
//        return Result.successAndData(ResultStatus.SUCCESS,employeeList);
//    }
//        //2.修改状态
//    @RequestMapping("updateAccessStatus")
//    public Result updateEmployeeAccess(@RequestBody Map<String,String> map ){
//        if(map.get("status").equals("0")){
//            rocketMqHelper.asyncSend("rocketmq-group-employee-access", MessageBuilder.withPayload(map).build());
//            return Result.successNoData(ResultStatus.SUCCESS);
//        }
//        boolean bool = adminService.updateEmployeeStatus(map);
//
//        if(bool){ //修改成功在发送
//            rocketMqHelper.asyncSend("rocketmq-group-employee-access", MessageBuilder.withPayload(map).build());
//            return Result.successNoData(ResultStatus.SUCCESS);
//        }
//        return Result.fail(ResultStatus.FAIL); //失败
//    }

    //查看所有的员工
    @RequestMapping("queryAllEmp")
    public Result queryAllEmployee(){
        List<EmployeeCondition> list = adminService.queryAllEmployee();
        return Result.successAndData(ResultStatus.SUCCESS,list);
    }

    //高级搜索
    @RequestMapping("highSearchEmp")
    public Result highSearchEmp(@RequestBody Map<String,String> map){
        List<EmployeeCondition> list = adminService.highSearch(map);
        return Result.successAndData(ResultStatus.SUCCESS,list);
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

    @RequestMapping("highSearchPosition")
    public Result highSearchPosition(@RequestBody Map<String,String> map){
        List<Position> list = adminService.queryPositionInfo(map);
        return Result.successAndData(ResultStatus.SUCCESS,list);
    }

    //新增部门 / 职位 依靠传参主键 判断
    @RequestMapping("insertDeptOrPos")
    public Result insertDept(@RequestBody DeptInfo deptInfo){
        boolean bool = adminService.insertNewDept(deptInfo);
        return Result.successNoData(ResultStatus.SUCCESS);
    }

    //删除部门
    @RequestMapping("delDept")
    public Result deleteDept(@RequestBody Map<String,String>map){
        int deptId = Integer.parseInt(map.get("deptId"));
        boolean bool = adminService.deleteDeptById(deptId);
        return bool ? Result.successNoData(ResultStatus.SUCCESS):Result.successNoData(ResultStatus.FAIL);
    }
    //删除职位
    @RequestMapping("delPos")
    public Result deletePosition(@RequestBody Map<String,String> map){
        int posId = Integer.parseInt(map.get("posId"));
        boolean bool = adminService.deletePositionById(posId);
        return bool ? Result.successNoData(ResultStatus.SUCCESS):Result.successNoData(ResultStatus.FAIL);

    }



    //结算工资
    @RequestMapping("settleAccounts")
    public Result settleAccounts(){
        //获取当前工资集合
        List<SalaryOrderTopic> list = getSalaryOrderTopicListObject();
        return Result.successAndData(ResultStatus.SUCCESS,list);
    }


    //保存工资记录
    @RequestMapping("saveSalary")
    public Result saveSalaryRecord(@RequestBody List<SalaryOrderTopic> list){

        int effRow = adminService.saveSalaryRecord(list);

        return effRow == list.size() ?Result.successNoData(ResultStatus.SUCCESS): Result.fail(ResultStatus.FAIL);
    }

}

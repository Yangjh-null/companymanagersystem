package com.companymanager.web.service;

import com.companymanager.entity.Employee;
import com.companymanager.entity.TransactionInfo;
import com.companymanager.entity.TransactionInfoSum;
import com.companymanager.util.RedisUtil;
import com.companymanager.web.dao.IEmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class IEmployeeServiceImpl implements  IEmployeeService{

    public static final String  EMP_KEY = "EMPLOYEE_KEY:";

    @Autowired
    private IEmployeeMapper employeeMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public int insertIntoNewEmployee(Employee employee) {
        //生成工号：主键
         String empId = UUID.randomUUID().toString().substring(3,8);
         employee.setEmpId(empId);
         redisUtil.set(EMP_KEY+empId,"1");
         int row = employeeMapper.insertNewEmployee(employee);
         return row;
    }

    //通过用户名密码查找已审批的员工  已审批方可登录成功 否则 等待人事审批
    @Override
    public Employee queryEmployeeByUserNameAndPassword(Map<String,String> map) {
        Employee emp = employeeMapper.queryEmployee(map);
        return emp;
    }

    //修改密码
    @Override
    public int updateEmployeePassword(Map<String, String> map) {
        return employeeMapper.updateEmployeePassword(map);
    }

    //修改员工基本信息
    @Override
    public int updateEmployeeInfo(Employee employee) {
        int row = employeeMapper.updateEmployeeInfo(employee);
        return row;
    }

    //申请事务
    @Override
    public int insertTransactionInfo(TransactionInfo transactionInfo) {
        int row = employeeMapper.insertTransaction(transactionInfo);
        return row;
    }

    //查看考勤
    @Override
    public List<TransactionInfoSum> queryTransactionInfoSumByDate(Map<String, String> map) {
        List<TransactionInfoSum> list = employeeMapper.queryTransactionInfoSumByDate(map);
        return list == null? new ArrayList<>():list;
    }

    //添加迟到早退的状态
    @Override
    public int updateEmpSingEveryDay(Map<String, String> map) {
        String empId = map.get("empId");
        int row = -1;
        if("1".equals(map.get("status"))) {//迟到
             row = employeeMapper.updateEmpSingLateSingEveryday(empId);
        }else if("2".equals(map.get("status"))){ //早退
             row = employeeMapper.updateEmpExitSingEveryDay(empId);
        }
        return row;
    }
}

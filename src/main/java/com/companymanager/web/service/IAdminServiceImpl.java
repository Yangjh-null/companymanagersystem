package com.companymanager.web.service;

import com.companymanager.entity.Employee;
import com.companymanager.entity.TransactionInfo;
import com.companymanager.entity.UtilInfo;
import com.companymanager.entity.condition.SalaryOrderTopic;
import com.companymanager.entity.condition.TransInfoSumCondition;
import com.companymanager.web.dao.IAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class IAdminServiceImpl implements IAdminService {

   @Autowired
   private IAdminMapper adminMapper;

    @Override
    public int queryAdmin(Map<String, String> map) {
        return adminMapper.queryAdmin(map);
    }


    //审批注册员工
        //1.查看未审批的员工
    @Override
    public List<Employee> queryNoAccessEmp() {
        List<Employee> list = adminMapper.queryNoAccessEmp();

        return list == null ? new ArrayList<>(): list;
    }
        //2.修改状态
    @Override
    @Transactional
    public boolean updateEmployeeStatus(Map<String, String> map) {
       int row1 =  adminMapper.updateEmployeeStatus(map);
       int row2 = adminMapper.updateSalaryByEmpId(map);
        return (row1 == 1) && (row2 == 1);
    }

    //查看事务  未审批 已审批
    @Override
    public List<TransactionInfo> queryTransactionInfo(Map<String,String> map) {
        List<TransactionInfo> list = adminMapper.queryTransactionInfo(map);
        return list == null ? new ArrayList<>():list;
    }

    //审批 通过 / 驳回 事务申请
    @Override
    public int updateTranStatus(Map<String,Integer> map) {
        int row = adminMapper.updateTransactionInfoStatus(map);
        // row == 1 表示通过
        //A  加入到事务总计表中
            //1.1 先查找总计表
                // 1.1.1 如果存在 则 找对应的属性增加
                // 1.1.2 不存在 则创建新对象 添加属性值 后 在插入
        //发送通知邮件到员工邮箱  消息队列
        return row;
    }


    //查看规则
    @Override
    public UtilInfo queryUtilInfo() {
        return adminMapper.queryUtilInfo();
    }

    //修改考勤规则
    @Override
    public int updateUtilInfo(UtilInfo utilInfo) {
        int row = adminMapper.updateUtilInfo(utilInfo);
        return row;
    }

    //查看各个部门下的人数
    @Override
    public List<Map<String, Integer>> queryDeptSumByDeptId() {
        List<Map<String,Integer>> list = adminMapper.queryDeptSumByDeptId();
        return list == null?  new ArrayList<>()  : list;
    }

    //结算工资
    @Override
    public List<SalaryOrderTopic> queryEveryEmpSalary() {
        List<SalaryOrderTopic> salaryOrderTopicList = adminMapper.queryEveryEmpSalary();
        try {
            for(SalaryOrderTopic salary:salaryOrderTopicList){
                 if(salary.getLateMon()!=0 || salary.getExitMon() !=0 || salary.getLeaveMon()!=0){
                     salary.setMarkMon(0);
                 }
                 salary.setSarReal(salary.getSarBasic()+salary.getLeaveMon()+salary.getLateMon()+salary.getExitMon()+(salary.getSarMerits()*salary.getSalMeritsPrecent()/100)
                         +salary.getMarkMon()+salary.getWorkOnTime()+salary.getEatSub()+salary.getTrffSub());
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return new ArrayList<>();
        }
        return salaryOrderTopicList;
    }
}



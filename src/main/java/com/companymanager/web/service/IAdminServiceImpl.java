package com.companymanager.web.service;

import com.companymanager.entity.*;
import com.companymanager.entity.condition.EmployeeCondition;
import com.companymanager.entity.condition.Position;
import com.companymanager.entity.condition.SalaryOrderTopic;
import com.companymanager.web.dao.IAdminMapper;
import com.companymanager.web.dao.IEmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class IAdminServiceImpl implements IAdminService {

    private static final Logger LOG = LoggerFactory.getLogger(IAdminServiceImpl.class);

   @Autowired
   private IAdminMapper adminMapper;

   @Autowired
   private IEmployeeMapper employeeMapper;

    @Override
    public int queryAdmin(Map<String, String> map) {
        return adminMapper.queryAdmin(map);
    }

    @Override
    @Transactional
    public int insertNewCompanyInfo(EmployeeCondition employeeCondition) {
        LOG.info("新增员工信息："+employeeCondition.toString());
        int row1 = employeeMapper.insertNewEmployee(employeeCondition); //新增  employee 表
        int row2 = adminMapper.insertSalary(employeeCondition);  //将新员工插入到 工资表   ：绩效工资  基本工资

        return row1+row2 ;
    }

    @Override
    @Transactional
    public boolean updateEmpInfo(EmployeeCondition emp) {
        //修改基本信息
        int row1 = adminMapper.updateEmployeeInfo(emp);
        //修改工资 绩效
        int row2 = adminMapper.updateEmpSalary(emp);
        return row1==row2;
    }

    @Override
    public List<EmployeeCondition> queryAllEmployee() {
        List<EmployeeCondition > employeeList = adminMapper.queryAllEmployee();
        return employeeList == null?new ArrayList<>(): employeeList;
    }

    @Override
    public List<EmployeeCondition> highSearch(Map<String, String> map) {
        List<EmployeeCondition > highSearchList = adminMapper.highSearch(map);
        return highSearchList == null?new ArrayList<>(): highSearchList;
    }

    //审批注册员工
        //1.查看未审批的员工
//    @Override
//    public List<Employee> queryNoAccessEmp() {
//        List<Employee> list = adminMapper.queryNoAccessEmp();
//
//        return list == null ? new ArrayList<>(): list;
//    }
//        //2.修改状态
//    @Override
//    @Transactional
//    public boolean updateEmployeeStatus(Map<String, String> map) {
//       int row1 =  adminMapper.updateEmployeeStatus(map); //修改状态
//
//
//        return (row1 == 1);
//    }

    //查看事务  未审批 已审批
    @Override
    public List<TransactionInfo> queryTransactionInfo(Map<String,String> map) {
        List<TransactionInfo> list = adminMapper.queryTransactionInfo(map);
        return list == null ? new ArrayList<>():list;
    }

    //审批 通过 / 驳回 事务申请
    @Override
    @Transactional
    public boolean updateTranStatus(Map<String,String> map) {
        int insertRow = 0,updateRow = 0;
        //修改状态
        int row = adminMapper.updateTransactionInfoStatus(map);
        LOG.info("修改事务影响行数："+row);
        // row == 1 表示通过
        //A  通过则 加入到事务总计表中
            //1.1 通过empId先查找总计表
        TransactionInfoSum transactionInfoSum = adminMapper.queryTransInfoRecord(map);
        if(transactionInfoSum == null){    // 1.1.2 不存在 则创建新对象 添加属性值 后 在插入

            transactionInfoSum = new TransactionInfoSum();
            transactionInfoSum.setEmpId(map.get("empId"));
            transactionInfoSum.setRecordTime(Date.valueOf(map.get("tranTime")));
            transactionInfoSum.setDepId(Integer.parseInt(map.get("depId")));
            LOG.info("总计表中无记录 新建对象"+transactionInfoSum);
                //插入新记录
             insertRow = adminMapper.insertTransInfoRecord(transactionInfoSum);
        }

        if (map.get("transDemo").equals("请假")){
            transactionInfoSum.setLeaveSum(transactionInfoSum.getLeaveSum()+Double.parseDouble(map.get("transTime")));
        }else{
            transactionInfoSum.setWorkOvertime(transactionInfoSum.getWorkOvertime()+Double.parseDouble(map.get("transTime")));
        }
            //修改新记录或者 原纪录的属性次数
             updateRow =adminMapper.updateTransInfoRecord(transactionInfoSum,map.get("tranTime"));
        if(row == 1 && (insertRow == 1 || updateRow ==1)){
            //发送通知邮件到员工邮箱  消息队列
            LOG.info("发送邮件消息。。。。。");
            return true;
        }

        return false;
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
    //查找职位 高级搜索
    @Override
    public List<Position> queryPositionInfo(Map<String, String> map) {
        List<Position> list = adminMapper.queryPositionInfo(map);
        return list == null ? new ArrayList<>() :list;
    }

    //新增职位
    @Override
    public boolean insertNewDept(DeptInfo deptInfo) {
        if(deptInfo!=null && deptInfo.getPosId()!=0 && deptInfo.getPosName() != null ){
            int row = adminMapper.insertNewPosition(deptInfo);
            return row == 1;
        }
        int row2 = adminMapper.insertNewDept(deptInfo);
        return 1 == row2;
    }
    //  删除部门 递归删除下边的职位
    @Override
    public boolean deleteDeptById(int depId) {
        int row = adminMapper.deleteDeptById(depId);
        LOG.info("row: "+row);
        return 1 == row;
    }
    //仅删除职位
    @Override
    public boolean deletePositionById(int posId) {
        return 1 == adminMapper.deletePositionById(posId);
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

    //保存工资

    @Override
    public int saveSalaryRecord(List<SalaryOrderTopic> list) {
        return adminMapper.saveSalaryRecord(list);
    }
}



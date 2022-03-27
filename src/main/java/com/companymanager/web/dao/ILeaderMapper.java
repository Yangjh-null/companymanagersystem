package com.companymanager.web.dao;

import com.companymanager.entity.SalaryInfo;
import com.companymanager.entity.TransactionInfo;
import com.companymanager.entity.TransactionInfoSum;
import com.companymanager.entity.condition.TransInfoSumCondition;
import com.companymanager.web.dao.sqlcondition.SQLProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ILeaderMapper {

    @SelectProvider(value = SQLProvider.class,method = "queryTransactionInfoSumByTime")
    List<TransInfoSumCondition> queryCheckOnTimeByDeptId(Map<String,String>map);

    @Update("update salary_info set salMeritsPrecent = #{salMeritsPrecent}")
    int updateMeritsByEmpId(SalaryInfo salaryInfo);

}

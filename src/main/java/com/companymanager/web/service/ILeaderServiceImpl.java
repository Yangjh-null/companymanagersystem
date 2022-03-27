package com.companymanager.web.service;

import com.companymanager.entity.SalaryInfo;
import com.companymanager.entity.TransactionInfo;
import com.companymanager.entity.TransactionInfoSum;
import com.companymanager.entity.condition.TransInfoSumCondition;
import com.companymanager.web.dao.ILeaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ILeaderServiceImpl implements ILeaderService {

    @Autowired
    private ILeaderMapper leaderMapper;

    //按照部门查看考勤记录
    @Override
    public List<TransInfoSumCondition> queryCheckOnTimeByDeptId(Map<String,String> map) {
        List<TransInfoSumCondition> list = leaderMapper.queryCheckOnTimeByDeptId(map);
        return list == null?new ArrayList<>() :list;
    }

    //设置当月绩效
    @Override
    public int updateMeritsByEmpId(SalaryInfo salaryInfo) {
        int row = leaderMapper.updateMeritsByEmpId(salaryInfo);
        return row;
    }
}

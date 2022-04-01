package com.companymanager.web.service;

import com.companymanager.entity.condition.EchartsCondition;

import java.util.List;
import java.util.Map;

public interface UtilService {

    List<Map<Integer,String>> queryDeptInfo();

    List<String> queryPositionByDept(int deptId);

    String queryDepNameByDepId(int depId);

    List<EchartsCondition> queryNumberSumByDeptName();
}

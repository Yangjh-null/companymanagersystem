package com.companymanager.web.service;

import com.companymanager.web.dao.IUtilMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UtilServiceImpl implements UtilService {

    @Autowired
    private IUtilMapper utilMapper;

    @Override
    public List<Map<Integer,String>> queryDeptInfo() {
        List<Map<Integer,String>> deptList = utilMapper.queryDeptInfo();
        return deptList == null? new ArrayList<>() : deptList;
    }

    //根据部门查看该部门职位名称
    @Override
    public List<String> queryPositionByDept(int deptId) {
        List<String> posList = utilMapper.queryPositionByDept(deptId);
        return posList == null ?new ArrayList<>():posList;
    }

    ////根据部门id 查看当前部门的名称
    @Override
    public String queryDepNameByDepId(int depId) {
        return utilMapper.queryDepNameByDepId(depId);
    }
}

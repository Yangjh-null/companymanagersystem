package com.companymanager.web.controller;

import com.companymanager.entity.UtilInfo;
import com.companymanager.entity.condition.EchartsCondition;
import com.companymanager.entity.condition.EchartsConditionToOne;
import com.companymanager.web.service.UtilService;
import com.companymanager.z_resultpackage.Result;
import com.companymanager.z_resultpackage.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("util")
public class UtilController {

    @Autowired
    private UtilService utilService;

    @RequestMapping("queryDept")
    public Result queryDeptInfo(){
        List<Map<Integer,String>> list = utilService.queryDeptInfo();
        return Result.successAndData(ResultStatus.SUCCESS,list);
    }

    @RequestMapping("queryPos")
    public Result queryPosByDeptId(@RequestBody Map<String,Integer> map ){
        int depId = map.get("depId");
        List<String> list = utilService.queryPositionByDept(depId);

        return Result.successAndData(ResultStatus.SUCCESS,list);
    }

    @RequestMapping("queryDepName")
    public Result queryDeptNameByDeptId(@RequestBody  Map<String,Integer> map){
        int depId = map.get("depId");
        String DepName = utilService.queryDepNameByDepId(depId);
          String res =  (DepName == null) ? "": DepName;
        return Result.successAndData(ResultStatus.SUCCESS,DepName);
    }
    //查看 每个部门下的总人数
    @RequestMapping("querySumByDept")
    public Result queryNumberSumByDeptName(){
        List<EchartsCondition> list = utilService.queryNumberSumByDeptName();
        return Result.successAndData(ResultStatus.SUCCESS,list);

    }

    //查看 各个职位的人数
    @RequestMapping("queryNumByPos")
    public  Result queryNumByPosition(){
        List<EchartsConditionToOne> list = utilService.queryPositionNum();
        return Result.successAndData(ResultStatus.SUCCESS,list);
    }

}

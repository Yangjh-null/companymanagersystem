package com.companymanager.web.dao.sqlcondition;

import java.util.Map;

public class SQLProvider {

    //查看考勤 按照月份年份时间
    public String queryTransactionInfoSumByTime(Map<String,String> map){
        StringBuilder sql = new StringBuilder("select trans.*, emp_name from transaction_info_sum as trans "+
              "  left join employee_info as emp on emp.emp_id = trans.emp_id WHERE 1=1 ");
        if(map.get("month") != null && !"".equals(map.get("month"))){
            sql.append(" AND MONTH(record_time) =  "+map.get("month"));
        }
        if(map.get("year") != null && !"".equals(map.get("year"))){
            sql.append(" AND year(record_time) = "+map.get("year"));
        }
        if(map.get("empId") != null && !"".equals(map.get("empId"))){ //员工个人查询
            sql.append(" AND emp.emp_id = '"+map.get("empId")+"' ");
        }
        if(map.get("depId") != null && !"".equals(map.get("depId"))) {  //主管按部门查询
            sql.append(" AND dep_id = '"+map.get("depId")+"' ");
        }
        sql.append(" order by record_time");

        return sql.toString();
    }

    //hr查看申请的事务sql
    public String queryTransactionInfo(Map<String,String> map){
        StringBuilder sql = new StringBuilder("SELECT trans.*,emp_name FROM player_manage_system.transaction_info trans\n" +
                "left join employee_info as emp on emp.emp_id = trans.emp_id WHERE 1=1 ");
        if(map.get("status") != null && !"".equals(map.get("status"))) {  //选择状态值  审批 未审批
            sql.append(" AND status_access = "+map.get("status"));
        }
        if(map.get("depId") != null && !"".equals(map.get("depId"))) {  //选择部门
            sql.append(" AND dep_id = "+map.get("depId"));
        }
        if(map.get("empId")  != null && !"".equals(map.get("empId"))){
            sql.append(" AND emp.emp_id = "+"'"+map.get("empId")+"'");
        }

        sql.append(" order by tran_time  ");
        return sql.toString();
    }
    //结算工资sql
    public String querySalary(){
        String sql = "select trans.emp_id,ee.emp_name,sar_basic,sar_merits ,sal_merits_precent,Round((sal_merits_precent/100)*sar_merits) as sal_real_merits,trans.late_sum*util.util_late_money as late_mon ,trans.exit_sum*util.util_exit_money as exit_mon ,\n" +
                "trans.leave_sum*util.util_hoilday_money as leave_mon,trans.work_overtime*util.util_overtime_money as work_on_time,util.util_eat_sub as eat_sub ,util.util_traffic_sub as trff_sub,util.util_mark as mark_mon,\n" +
                "sar_merits+sar_basic+util_eat_sub+util_traffic_sub+util_mark as  sar_all ,left(record_time,7) as record_time\n" +
                "from (select * from  transaction_info_sum  where year(record_time) = year(now()) AND month(record_time)  = month(now())) \n" +
                "as trans,util_info as util ,salary_info,employee_info as ee\n" +
                "where trans.emp_id = salary_info.emp_id AND salary_info.emp_id = ee.emp_id;";
        return sql;
    }

}

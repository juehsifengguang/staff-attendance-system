package com.as.service;

import java.util.HashMap;
import java.util.List;

import com.as.entity.Overtimeapplication;

public interface OvertimeapplicationService {
	//通过记录id查找加班申请记录
	public Overtimeapplication findOvertimeApplyByOaid(Integer oa_id);
	
	//修改加班申请记录
	public void updateOvertimeApplyByOaid(HashMap<String, Object> updateMap);
	
	//新增加班申请记录
	public int insertOvertimeApply(HashMap<String, Object> insertMap);
	
	//查询某人的所有记录
	public List<Overtimeapplication> selectAllOvertimeApplyBySid(Integer s_id);
	
	//删除某条记录
	public void deleteOvertimeApplyByOaid(Integer oa_id);
}
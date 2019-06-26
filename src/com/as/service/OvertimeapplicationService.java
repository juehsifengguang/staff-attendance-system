package com.as.service;

import java.util.HashMap;
import java.util.List;

import com.as.entity.Overtimeapplication;

public interface OvertimeapplicationService {
	//ͨ����¼id���ҼӰ������¼
	public Overtimeapplication findOvertimeApplyByOaid(Integer oa_id);
	
	//�޸ļӰ������¼
	public void updateOvertimeApplyByOaid(HashMap<String, Object> updateMap);
	
	//�����Ӱ������¼
	public int insertOvertimeApply(HashMap<String, Object> insertMap);
	
	//��ѯĳ�˵����м�¼
	public List<Overtimeapplication> selectAllOvertimeApplyBySid(Integer s_id);
	
	//ɾ��ĳ����¼
	public void deleteOvertimeApplyByOaid(Integer oa_id);
}
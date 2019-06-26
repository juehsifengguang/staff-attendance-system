package com.as.mapping;

import java.util.HashMap;
import java.util.List;

import com.as.entity.Overtimeapplication;

public interface OvertimeapplicationDao {

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
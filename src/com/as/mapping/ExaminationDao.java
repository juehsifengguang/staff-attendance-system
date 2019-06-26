package com.as.mapping;

import java.util.HashMap;
import java.util.List;
import com.as.entity.Examination;
public interface ExaminationDao {
	//ͨ��id���ҿ��ڱ�
	public  Examination findExaminationById(Integer e_id);

	
	//��д����������ز���
	public void insertExamination(HashMap<String, Object> examinationMap);
	
	//��ѯ�����������
	public List<Examination> selectAllExamination();
	
	//ɾ���������
	public void deleteExaminationById(Integer e_id);

}

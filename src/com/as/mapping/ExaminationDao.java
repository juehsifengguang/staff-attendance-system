package com.as.mapping;

import java.util.HashMap;
import java.util.List;
import com.as.entity.Examination;
public interface ExaminationDao {
	//通过id查找考勤表
	public  Examination findExaminationById(Integer e_id);

	
	//填写审批意见返回参数
	public void insertExamination(HashMap<String, Object> examinationMap);
	
	//查询所有审批意见
	public List<Examination> selectAllExamination();
	
	//删除审批意见
	public void deleteExaminationById(Integer e_id);

}

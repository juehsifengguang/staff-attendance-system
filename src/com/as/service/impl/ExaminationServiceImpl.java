package com.as.service.impl;

import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.as.entity.Examination;
import com.as.mapping.ExaminationDao;
import com.as.service.ExaminationService;

public class ExaminationServiceImpl implements  ExaminationService {
	
	public static SqlSession getSession() 
	{
	try {
		// 构建器
		SqlSessionFactoryBuilder sqlSessionBuilder = new SqlSessionFactoryBuilder();
		// 读取配置文件
		InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
		// 获取会话工厂
		SqlSessionFactory sqlFactory = sqlSessionBuilder.build(inputStream);
		// 获取会话
		SqlSession session = sqlFactory.openSession();
		return session;
		}catch (Exception e) {
			e.printStackTrace();
			return null;}
	}

	//通过id查找审批意见表
	public  Examination findExaminationById(Integer e_id) {
		try {
			SqlSession session = getSession();
			ExaminationDao examDao = session.getMapper(ExaminationDao.class);
			Examination examination = examDao.findExaminationById(e_id);
			System.out.println(examination.getE_comment());
			return examination;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;}
	}
	

			
	//新增审批意见
	public void insertExamination(HashMap<String, Object> examinationMap) {
		try {
			SqlSession session = getSession();
			ExaminationDao examDao = session.getMapper(ExaminationDao.class);
			//获取参数
			Integer is_overTime = 0;
			if(examinationMap.get("is_overTime")!=null) 
			{
				 is_overTime = Integer.parseInt(examinationMap.get(" is_overTime").toString());
			}
			String e_comment = "不同意";
			if(examinationMap.get("e_comment") != null){
				e_comment = (String) examinationMap.get("e_comment");
			}
			Integer record_id = 1;
			if(examinationMap.get("record_id")!=null) 
			{
				record_id = Integer.parseInt(examinationMap.get("record_id").toString());
			}
								
			HashMap<String, Object> insertExamMap = new HashMap<String, Object>();		
			insertExamMap.put("e_comment", e_comment);
			insertExamMap.put("is_overTime", is_overTime);
			insertExamMap.put("record_id", record_id);
			//调用新增审批意见表函数
			examDao.insertExamination(insertExamMap);
			session.commit();
			session.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			return;}
	}
			
	//查询所有审批意见
	public  List<Examination> selectAllExamination(){
		try {
			SqlSession session = getSession();
			ExaminationDao examDao = session.getMapper(ExaminationDao.class);
			List<Examination> examinationList = examDao.selectAllExamination();		
			
			session.commit();		
			session.close();
			
			return examinationList;
		}catch (Exception e) {
			e.printStackTrace();
			return null;}
	}
			
	//删除审批意见
	public void deleteExaminationById(Integer e_id) {
		try {
			SqlSession session = getSession();
			ExaminationDao examDao = session.getMapper(ExaminationDao.class);
			//调用删除函数
			examDao.deleteExaminationById(e_id);
			session.commit();
			session.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			return;}
	}

}

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
		// ������
		SqlSessionFactoryBuilder sqlSessionBuilder = new SqlSessionFactoryBuilder();
		// ��ȡ�����ļ�
		InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
		// ��ȡ�Ự����
		SqlSessionFactory sqlFactory = sqlSessionBuilder.build(inputStream);
		// ��ȡ�Ự
		SqlSession session = sqlFactory.openSession();
		return session;
		}catch (Exception e) {
			e.printStackTrace();
			return null;}
	}

	//ͨ��id�������������
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
	

			
	//�����������
	public void insertExamination(HashMap<String, Object> examinationMap) {
		try {
			SqlSession session = getSession();
			ExaminationDao examDao = session.getMapper(ExaminationDao.class);
			//��ȡ����
			Integer is_overTime = 0;
			if(examinationMap.get("is_overTime")!=null) 
			{
				 is_overTime = Integer.parseInt(examinationMap.get(" is_overTime").toString());
			}
			String e_comment = "��ͬ��";
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
			//�������������������
			examDao.insertExamination(insertExamMap);
			session.commit();
			session.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			return;}
	}
			
	//��ѯ�����������
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
			
	//ɾ���������
	public void deleteExaminationById(Integer e_id) {
		try {
			SqlSession session = getSession();
			ExaminationDao examDao = session.getMapper(ExaminationDao.class);
			//����ɾ������
			examDao.deleteExaminationById(e_id);
			session.commit();
			session.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			return;}
	}

}

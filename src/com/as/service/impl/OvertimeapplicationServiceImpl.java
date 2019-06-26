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

import com.as.entity.Overtimeapplication;
import com.as.mapping.OvertimeapplicationDao;
import com.as.service.OvertimeapplicationService;

public class OvertimeapplicationServiceImpl implements OvertimeapplicationService {
	
	//���Session�Ự
	public static SqlSession getSession(){
		try {
			//������
			SqlSessionFactoryBuilder sqlSessionBuilder = new SqlSessionFactoryBuilder();
			//��ȡ�����ļ�
			InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
			//��ȡ�Ự����
			SqlSessionFactory sqlFactory = sqlSessionBuilder.build(inputStream);
			//��ȡsession
			SqlSession session = sqlFactory.openSession();
			
			return session;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Overtimeapplication findOvertimeApplyByOaid(Integer oa_id) {
		try {
			//���session
			SqlSession session = getSession();
			//��ȡ�������
			OvertimeapplicationDao overtimeDao = session.getMapper(OvertimeapplicationDao.class);

			//���ҷ���
			Overtimeapplication overtimeApply = overtimeDao.findOvertimeApplyByOaid(oa_id);

			session.close();
			
			return overtimeApply;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateOvertimeApplyByOaid(HashMap<String, Object> updateMap) {
		try {
			//���session
			SqlSession session = getSession();
			//��ȡ�������
			OvertimeapplicationDao overtimeDao = session.getMapper(OvertimeapplicationDao.class);

			//��ʽת��
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			//���·���
			HashMap<String, Object> updateMapReal = new HashMap<String, Object>();
			
			//��ò���
			Integer oaId = 0;
			if(updateMap.get("oa_id") != null){
				oaId = Integer.parseInt((String) updateMap.get("oa_id"));
			}
//			Integer sId = 0;
//			if(updateMap.get("s_id") != null){
//				sId = (Integer) updateMap.get("s_id");
//			}
			String startTime = "2019-01-01 17:00:00";
			if(updateMap.get("overtime_start") != null){
				startTime = (String)updateMap.get("overtime_start");
				Timestamp sqlStart = new Timestamp(sdf.parse(startTime).getTime());
				updateMapReal.put("overtime_start", sqlStart);
			}
			String endTime = "2019-01-01 18:00:00";
			if(updateMap.get("overtime_end") != null){
				endTime = (String) updateMap.get("overtime_end");
				Timestamp sqlEnd = new Timestamp(sdf.parse(endTime).getTime());
				updateMapReal.put("overtime_end", sqlEnd);
			}
			String oa_reason = "��";
			if(updateMap.get("overtime_reason") != null){
				oa_reason = (String) updateMap.get("overtime_reason");
				updateMapReal.put("overtime_reason",oa_reason);
			}
			Integer isApproved = 0;
			if(updateMap.get("is_approved") != null){
				isApproved = Integer.parseInt((String) updateMap.get("is_approved"));
				updateMapReal.put("is_approved", isApproved);
			}
			
			updateMapReal.put("oa_id", oaId);
			//updateMapReal.put("s_id", sId);
			
			//���ø��·���
			overtimeDao.updateOvertimeApplyByOaid(updateMapReal);
			
			//�ֶ��ύsession
			session.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insertOvertimeApply(HashMap<String, Object> insertMap) {
		try {
			//���session
			SqlSession session = getSession();
			//��ȡ�������
			OvertimeapplicationDao overtimeDao = session.getMapper(OvertimeapplicationDao.class);

			//��ʽת��
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			//���·���
			HashMap<String, Object> insertMapReal = new HashMap<String, Object>();
			
			//��ò���
			Integer sId = 1;
			if(insertMap.get("s_id") != null){
				sId = (Integer) insertMap.get("s_id");
			}
			insertMapReal.put("s_id", sId);
			//��ʼʱ��
			String startTime = "2019-01-01 17:00:00";
			if(insertMap.get("overtime_start") != null){
				startTime = (String)insertMap.get("overtime_start");
			}
			Timestamp sqlStart = new Timestamp(sdf.parse(startTime).getTime());
			insertMapReal.put("overtime_start", sqlStart);
			//����ʱ��
			String endTime = "2019-01-01 18:00:00";
			if(insertMap.get("overtime_end") != null){
				endTime = (String) insertMap.get("overtime_end");
			}
			Timestamp sqlEnd = new Timestamp(sdf.parse(endTime).getTime());
			insertMapReal.put("overtime_end", sqlEnd);
			//�Ӱ���������
			String oa_reason = "��";
			if(insertMap.get("overtime_reason") != null){
				oa_reason = (String) insertMap.get("overtime_reason");
			}
			insertMapReal.put("overtime_reason",oa_reason);
			//�Ƿ�����
			Integer isApproved = 0;
			if(insertMap.get("is_approved") != null){
				isApproved = (Integer) insertMap.get("is_approved");
			}
			insertMapReal.put("is_approved", isApproved);
			
			
			//������������
			overtimeDao.insertOvertimeApply(insertMapReal);
			
			//�ֶ��ύsession
			session.commit();
			session.close();
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Overtimeapplication> selectAllOvertimeApplyBySid(Integer s_id){
		try {
			//���session
			SqlSession session = getSession();
			//��ȡ�������
			OvertimeapplicationDao overtimeDao = session.getMapper(OvertimeapplicationDao.class);

			//���ҷ���
			List<Overtimeapplication> overtimeList = overtimeDao.selectAllOvertimeApplyBySid(s_id);
			
			session.close();
			
			return overtimeList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteOvertimeApplyByOaid(Integer oa_id) {
		try {
			//���session
			SqlSession session = getSession();
			//��ȡ�������
			OvertimeapplicationDao overtimeDao = session.getMapper(OvertimeapplicationDao.class);
	
			//ɾ������
			overtimeDao.deleteOvertimeApplyByOaid(oa_id);
			
			//�ֶ��ύsession
			session.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
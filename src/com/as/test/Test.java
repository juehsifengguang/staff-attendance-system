package com.as.test;

import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.as.entity.Department;
import com.as.entity.Examination;
import com.as.entity.Staff;
import com.as.mapping.DepartmentDao;
import com.as.mapping.ExaminationDao;
import com.as.mapping.StaffDao;

public class Test {
	
	public static void test(){
		try {
			// ������
			SqlSessionFactoryBuilder sqlSessionBuilder = new SqlSessionFactoryBuilder();
			// ��ȡ�����ļ�
			InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
			// ��ȡ�Ự����
			SqlSessionFactory sqlFactory = sqlSessionBuilder.build(inputStream);
			// ��ȡ�Ự
			SqlSession session = sqlFactory.openSession();
			
			// ��ȡ�������
			
			//1.���Բ��Ҳ���
			DepartmentDao depDao = session.getMapper(DepartmentDao.class);
			Department department = depDao.findDepById(1001);
			System.out.println(department.getDep_name());
		
			//2.���Բ���Ա��
			StaffDao staffDao = session.getMapper(StaffDao.class);
			Staff staff = staffDao.findStaffById(1);
			System.out.println(staff.getS_name());
			//��������Ա��		
			List<Staff> staffList = staffDao.selectAllStaff();
				for (Staff staff1 : staffList) {
						System.out.println("Ա��id��"+staff1.getS_id()+"    Ա������:"+staff1.getS_name()+
								"     ����id��"+staff1.getDep_id()+"   ���:"+staff1.getIdentity()+"   ��ʼ���룺"+staff1.getS_pass()+"    ��ְʱ�䣺"+staff1.getEntry_time());
					}
			
			//3.���Ը���Ա����Ϣ����
			HashMap<String, Object> updateStaffMap = new HashMap<String,Object>();
			updateStaffMap.put("s_id", 2);
			String s_name1 = "Alice";
			updateStaffMap.put("s_name", s_name1);
			
			//4.����������ʾ
			//��ʽת��
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentDate = "2019-04-01 14:30:00";
			Timestamp sqlDate = new Timestamp(sdf.parse(currentDate).getTime());	
			updateStaffMap.put("entry_time", sqlDate);
			//���ø���Ա����Ϣ����
			staffDao.updateStaffInfoReturnId(updateStaffMap);
			
			//5.��������Ա������
			HashMap<String, Object> insertStaffMap = new HashMap<String, Object>();
			Integer s_id = 1008;
			insertStaffMap.put("s_id", s_id);
			String s_name2 = "janice";
			insertStaffMap.put("s_name", s_name2);
			String entry_time1 = "2011-02-03 12:00:00";
			Timestamp entry_time = new Timestamp(sdf.parse(entry_time1).getTime());
			insertStaffMap.put("entry_time", entry_time);
			Integer dep_id = 1001;
			insertStaffMap.put("dep_id", dep_id);
			Integer identity = 1;
			insertStaffMap.put("identity", identity);
			String s_pass = "123456";
			insertStaffMap.put("s_pass", s_pass);
			//��������Ա������
			staffDao.insertNewStaffInfoReturnId(insertStaffMap);
			
			//6.����ɾ��ĳλԱ����Ϣ
			staffDao.deleteStaffInfoReturnId(1004);
			
			//7..���Բ������������
			ExaminationDao examDao = session.getMapper(ExaminationDao.class);
			Examination examination = examDao.findExaminationById(1);
			System.out.println(examination.getE_comment());
			
			//8.�����������������
			HashMap<String, Object> insertExamMap = new HashMap<String, Object>();
			String e_comment = "��ͬ��";
			insertExamMap.put("e_comment", e_comment);
			Integer is_overTime =0;			;
			insertExamMap.put("is_overTime", is_overTime);
			Integer record_id = 258;
			insertExamMap.put("record_id", record_id);
			//�������������������
			examDao.insertExamination(insertExamMap);
			
			//ɾ��ĳ�����������¼
			examDao.deleteExaminationById(1003);
			//���������������		
			List<Examination> examinationList = examDao.selectAllExamination();
				for (Examination exam : examinationList) {
						System.out.println("�����id��"+exam.getE_id()+"       comment:"+exam.getE_comment()+"    ���/�Ӱࣿ��"+exam.getIs_overtime()+"    ��¼id:"+exam.getRecord_id());
					}
																
			//�ֶ��ύ����
			session.commit();
			session.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		test();

	}
}

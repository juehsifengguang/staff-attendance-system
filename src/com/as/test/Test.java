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
			// 构建器
			SqlSessionFactoryBuilder sqlSessionBuilder = new SqlSessionFactoryBuilder();
			// 读取配置文件
			InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
			// 获取会话工厂
			SqlSessionFactory sqlFactory = sqlSessionBuilder.build(inputStream);
			// 获取会话
			SqlSession session = sqlFactory.openSession();
			
			// 获取代理对象
			
			//1.测试查找部门
			DepartmentDao depDao = session.getMapper(DepartmentDao.class);
			Department department = depDao.findDepById(1001);
			System.out.println(department.getDep_name());
		
			//2.测试查找员工
			StaffDao staffDao = session.getMapper(StaffDao.class);
			Staff staff = staffDao.findStaffById(1);
			System.out.println(staff.getS_name());
			//查找所有员工		
			List<Staff> staffList = staffDao.selectAllStaff();
				for (Staff staff1 : staffList) {
						System.out.println("员工id："+staff1.getS_id()+"    员工姓名:"+staff1.getS_name()+
								"     部门id："+staff1.getDep_id()+"   身份:"+staff1.getIdentity()+"   初始密码："+staff1.getS_pass()+"    入职时间："+staff1.getEntry_time());
					}
			
			//3.测试更新员工信息方法
			HashMap<String, Object> updateStaffMap = new HashMap<String,Object>();
			updateStaffMap.put("s_id", 2);
			String s_name1 = "Alice";
			updateStaffMap.put("s_name", s_name1);
			
			//4.测试日期显示
			//格式转换
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentDate = "2019-04-01 14:30:00";
			Timestamp sqlDate = new Timestamp(sdf.parse(currentDate).getTime());	
			updateStaffMap.put("entry_time", sqlDate);
			//调用更新员工信息函数
			staffDao.updateStaffInfoReturnId(updateStaffMap);
			
			//5.测试新增员工方法
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
			//调用新增员工函数
			staffDao.insertNewStaffInfoReturnId(insertStaffMap);
			
			//6.测试删除某位员工信息
			staffDao.deleteStaffInfoReturnId(1004);
			
			//7..测试查找审批意见表
			ExaminationDao examDao = session.getMapper(ExaminationDao.class);
			Examination examination = examDao.findExaminationById(1);
			System.out.println(examination.getE_comment());
			
			//8.测试新增审批意见表
			HashMap<String, Object> insertExamMap = new HashMap<String, Object>();
			String e_comment = "不同意";
			insertExamMap.put("e_comment", e_comment);
			Integer is_overTime =0;			;
			insertExamMap.put("is_overTime", is_overTime);
			Integer record_id = 258;
			insertExamMap.put("record_id", record_id);
			//调用新增审批意见表函数
			examDao.insertExamination(insertExamMap);
			
			//删除某项审批意见记录
			examDao.deleteExaminationById(1003);
			//查找所有审批意见		
			List<Examination> examinationList = examDao.selectAllExamination();
				for (Examination exam : examinationList) {
						System.out.println("意见表id："+exam.getE_id()+"       comment:"+exam.getE_comment()+"    请假/加班？："+exam.getIs_overtime()+"    记录id:"+exam.getRecord_id());
					}
																
			//手动提交事务
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

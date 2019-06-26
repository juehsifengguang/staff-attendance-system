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
	
	//获得Session会话
	public static SqlSession getSession(){
		try {
			//构建器
			SqlSessionFactoryBuilder sqlSessionBuilder = new SqlSessionFactoryBuilder();
			//读取配置文件
			InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
			//获取会话工厂
			SqlSessionFactory sqlFactory = sqlSessionBuilder.build(inputStream);
			//获取session
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
			//获得session
			SqlSession session = getSession();
			//获取代理对象
			OvertimeapplicationDao overtimeDao = session.getMapper(OvertimeapplicationDao.class);

			//查找方法
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
			//获得session
			SqlSession session = getSession();
			//获取代理对象
			OvertimeapplicationDao overtimeDao = session.getMapper(OvertimeapplicationDao.class);

			//格式转换
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			//更新方法
			HashMap<String, Object> updateMapReal = new HashMap<String, Object>();
			
			//获得参数
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
			String oa_reason = "无";
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
			
			//调用更新方法
			overtimeDao.updateOvertimeApplyByOaid(updateMapReal);
			
			//手动提交session
			session.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insertOvertimeApply(HashMap<String, Object> insertMap) {
		try {
			//获得session
			SqlSession session = getSession();
			//获取代理对象
			OvertimeapplicationDao overtimeDao = session.getMapper(OvertimeapplicationDao.class);

			//格式转换
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			//更新方法
			HashMap<String, Object> insertMapReal = new HashMap<String, Object>();
			
			//获得参数
			Integer sId = 1;
			if(insertMap.get("s_id") != null){
				sId = (Integer) insertMap.get("s_id");
			}
			insertMapReal.put("s_id", sId);
			//开始时间
			String startTime = "2019-01-01 17:00:00";
			if(insertMap.get("overtime_start") != null){
				startTime = (String)insertMap.get("overtime_start");
			}
			Timestamp sqlStart = new Timestamp(sdf.parse(startTime).getTime());
			insertMapReal.put("overtime_start", sqlStart);
			//结束时间
			String endTime = "2019-01-01 18:00:00";
			if(insertMap.get("overtime_end") != null){
				endTime = (String) insertMap.get("overtime_end");
			}
			Timestamp sqlEnd = new Timestamp(sdf.parse(endTime).getTime());
			insertMapReal.put("overtime_end", sqlEnd);
			//加班申请理由
			String oa_reason = "无";
			if(insertMap.get("overtime_reason") != null){
				oa_reason = (String) insertMap.get("overtime_reason");
			}
			insertMapReal.put("overtime_reason",oa_reason);
			//是否被审批
			Integer isApproved = 0;
			if(insertMap.get("is_approved") != null){
				isApproved = (Integer) insertMap.get("is_approved");
			}
			insertMapReal.put("is_approved", isApproved);
			
			
			//调用新增方法
			overtimeDao.insertOvertimeApply(insertMapReal);
			
			//手动提交session
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
			//获得session
			SqlSession session = getSession();
			//获取代理对象
			OvertimeapplicationDao overtimeDao = session.getMapper(OvertimeapplicationDao.class);

			//查找方法
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
			//获得session
			SqlSession session = getSession();
			//获取代理对象
			OvertimeapplicationDao overtimeDao = session.getMapper(OvertimeapplicationDao.class);
	
			//删除方法
			overtimeDao.deleteOvertimeApplyByOaid(oa_id);
			
			//手动提交session
			session.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
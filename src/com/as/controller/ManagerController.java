package com.as.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.as.entity.Examination;
import com.as.entity.Staff;
import com.as.service.ExaminationService;
import com.as.service.StaffService;
import com.as.service.TemporaryovertimeService;
import com.as.service.impl.ExaminationServiceImpl;
import com.as.service.impl.StaffServiceImpl;
import com.as.service.impl.TemporaryovertimeServiceImpl;

@Controller
@RequestMapping("/manager")

public class ManagerController {
	private StaffService staffService = new StaffServiceImpl();
	private ExaminationService  examService = new ExaminationServiceImpl(); 
	private TemporaryovertimeService tmpOvertimeService = new TemporaryovertimeServiceImpl();
	
	@RequestMapping("/insertNewStaffInfo")
	public String insertStaffInfo(HttpServletRequest request, Integer s_id) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		//获得员工信息相关页面传来的参数
		String getId = request.getParameter("s_id");
		String getS_name = request.getParameter("s_name");
		String getEntry_time = request.getParameter("entry_time");
		String getDep_id = request.getParameter("dep_id");
		String getIdentity = request.getParameter("identity");
		String getS_pass = request.getParameter("s_pass");
		
		//把参数放到Map中
		HashMap<String, Object> staffMap = new HashMap<String, Object>();
		staffMap.put("s_id",getId);
		staffMap.put("s_name", getS_name);
		staffMap.put("entry_time", getEntry_time);
		staffMap.put("dep_id", getDep_id);
		staffMap.put("identity", getIdentity);
		staffMap.put("s_pass", getS_pass);
		
		//调用新增的方法
		staffService.insertNewStaffInfoReturnId(staffMap);
		return "home";
	}
	
	@RequestMapping("/deleteStaffInfo")
	public String deleteStaffInfo(HttpServletRequest request, Integer s_id) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		//获得员工信息相关页面传来的参数
		String getId = request.getParameter("s_id");
		
		//调用删除的方法
		Integer s_id1 = Integer.parseInt(getId);
		staffService.deleteStaffInfoReturnId(s_id1);
		return "home";
	}
	
	@RequestMapping("/managerUpdateStaffInfo")
	public String updateStaffInfo(HttpServletRequest request, Integer s_id) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		//获得员工信息相关页面传来的参数
		String getId = request.getParameter("s_id");
		String getS_name = request.getParameter("s_name");
		String getEntry_time = request.getParameter("entry_time");
		String getDep_id = request.getParameter("dep_id");
		String getIdentity = request.getParameter("identity");
		String getS_pass = request.getParameter("s_pass");
		
		//把参数放到Map中
		HashMap<String, Object> staffMap = new HashMap<String, Object>();
		staffMap.put("s_id",getId);
		staffMap.put("s_name", getS_name);
		staffMap.put("entry_time", getEntry_time);
		staffMap.put("dep_id", getDep_id);
		staffMap.put("identity", getIdentity);
		staffMap.put("s_pass", getS_pass);
		
		//调用新增的方法
		staffService.updateStaffInfoReturnId(staffMap);
		return "home";
	}
	
	@RequestMapping("/managerSelectStaffInfo")
	public String selectAllStaff(HttpServletRequest request, Integer s_id) throws ServletException, IOException{
			request.setCharacterEncoding("UTF-8");
		
	        List<Staff> staffList = staffService.selectAllStaff();
	        
	        for (Staff staff : staffList) {
				System.out.println(staff.getS_id());
			}
	        
	        //传参
	        request.setAttribute("staffList", staffList);
	        
	        //跳转
		    return "manager/managerSelectStaffInfo";
	}

	
	
	@RequestMapping("/managerSelectExamination")
	public String selectAllExamination(HttpServletRequest request, Integer e_id) throws ServletException, IOException{
			request.setCharacterEncoding("UTF-8");
		
	        List<Examination> examinationList = examService.selectAllExamination();
	        
	        for (Examination examination : examinationList) {
				System.out.println(examination.getE_id());
			}
	        
	        //传参
	        request.setAttribute("examinationList", examinationList);
	        
	        //跳转
		    return "manager/managerSelectExamination";
	}

	
	@RequestMapping("/addNewTemporaryOvertime")
	public String insertTempOvertime(HttpServletRequest request) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		//获得页面传来的参数
		String getStartTime = request.getParameter("t_overtime_start");
		String getEndTime = request.getParameter("t_overtime_end");
		String t_o_reason = request.getParameter("t_o_reason");
		
		//把参数放到Map中
		HashMap<String, Object> toMap = new HashMap<String, Object>();
		toMap.put("t_overtime_start", getStartTime);
		toMap.put("t_overtime_end", getEndTime);
		toMap.put("t_o_reason", t_o_reason);
		
		//调用新增的方法
		tmpOvertimeService.insertTempOvertime(toMap);
		
		return "home";
	}

}

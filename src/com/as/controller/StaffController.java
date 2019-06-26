package com.as.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.as.entity.Overtimeapplication;
import com.as.entity.Staff;
import com.as.service.ExaminationService;
import com.as.service.StaffService;
import com.as.service.impl.ExaminationServiceImpl;
import com.as.service.impl.StaffServiceImpl;

import oracle.net.aso.r;

@Controller
@RequestMapping("/staff")

public class StaffController {
	private StaffService staffService = new StaffServiceImpl();
	
	@RequestMapping("/staffUpdateStaffInfo")
	public String updateStaffInfo(HttpServletRequest request) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		//获得有关员工信息页面传来的参数
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
		
		//调用更新的方法
		staffService.updateStaffInfoReturnId(staffMap);
		return "home";
	}
	
	@RequestMapping("/staffSelectStaffInfo")
	public String findStaffById(HttpServletRequest request) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		//查看自己的所有账户信息
		//获得有关员工信息页面传来的参数
		//String getId = request.getParameter("s_id");
        Integer s_id=4;
        if(request.getParameter("s_id") != null) {
        	 s_id = Integer.parseInt(request.getParameter("s_id"));
        }
        
        //调用查询方法
    	Staff staff = staffService.findStaffById(s_id);
						
		//传参
		request.setAttribute("staff", staff);
        request.setAttribute("s_id", s_id);
		
		//跳转
		return "staff/staffSelectStaffInfo";
	}
	public String login(HttpServletRequest request) throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		//获取参数
		Integer  s_id = 0;
        if(request.getParameter("s_id") != null) {
       	 s_id = Integer.parseInt(request.getParameter("s_id"));
       }
        String s_pass = "123456";
        if(request.getParameter("s_pass") != null) {
        	s_pass = (String)request.getParameter("s_pass");
        }
		Staff staff = staffService.findStaffById(s_id);
		
		//传参给jsp
		request.setAttribute("staff", staff);
		request.setAttribute("s_id", s_id);
		request.setAttribute("s_pass", s_pass);
		
		return "staff/Login";
	}
}

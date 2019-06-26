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
		
		//����й�Ա����Ϣҳ�洫���Ĳ���
		String getId = request.getParameter("s_id");
		String getS_name = request.getParameter("s_name");
		String getEntry_time = request.getParameter("entry_time");
		String getDep_id = request.getParameter("dep_id");
		String getIdentity = request.getParameter("identity");
		String getS_pass = request.getParameter("s_pass");
		
		
		//�Ѳ����ŵ�Map��
		HashMap<String, Object> staffMap = new HashMap<String, Object>();
		staffMap.put("s_id",getId);
		staffMap.put("s_name", getS_name);
		staffMap.put("entry_time", getEntry_time);
		staffMap.put("dep_id", getDep_id);
		staffMap.put("identity", getIdentity);
		staffMap.put("s_pass", getS_pass);
		
		//���ø��µķ���
		staffService.updateStaffInfoReturnId(staffMap);
		return "home";
	}
	
	@RequestMapping("/staffSelectStaffInfo")
	public String findStaffById(HttpServletRequest request) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		//�鿴�Լ��������˻���Ϣ
		//����й�Ա����Ϣҳ�洫���Ĳ���
		//String getId = request.getParameter("s_id");
        Integer s_id=4;
        if(request.getParameter("s_id") != null) {
        	 s_id = Integer.parseInt(request.getParameter("s_id"));
        }
        
        //���ò�ѯ����
    	Staff staff = staffService.findStaffById(s_id);
						
		//����
		request.setAttribute("staff", staff);
        request.setAttribute("s_id", s_id);
		
		//��ת
		return "staff/staffSelectStaffInfo";
	}
	public String login(HttpServletRequest request) throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		//��ȡ����
		Integer  s_id = 0;
        if(request.getParameter("s_id") != null) {
       	 s_id = Integer.parseInt(request.getParameter("s_id"));
       }
        String s_pass = "123456";
        if(request.getParameter("s_pass") != null) {
        	s_pass = (String)request.getParameter("s_pass");
        }
		Staff staff = staffService.findStaffById(s_id);
		
		//���θ�jsp
		request.setAttribute("staff", staff);
		request.setAttribute("s_id", s_id);
		request.setAttribute("s_pass", s_pass);
		
		return "staff/Login";
	}
}

package com.as.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.as.entity.Examination;
import com.as.entity.Overtimeapplication;
import com.as.entity.Temporaryovertime;
import com.as.service.ExaminationService;
import com.as.service.OvertimeapplicationService;
import com.as.service.TemporaryovertimeService;
import com.as.service.impl.ExaminationServiceImpl;
import com.as.service.impl.OvertimeapplicationServiceImpl;
import com.as.service.impl.TemporaryovertimeServiceImpl;

@Controller
@RequestMapping("/director")

public class DirectorController {
	private ExaminationService examinationService = new ExaminationServiceImpl();
	private OvertimeapplicationService overtimeService = new OvertimeapplicationServiceImpl();
	private TemporaryovertimeService tmpOvertimeService = new TemporaryovertimeServiceImpl();
	
	@RequestMapping("/directorSelectExamination")
	public String selectExamination(HttpServletRequest request) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		//获得审批意见表相关页面的参数
		String getE_id = request.getParameter("e_id");
		
		//调用查看所有审批意见函数
		Integer e_id = Integer.parseInt(getE_id);
		examinationService.findExaminationById(e_id);
		
		return "home";
	}
	
	@RequestMapping("/directorSelectAllExamination")
	public String selectAllExamination(HttpServletRequest request) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		

        List<Examination> examinationList = examinationService.selectAllExamination();
        
        for (Examination examination : examinationList) {
			System.out.println(examination.getE_id());
		}
        
        //传参
        request.setAttribute("examinationList", examinationList);
        
        //跳转
        return "director/directorSelectAllExamination";
		
	}
	
	@RequestMapping("/deleteExaminationById")
	public String deleteExamination(HttpServletRequest request) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		//获取参数
		String getE_id = request.getParameter("e_id");
		Integer e_id = Integer.parseInt(getE_id);
		
		//调用删除函数
		examinationService.deleteExaminationById(e_id);
		
		return "home";
	}
	
	@RequestMapping("/applyForTempOvertime")
	public String applyForTempOvertime(HttpServletRequest request) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		//申请临时加班
		
		HashMap<String, Object> oaJspMap = new HashMap<String, Object>();
		//获得参数
		Integer toId = Integer.parseInt(request.getParameter("to_id"));
		Integer sId = Integer.parseInt(request.getParameter("s_id"));
		String overtime_start = "2019-06-01 17:00:00";
		String overtime_end = "2019-06-01 19:00:00";
		String overtime_reason = "临时加班。原因：";
		Integer is_approved = 0;
		
		//获得已选择的临时加班信息
		Temporaryovertime selectTmpOvertime = tmpOvertimeService.findTempOvertimeByToid(toId);
		if(selectTmpOvertime != null){
			overtime_start = selectTmpOvertime.getT_overtime_start().toString();
			overtime_end = selectTmpOvertime.getT_overtime_end().toString();
			overtime_reason += selectTmpOvertime.getT_o_reason();
		}
		
		//传参
		oaJspMap.put("s_id", sId);
		oaJspMap.put("overtime_start", overtime_start);
		oaJspMap.put("overtime_end", overtime_end);
		oaJspMap.put("overtime_reason", overtime_reason);
		oaJspMap.put("is_approved", is_approved);
		
		//调用新增方法
		overtimeService.insertOvertimeApply(oaJspMap);
		
		//传参
		//request.setAttribute("s_id", sId);
		HttpSession session = request.getSession();
		session.setAttribute("s_id", sId);
		
		//return "director/selectOvertimeRecord";
		return "redirect:/director/selectOvertimeRecord";	//重定向
	}
	@RequestMapping("/insertNewExamination")
	public String insertExamination(HttpServletRequest request) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		//获得审批意见表相关页面的参数
		String getE_id = request.getParameter("e_id");
		String getE_comment = request.getParameter("e_comment");
		String getIs_overTime = request.getParameter("is_overTime");
		String getRecord_id = request.getParameter("record_id");
		
		//把参数放进Map中
		HashMap<String, Object> examMap = new HashMap<String,Object>();
		examMap.put("e_id", getE_id);
		examMap.put("e_comment", getE_comment);
		examMap.put("is_overTime", getIs_overTime);
		examMap.put("record_id", getRecord_id);
		
		//调用新增函数
		examinationService.insertExamination(examMap);
		
		return "home";
	}
	
	@RequestMapping("/selectAllTemporaryOvertime")
	public String selectAllTemporaryOvertime(HttpServletRequest request) throws ServletException, IOException{
		//查看所有可以申请的临时加班
		
		request.setCharacterEncoding("UTF-8");
		
		List<Temporaryovertime> tmpOvertimeNowList = tmpOvertimeService.selectTmpOvertimeByNowDate();
		
		//传参
		request.setAttribute("tmpOvertimeNowList", tmpOvertimeNowList);
		
		//跳转
		return "director/selectAllTemporaryOvertime";
	}
	
	@RequestMapping("selectOvertimeRecord")
	public String selectOvertimeRecord(HttpServletRequest request) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		//查看自己的所有加班记录
		
		//获得参数
		Integer sId = 0;
		if(request.getParameter("s_id") != null){	//页面传来的参数
			sId = Integer.parseInt(request.getParameter("s_id"));
		}else if(request.getSession() != null){	//controller之间通过session传参的
			HttpSession session = request.getSession();
			sId = (Integer)session.getAttribute("s_id");
			System.out.println("看看有sid了么："+sId);
		}
		
		//调用查询方法
		List<Overtimeapplication> myOvertimeApplyList = overtimeService.selectAllOvertimeApplyBySid(sId);
		
		//传参
		request.setAttribute("s_id", sId);
		request.setAttribute("myOvertimeApplyList", myOvertimeApplyList);
		
		//跳转
		return "director/selectOvertimeRecord";
	}

}

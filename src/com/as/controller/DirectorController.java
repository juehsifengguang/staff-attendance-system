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
		
		//���������������ҳ��Ĳ���
		String getE_id = request.getParameter("e_id");
		
		//���ò鿴���������������
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
        
        //����
        request.setAttribute("examinationList", examinationList);
        
        //��ת
        return "director/directorSelectAllExamination";
		
	}
	
	@RequestMapping("/deleteExaminationById")
	public String deleteExamination(HttpServletRequest request) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		//��ȡ����
		String getE_id = request.getParameter("e_id");
		Integer e_id = Integer.parseInt(getE_id);
		
		//����ɾ������
		examinationService.deleteExaminationById(e_id);
		
		return "home";
	}
	
	@RequestMapping("/applyForTempOvertime")
	public String applyForTempOvertime(HttpServletRequest request) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		//������ʱ�Ӱ�
		
		HashMap<String, Object> oaJspMap = new HashMap<String, Object>();
		//��ò���
		Integer toId = Integer.parseInt(request.getParameter("to_id"));
		Integer sId = Integer.parseInt(request.getParameter("s_id"));
		String overtime_start = "2019-06-01 17:00:00";
		String overtime_end = "2019-06-01 19:00:00";
		String overtime_reason = "��ʱ�Ӱࡣԭ��";
		Integer is_approved = 0;
		
		//�����ѡ�����ʱ�Ӱ���Ϣ
		Temporaryovertime selectTmpOvertime = tmpOvertimeService.findTempOvertimeByToid(toId);
		if(selectTmpOvertime != null){
			overtime_start = selectTmpOvertime.getT_overtime_start().toString();
			overtime_end = selectTmpOvertime.getT_overtime_end().toString();
			overtime_reason += selectTmpOvertime.getT_o_reason();
		}
		
		//����
		oaJspMap.put("s_id", sId);
		oaJspMap.put("overtime_start", overtime_start);
		oaJspMap.put("overtime_end", overtime_end);
		oaJspMap.put("overtime_reason", overtime_reason);
		oaJspMap.put("is_approved", is_approved);
		
		//������������
		overtimeService.insertOvertimeApply(oaJspMap);
		
		//����
		//request.setAttribute("s_id", sId);
		HttpSession session = request.getSession();
		session.setAttribute("s_id", sId);
		
		//return "director/selectOvertimeRecord";
		return "redirect:/director/selectOvertimeRecord";	//�ض���
	}
	@RequestMapping("/insertNewExamination")
	public String insertExamination(HttpServletRequest request) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		//���������������ҳ��Ĳ���
		String getE_id = request.getParameter("e_id");
		String getE_comment = request.getParameter("e_comment");
		String getIs_overTime = request.getParameter("is_overTime");
		String getRecord_id = request.getParameter("record_id");
		
		//�Ѳ����Ž�Map��
		HashMap<String, Object> examMap = new HashMap<String,Object>();
		examMap.put("e_id", getE_id);
		examMap.put("e_comment", getE_comment);
		examMap.put("is_overTime", getIs_overTime);
		examMap.put("record_id", getRecord_id);
		
		//������������
		examinationService.insertExamination(examMap);
		
		return "home";
	}
	
	@RequestMapping("/selectAllTemporaryOvertime")
	public String selectAllTemporaryOvertime(HttpServletRequest request) throws ServletException, IOException{
		//�鿴���п����������ʱ�Ӱ�
		
		request.setCharacterEncoding("UTF-8");
		
		List<Temporaryovertime> tmpOvertimeNowList = tmpOvertimeService.selectTmpOvertimeByNowDate();
		
		//����
		request.setAttribute("tmpOvertimeNowList", tmpOvertimeNowList);
		
		//��ת
		return "director/selectAllTemporaryOvertime";
	}
	
	@RequestMapping("selectOvertimeRecord")
	public String selectOvertimeRecord(HttpServletRequest request) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		//�鿴�Լ������мӰ��¼
		
		//��ò���
		Integer sId = 0;
		if(request.getParameter("s_id") != null){	//ҳ�洫���Ĳ���
			sId = Integer.parseInt(request.getParameter("s_id"));
		}else if(request.getSession() != null){	//controller֮��ͨ��session���ε�
			HttpSession session = request.getSession();
			sId = (Integer)session.getAttribute("s_id");
			System.out.println("������sid��ô��"+sId);
		}
		
		//���ò�ѯ����
		List<Overtimeapplication> myOvertimeApplyList = overtimeService.selectAllOvertimeApplyBySid(sId);
		
		//����
		request.setAttribute("s_id", sId);
		request.setAttribute("myOvertimeApplyList", myOvertimeApplyList);
		
		//��ת
		return "director/selectOvertimeRecord";
	}

}

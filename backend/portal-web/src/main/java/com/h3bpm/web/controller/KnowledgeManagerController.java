package com.h3bpm.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.h3bpm.web.entity.Knowledge;
import com.h3bpm.web.mapper.KnowledgeMapper;
import com.h3bpm.web.service.KnowledgeService;
import com.h3bpm.web.vo.KnowledgeVo;
import com.h3bpm.web.vo.ReqCreateKnowledge;
import com.h3bpm.web.vo.ReqListKnowledgePageVo;
import com.h3bpm.web.vo.ReqUpdateKnowledge;
import com.h3bpm.web.vo.RespPageVo;
import com.h3bpm.web.vo.ResponseVo;
import com.h3bpm.web.vo.query.QueryKnowledgeList;

import OThinker.Common.Organization.Models.User;
import OThinker.H3.Controller.ControllerBase;

/**
 * Created by tonghao on 2020/3/1.
 */
@Controller
@RequestMapping(value = "/Portal/knowledgeManage")
public class KnowledgeManagerController extends ControllerBase {

	private static final Logger logger = LoggerFactory.getLogger(KnowledgeManagerController.class);

	@Autowired
	private KnowledgeService knowledgeService;
	@Autowired
	private KnowledgeMapper knowledgeMapper;

	@Override
	public String getFunctionCode() {
		// TODO Auto-generated method stub
		return null;
	}

	// author:lbh
	@RequestMapping(value = "/createKnowledge", produces = "application/json;charset=utf8")
	@ResponseBody
	public ResponseVo createKnowledge(@RequestBody ReqCreateKnowledge reqParam) throws Exception {
		Map<String, Object> userMap = this._getCurrentUser();
		User user = (User) userMap.get("User");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");	//String与Date之间进行相互转换
		KnowledgeVo knowledgeVo = new KnowledgeVo();

		knowledgeVo.setCreateUserName(user._Name);
		knowledgeVo.setCreateUserId(user.getObjectID());	//存入用户id
		knowledgeVo.setFlowId(reqParam.getFlowId());
		knowledgeVo.setName(reqParam.getName());
		knowledgeVo.setDesc(reqParam.getDesc());
		knowledgeVo.setTagName(reqParam.getTagName());
		knowledgeVo.setFlowCodeDesc(reqParam.getFlowCodeDesc());
		try{
			knowledgeVo.setStartTime(format.parse(reqParam.getStartTime()));
		}catch (Exception e){
			e.printStackTrace();
		}
		try{
			knowledgeVo.setEndTime(format.parse(reqParam.getEndTime()));
		}catch (Exception e){
			e.printStackTrace();
		}
		knowledgeVo.setCreateTime(new Date());
		knowledgeVo.setPermission(reqParam.getPermission());
		knowledgeService.createKnowledge(knowledgeVo);

		return new ResponseVo("创建成功");
	}

	@RequestMapping(value = "/updateKnowledge", produces = "application/json;charset=utf8")
	@ResponseBody
	public void updateKnowledge(@RequestBody ReqUpdateKnowledge reqUpdateKnowledge) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");	//String与Date之间进行相互转换
		Knowledge knowledgeEntity = knowledgeService.getKnowledgeById(reqUpdateKnowledge.getId());
		KnowledgeVo knowledgeVo = new KnowledgeVo(knowledgeEntity);

		knowledgeVo.setFlowId(reqUpdateKnowledge.getFlowId());
		knowledgeVo.setName(reqUpdateKnowledge.getName());
		knowledgeVo.setDesc(reqUpdateKnowledge.getDesc());
		knowledgeVo.setTagName(reqUpdateKnowledge.getTagName());
		knowledgeVo.setFlowCodeDesc(reqUpdateKnowledge.getFlowCodeDesc());
		try{	 //将前端传过来的'yyyy-MM-dd'样式的String转换成Date类型
			knowledgeVo.setStartTime(format.parse(reqUpdateKnowledge.getStartTime()));
		}catch (Exception e){
			e.printStackTrace();
		}
		try{
			knowledgeVo.setEndTime(format.parse(reqUpdateKnowledge.getEndTime()));
		}catch (Exception e){
			e.printStackTrace();
		}
		knowledgeVo.setPermission(reqUpdateKnowledge.getPermission());
		knowledgeService.updateKnowledge(knowledgeVo);
	}
	
//	@RequestParam(value = "path") String path
	@RequestMapping(value = "/listKnowledgeByPage", produces = "application/json;charset=utf8")
	@ResponseBody
	public RespPageVo listKnowledgeByPage(@ModelAttribute ReqListKnowledgePageVo requestBean) {
//		Map<String, Object> userMap = null;
//		try {
//			userMap = this._getCurrentUser();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		User user = (User) userMap.get("User");
		
		QueryKnowledgeList queryKnowledgeList = new QueryKnowledgeList(requestBean);
//		queryKnowledgeList.setQueryUserId(user.getObjectId());
		
		PageInfo<KnowledgeVo> pageInfo = knowledgeService.findKnowledgeByPage(queryKnowledgeList);
		
		return new RespPageVo(requestBean.getsEcho(),pageInfo.getTotal(),pageInfo.getList());
	}
}
package com.activemq.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.activemq.dto.SendMessageDto;
import com.activemq.exception.BizDataException;
import com.activemq.ret.BaseRet;

@Controller
@RequestMapping("/send")
public class SendController {

	private static final Logger logger = LoggerFactory.getLogger(SendController.class);
	
	@Autowired
	@Qualifier("queueTemplate")
	private JmsTemplate queueTemplate;
	@Autowired
	@Qualifier("topicTemplate")
	private JmsTemplate topicTemplate;
	
	@ResponseBody
	@RequestMapping("/sendMsg")
	public BaseRet send(@RequestBody SendMessageDto dto){
		
		BaseRet ret = new BaseRet();
		try {
			if(StringUtils.isBlank(dto.getRoutingKey())){
				throw new BizDataException("路由键不能为空");
			}
			if(null == dto.getMsg()){
				throw new BizDataException("消息内容不能为空");
			}
			queueTemplate.convertAndSend(dto.getRoutingKey(), dto.getMsg());
			ret.setSuccess("0");
			ret.setMsg("成功");
		} catch (BizDataException e) {
			ret.setSuccess("1");
			ret.setMsg(e.getMessage());
			logger.error(e.getMessage());
		} catch (Exception e) {
			ret.setSuccess("2");
			ret.setMsg("失败");
			logger.error(e.getMessage());
		}
		return ret;
	}
	
	@ResponseBody
	@RequestMapping("/sendMsg2")
	public BaseRet send2(@RequestBody SendMessageDto dto){
		
		BaseRet ret = new BaseRet();
		try {
			if(StringUtils.isBlank(dto.getRoutingKey())){
				throw new BizDataException("路由键不能为空");
			}
			if(null == dto.getMsg()){
				throw new BizDataException("消息内容不能为空");
			}
			topicTemplate.convertAndSend(dto.getRoutingKey(), dto.getMsg());
			ret.setSuccess("0");
			ret.setMsg("成功");
		} catch (BizDataException e) {
			ret.setSuccess("1");
			ret.setMsg(e.getMessage());
			logger.error(e.getMessage());
		} catch (Exception e) {
			ret.setSuccess("2");
			ret.setMsg("失败");
			logger.error(e.getMessage());
		}
		return ret;
	}
	
}

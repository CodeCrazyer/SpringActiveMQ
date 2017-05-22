package com.activemq.core;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import com.alibaba.fastjson.JSONObject;

public class FastJsonMessageConverter implements MessageConverter {

	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		
		ActiveMQTextMessage textMessage = new ActiveMQTextMessage();
		String json = JSONObject.toJSONString(object);
		textMessage.setText(json);
		return textMessage;
	}

	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		
		ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
		return JSONObject.parse(textMessage.getText());
	}

}

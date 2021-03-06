package com.activemq.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;

public class Consumer3 implements MessageListener {

	public void onMessage(Message message) {
		try {
			ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
			System.out.println("consumer3 received message:" + msg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}

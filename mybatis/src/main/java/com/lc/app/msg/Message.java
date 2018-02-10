package com.lc.app.msg;

public enum Message {
	INFO, DEBUG, ERROR;
	public boolean isInfo(Message msg) {
		return Message.INFO.equals(msg);
	}
}

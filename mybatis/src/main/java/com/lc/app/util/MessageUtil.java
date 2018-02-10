package com.lc.app.util;

import java.util.HashMap;

public final class MessageUtil {
	private static HashMap<Integer,String> msgMap;
	private MessageUtil() {
	}
	static {
		msgMap = new HashMap<Integer,String>();
		msgMap.put(1, "id must not be null");
		msgMap.put(2, "id format is invalid");
	}
	public static final String getMsgByCode(Integer code) {
		return msgMap.get(code);
	}
}
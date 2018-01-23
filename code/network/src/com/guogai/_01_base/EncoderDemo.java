package com.guogai._01_base;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class EncoderDemo {
	public static void main(String[] args) throws Exception {
		String msg = "中国";
		//编码
		String str = URLEncoder.encode(msg,"utf-8");
		System.out.println(str);
		//解码
		msg = URLDecoder.decode(str,"utf-8");
		System.out.println(msg);
	}
}
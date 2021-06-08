package com.projectmigry.migry.admin.common;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getDateDiffStr(int dateDiff) {
		String str = "";
		
		if(dateDiff/60 < 1) {
			str = dateDiff + "초";
		} else if(dateDiff/60/60 < 1) {
			str = dateDiff/60 + "분";
		} else if(dateDiff/60/60/24 < 1) {
			str = dateDiff/60/60 + "시간";
		} else if(dateDiff/60/60/24/7 < 1) {
			str = dateDiff/60/60/24 + "일";
		} else if(dateDiff/60/60/24/30 < 1) {
			str = dateDiff/60/60/24/7 + "주";
		} else if(dateDiff/60/60/24/365 < 1) {
			str = dateDiff/60/60/24/30 + "달";
		} else {
			str = dateDiff/60/60/24/365 + "년";
		}
		
		return str;
	}
	
	/**
	 * 시스템 날짜 구하기
	 * @param format ex) yyyy-MM-dd, yyyyMMdd, yyyy-MM-dd HH:mm:ss, yyyyMMddHHmmss
	 * @return
	 */
	public static String getSystemDate(String format) {
		Format form = new SimpleDateFormat(format);
		String sysdateStr = form.format(new Date());
		
		return sysdateStr;
	}
}

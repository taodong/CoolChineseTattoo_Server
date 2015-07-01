package com.daijt.cth.util;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Misc {
	
	/**
	 * Get HTTP Session
	 * @return
	 */
	public static HttpSession retrieveSession() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true); // true == allow create
	}
	
	/**
	 * Append % on both side of a string to form like query condition
	 * @return
	 */
	public static String formLikeQueryStr(String condition) {
		StringBuilder sb = new StringBuilder(condition);
		sb.insert(0, "%");
		sb.append("%");
		
		return sb.toString();
	}
	
	/**
	 * Remove none alphabetic characters in a given string
	 * @param rawStr
	 * @return
	 */
	public static String removeNonAlphabetic(String rawStr) {
		String newStr = rawStr.replaceAll("[^a-zA-Z ]", "");
		newStr = newStr.replaceAll("[ ]+", " ");
		return newStr;
	}
	
	/**
	 * Check whether the tags contain a tag exactly match the given word
	 * @param word
	 * @param tags
	 * @return
	 */
	public static boolean matchWordThroughTags(String word, String tags) {
		
		boolean match = false;
		
		int index = StringUtils.indexOf(tags, word);
		int wordLen = StringUtils.length(word);
		
		if ((index + wordLen) == StringUtils.length(tags)) {
			match = true;
		}
		else if (index == 0) {
			int startIndex = wordLen;
			String spStr = StringUtils.substring(tags, startIndex, startIndex + 1);
			
			if (spStr.equals(" ")) {
				match = true;
			}
		}
		else {
			StringBuilder sb = new StringBuilder(" ");
			sb.append(word);
			sb.append(" ");
			
			match = StringUtils.contains(tags, sb.toString());
		}
		
		return match;
	}
	
	/**
	 * Replace white space with a plus sign within a given string. 
	 * Used to form the url string
	 * @param rawStr
	 * @return
	 */
	public static String formatString4Query(String rawStr) {
		
		String address = StringUtils.replace(rawStr, " ", "+");
		
		return address;
	}
	
}

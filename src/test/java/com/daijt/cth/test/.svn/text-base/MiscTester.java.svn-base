package com.daijt.cth.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.daijt.cth.util.Misc;

public class MiscTester {
	
	@Test
	public void testMatchWordThroughTags() {
		String tags = "god_bless god_blessing god_blessed";
		String word = "god_bless";
		
		boolean rs = Misc.matchWordThroughTags(word, tags);
		
		assertTrue(rs);
	}
	
	@Test
	public void testRemoveNonAlphabetic() {
		String rawStr = "I 829 love y!!!";
		
		String newStr = Misc.removeNonAlphabetic(rawStr);
		
		// System.out.println(newStr);
		
		assertEquals("I love y", newStr);
	}
	
}

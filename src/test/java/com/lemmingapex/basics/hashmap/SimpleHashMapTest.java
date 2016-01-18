package com.lemmingapex.basics.hashmap;

import org.junit.Test;

public class SimpleHashMapTest {

	@Test
	public void test() {
		SimpleHashMap<String, String> myHashMap = new SimpleHashMap<String, String>();
		myHashMap.put("Test", "testvalue");
		myHashMap.put("Test", "testvalue1");
		myHashMap.put("Test2", "test2");
		
		System.out.println(myHashMap.get("Test"));
		System.out.println(myHashMap.get("Test2"));
		System.out.println(myHashMap.get("Test3"));
	}
}

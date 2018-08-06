package com.example.kt.java;

import com.example.kt.kotlin.TestKotlinDemo;

/**
 * Created By LRD
 * on 2018/8/6  notes：测试kotlin 调用java
 */
public class TestJavaDemo {
	public String getType(){
		return "Java";
	}

	public String getKotlinType(){
		return new TestKotlinDemo().getType();
	}
}

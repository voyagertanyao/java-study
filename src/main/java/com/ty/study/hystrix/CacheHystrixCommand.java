package com.ty.study.hystrix;

import java.util.concurrent.ExecutionException;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import junit.framework.Assert;

public class CacheHystrixCommand extends HystrixCommand<String> {

	private String name;
	private int age;
	
	public CacheHystrixCommand(String name, int age) {
		super(HystrixCommandGroupKey.Factory.asKey("cacheGroup"));
		this.name = name;
		this.age = age;
	}

	@Override
	protected String run() throws Exception {
		System.out.println(name + String.valueOf(age) + "run()");
		return name + String.valueOf(age);
	}
	
	@Override
	public String getCacheKey() {
		return name + String.valueOf(age); 
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		HystrixRequestContext context =  HystrixRequestContext.initializeContext();
		
		CacheHystrixCommand command1 = new CacheHystrixCommand("t", 1);
		command1.queue().get();
		
		CacheHystrixCommand command2 = new CacheHystrixCommand("t", 2);
		command2.queue().get();
		
		CacheHystrixCommand command3 = new CacheHystrixCommand("t", 2);
		command3.queue().get();
		
		Assert.assertEquals(true, false);
		
		context.shutdown();
	}
	
}

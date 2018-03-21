package com.ty.study.hystrix;

import java.io.IOException;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class HelloWorldHystrixCommand extends HystrixCommand<String> {
	private String name;

	protected HelloWorldHystrixCommand(String name) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("groupKey"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("commandKey"))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("threadPoolKey"))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
						.withCircuitBreakerEnabled(true)
						.withCircuitBreakerRequestVolumeThreshold(3))
				.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(100)));
		this.name = name;
	}

	@Override
	protected String run() throws Exception {
		//System.out.println(Thread.currentThread().getName() + " >>> run" + name);
		//Thread.sleep(2000);
		return "run";
	}
	
	@Override
	protected String getFallback() {
		System.out.println(Thread.currentThread().getName() + " >>> fall back " + name);
		return "fallback";
	}
	
	public static void main(String[] args) throws IOException {
		for(int i=0; i< 100000; i++) {
			new HelloWorldHystrixCommand(String.valueOf(i)).queue();
		}
		System.in.read();
	}

}

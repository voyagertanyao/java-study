package com.ty.study.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolTest implements Callable<Integer>{
	
	private int from;
	private int to;
	private ExecutorService threadPool;
	
	public ThreadPoolTest(int from, int to, ExecutorService threadPool){
		this.from = from;
		this.to = to;
		this.threadPool = threadPool;
	}
	
	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		Future<Integer> result = pool.submit(new ThreadPoolTest(1,100,pool));
		try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}finally {
			pool.shutdown();
		}
	}

	@Override
	public Integer call() throws Exception {
		System.out.println("-->"+Thread.currentThread().getName()+"from:"+from+",to:"+to);
		if(this.to - this.from > 10) {
			Future<Integer> t1 = threadPool.submit(new ThreadPoolTest(from, (from + to)/2, threadPool));
			Future<Integer> t2 = threadPool.submit(new ThreadPoolTest((from + to)/2 + 1, to, threadPool));
			return t1.get() + t2.get();
		}
		return add(from, to);
	}
	
	private int add(int from, int to) {
		int total = 0;
		if(to < from) {
			return 0;			
		}
		for(int i=from; i <=to; i++) {
			total += i;
		}
		return total;
	}

}

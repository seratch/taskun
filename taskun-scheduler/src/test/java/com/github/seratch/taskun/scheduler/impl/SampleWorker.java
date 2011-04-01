package com.github.seratch.taskun.scheduler.impl;

public class SampleWorker implements Runnable {

	@Override
	public void run() {
		System.out.println("SampleWorker called!");
	}
}

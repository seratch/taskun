package com.github.seratch.taskun.servlet.snippet;

import java.util.Date;

public class EchoWorker implements Runnable {

	@Override
	public void run() {
		System.out.println("Hello, World! (Thread:"
				+ Thread.currentThread().getId() + "," + new Date().toString()
				+ ")");
	}

}

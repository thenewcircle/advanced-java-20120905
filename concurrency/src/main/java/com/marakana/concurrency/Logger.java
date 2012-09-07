package com.marakana.concurrency;

public class Logger extends ProducerConsumer<Object> {

	@Override
	protected void handleMessage(Object message) {
		System.out.println(message);
	}

}

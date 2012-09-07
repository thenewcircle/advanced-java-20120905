package com.marakana.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class ProducerConsumer<M> implements Runnable {

	private final BlockingQueue<M> queue = new LinkedBlockingQueue<M>();

	public void put(M message) {
		try {
			queue.put(message);
		} catch (InterruptedException e) {}
	}

	@Override
	public void run() {
		try {
			while (true) {
				handleMessage(queue.take());
			}
		} catch (InterruptedException e) {}
	}

	protected abstract void handleMessage(M message);
}

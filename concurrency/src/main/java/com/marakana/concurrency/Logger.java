package com.marakana.concurrency;

import java.util.LinkedList;
import java.util.Queue;

public class Logger implements Runnable {

	private final Queue<Object> queue = new LinkedList<Object>();

	public void log(Object o) {
		synchronized (queue) {
			queue.add(o);
		}
	}

	@Override
	public void run() {
		while (true) {
			if (queue.isEmpty()) {
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {}
			} else {
				synchronized (queue) {
					System.out.println(queue.remove());
				}
			}
		}
	}
}

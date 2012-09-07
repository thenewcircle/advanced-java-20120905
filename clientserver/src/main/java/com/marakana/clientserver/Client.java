package com.marakana.clientserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

import com.marakana.concurrency.Fibonacci;

public class Client {

	public static void main(String[] args) {
		try {
			Socket server = new Socket(InetAddress.getLocalHost(), 31337);
			try {
				PrintWriter out = new PrintWriter(server.getOutputStream());
				try {
					for (int i = 0; i < 100; i++) {
						out.println(Fibonacci.fibonacci(new Random().nextInt(40)));
						out.flush();
					}
				} finally {
					out.close();
				}
			} finally {
				server.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

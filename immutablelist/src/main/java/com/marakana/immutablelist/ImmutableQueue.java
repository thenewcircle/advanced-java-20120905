package com.marakana.immutablelist;

public class ImmutableQueue<E> {

	private ImmutableStack<E> in, out;

	public static <E> ImmutableQueue<E> empty() {
		return new ImmutableQueue<E>(ImmutableStack.<E> empty(), ImmutableStack.<E> empty());
	}

	private ImmutableQueue(ImmutableStack<E> in, ImmutableStack<E> out) {
		this.in = in;
		this.out = out;
	}

	public ImmutableQueue<E> enqueue(E element) {
		return new ImmutableQueue<E>(in.push(element), out);
	}

	public E head() {
		shuffle();
		return out.head();
	}

	public ImmutableQueue<E> tail() {
		shuffle();
		return new ImmutableQueue<E>(in, out.tail());
	}

	private void shuffle() {
		ImmutableStack<E> in = this.in;
		ImmutableStack<E> out = this.out;
		while (in.size() > 0) {
			out = out.push(in.head());
			in = in.tail();
		}
		synchronized (this) {
			this.in = in;
			this.out = out;
		}
	}
}

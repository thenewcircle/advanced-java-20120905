package com.marakana.immutablelist;

import java.util.NoSuchElementException;

public abstract class ImmutableStack<E> {

	public static <E> ImmutableStack<E> empty() {
		return new Nil<E>();
	}

	public ImmutableStack<E> push(E element) {
		return new Cons<E>(element, this);
	}

	public abstract E head();
	public abstract ImmutableStack<E> tail();
	public abstract int size();
	public abstract boolean isEmpty();
	public abstract <F> ImmutableStack<F> map(Function<? super E, ? extends F> fn);

	private static class Cons<E> extends ImmutableStack<E> {
		private final E head;
		private final ImmutableStack<E> tail;

		public Cons(E head, ImmutableStack<E> tail) {
			this.head = head;
			this.tail = tail;
		}

		@Override
		public E head() {
			return head;
		}

		@Override
		public ImmutableStack<E> tail() {
			return tail;
		}

		@Override
		public int size() {
			return 1 + tail.size();
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public <F> ImmutableStack<F> map(Function<? super E, ? extends F> fn) {
			return new Cons<F>(fn.apply(head), tail.map(fn));
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((head == null) ? 0 : head.hashCode());
			result = prime * result + tail.hashCode();
			return result;
		}

		@Override
		@SuppressWarnings("unchecked")
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof Cons))
				return false;
			Cons<E> other = (Cons<E>) obj;
			if (head == null) {
				if (other.head != null)
					return false;
			} else if (!head.equals(other.head))
				return false;
			if (!tail.equals(other.tail))
				return false;
			return true;
		}
	}

	private static class Nil<E> extends ImmutableStack<E> {

		@Override
		public E head() {
			throw new NoSuchElementException();
		}

		@Override
		public ImmutableStack<E> tail() {
			return this;
		}

		@Override
		public int size() {
			return 0;
		}

		@Override
		public boolean isEmpty() {
			return true;
		}

		@Override
		public <F> ImmutableStack<F> map(Function<? super E, ? extends F> fn) {
			return empty();
		}

		@Override
		public int hashCode() {
			return 0;
		}

		@Override
		public boolean equals(Object o) {
			return (o instanceof Nil);
		}
	}

}

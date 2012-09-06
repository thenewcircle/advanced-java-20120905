package com.marakana.immutablelist;

import java.util.NoSuchElementException;

public abstract class ImmutableList<E> {

	public static <E> ImmutableList<E> empty() {
		return new Nil<E>();
	}

	public ImmutableList<E> push(E element) {
		return new Cons<E>(element, this);
	}

	public abstract E head();
	public abstract ImmutableList<E> tail();
	public abstract int size();
	public abstract <F> ImmutableList<F> map(Function<? super E, ? extends F> fn);

	private static class Cons<E> extends ImmutableList<E> {
		private final E head;
		private final ImmutableList<E> tail;

		public Cons(E head, ImmutableList<E> tail) {
			this.head = head;
			this.tail = tail;
		}

		@Override
		public E head() {
			return head;
		}

		@Override
		public ImmutableList<E> tail() {
			return tail;
		}

		@Override
		public int size() {
			return 1 + tail.size();
		}

		@Override
		public <F> ImmutableList<F> map(Function<? super E, ? extends F> fn) {
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

	private static class Nil<E> extends ImmutableList<E> {

		@Override
		public E head() {
			throw new NoSuchElementException();
		}

		@Override
		public ImmutableList<E> tail() {
			return this;
		}

		@Override
		public int size() {
			return 0;
		}

		@Override
		public <F> ImmutableList<F> map(Function<? super E, ? extends F> fn) {
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

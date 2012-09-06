package com.marakana.immutablelist;

public class VarianceDemo {

	private static class A {};
	private static class B extends A {
		public String b() { return "B"; }
	};
	private static class C extends B {};

	private static class Box<E> {
		private E element;
		public Box(E element) {
			this.element = element;
		}
		public E get() {
			return element;
		}
		public void put(E element) {
			this.element = element;
		}
	}

	public static void getFromABox(Box<? extends B> box) {
		B element = box.get();
		System.out.println(element.b());
	}

	public static void putInABox(Box<? super B> box) {
		B element = new B();
		box.put(element);
	}

	public static void getAndPut(Box<B> box) {
		B element = box.get();
		System.out.println(element.b());
		box.put(element);
	}

	public static void covarianceDemo() {
		Box<C> box = new Box<C>(new C());
		getFromABox(box);
	}

	public static void contravarianceDemo() {
		Box<A> box = new Box<A>(null);
		putInABox(box);
	}

	public static void invarianceDemo() {
		Box<B> box = new Box<B>(new B());
		getAndPut(box);
	}

}

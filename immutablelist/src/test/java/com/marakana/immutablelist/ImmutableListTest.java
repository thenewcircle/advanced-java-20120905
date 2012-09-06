package com.marakana.immutablelist;

import static org.junit.Assert.*;

import org.junit.Test;

import static com.marakana.immutablelist.ImmutableList.empty;

public class ImmutableListTest {

	private static final ImmutableList<Integer>
		EMPTY = empty(),
		ONE = EMPTY.push(1),
		TWO = ONE.push(2);

	private static final Function<Object, String> TO_STRING =
		new Function<Object, String>() {
			public String apply(Object input) {
				return input.toString();
			}
		};

	@Test
	public void oneElementListMustHaveSizeOne() {
		assertEquals(1, ONE.size());
	}

	@Test
	public void oneElementListMustHaveCorrectHead() {
		assertEquals(1, (int) ONE.head());
	}

	@Test
	public void oneElementListMustHaveEmptyTail() {
		assertSame(EMPTY, ONE.tail());
	}

	@Test
	public void pushToOneElementListShouldKeepOldListIntact() {
		ONE.push(2);
		assertEquals(1, ONE.size());
	}

	@Test
	public void mapIntegerToStringMustWork() {
		assertEquals(empty().push("1").push("2"), TWO.map(TO_STRING));
	}
}

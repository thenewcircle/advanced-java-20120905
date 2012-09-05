package com.marakana.immutablelist;

import static org.junit.Assert.*;

import org.junit.Test;

import static com.marakana.immutablelist.ImmutableList.empty;

public class ImmutableListTest {

	private static final ImmutableList<Integer> EMPTY = empty();
	private static final ImmutableList<Integer> ONE = EMPTY.push(1);

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

}

package com.github.seratch.taskun.scheduler.crond;

import com.github.seratch.taskun.scheduler.crond.RawCrontabLine.*;
import static org.junit.Assert.*;
import org.junit.Test;


public class RawCrontabLineTest {

	@Test
	public void type() throws Exception {
		assertNotNull(RawCrontabLine.class);
	}

	@Test
	public void instantiation() throws Exception {
		String line = null;
		RawCrontabLine target = new RawCrontabLine(line);
		assertNotNull(target);
	}

	@Test
	public void toString_A$() throws Exception {
		String line = null;
		RawCrontabLine target = new RawCrontabLine(line);
		String actual = target.toString();
		String expected = "";
		assertEquals(expected, actual);
	}

	@Test
	public void equals_A$Object() throws Exception {
		String line = null;
		RawCrontabLine target = new RawCrontabLine(line);
		Object obj = null;
		boolean actual = target.equals(obj);
		boolean expected = false;
		assertEquals(expected, actual);
	}

}

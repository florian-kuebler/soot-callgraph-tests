package de.tud.cs.soot.callgraph.tests;

import junit.framework.TestCase;

import org.junit.Test;

public class MissInvokeTest extends TestCase {

	public MissInvokeTest(){
		super("testMethodInvokation");
	}

	@Test
	public void testMethodInvokation() {
		fail("Method not invoked");
	}

}

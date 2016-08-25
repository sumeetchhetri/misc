package com;

import com.gatf.executor.core.PostTestCaseExecutionHook;
import com.gatf.executor.core.PreTestCaseExecutionHook;
import com.gatf.executor.core.TestCase;
import com.gatf.executor.report.TestCaseReport;

public class PrePostCustomHooks {

	@PreTestCaseExecutionHook
	public static void preHook(final TestCase testCase) {
		System.out.println("Pre hook called....");
	}
	
	@PostTestCaseExecutionHook
	public static void postHook(final TestCaseReport testCaseReportr) {
		System.out.println("Post hook called....");
	}
}

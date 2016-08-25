package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gatf.executor.core.AcceptanceTestContext;
import com.gatf.executor.dataprovider.GatfTestDataProvider;

public class CustomTestDataProvider /*implements TestDataProvider*/ {

	public List<Map<String, String>> provide(GatfTestDataProvider provider, AcceptanceTestContext context) {
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		return result;
	}
}

package com.vendantu.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.vendantu.util.CommonUtils;

public class VedantuDataProviderFactory {

	@DataProvider(name = "getData")
	public static Iterator<Map<String, String>> getData() {

		List<Map<String, String>> ids = new ArrayList<Map<String, String>>();
		Map<String, String> innerMap = new LinkedHashMap<String, String>();

		Map<String, String> hm = CommonUtils.readCsvData();

		for (String key : hm.keySet()) {
			String val = hm.get(key);
			innerMap.put(key, val);
		}
		ids.add(innerMap);
		System.out.println("This is what ?");
		System.out.println(ids);

		return ids.iterator();

	}

}

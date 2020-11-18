package com.vendantu.common.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CommonUtils {

	// read from CSV file function.
	public static void main(String[] args) {
		readCsvData();
	}

	public static Map<String, String> readCsvData() {

		Map<String, String> hm = new LinkedHashMap<String, String>();

		try (Reader reader = Files.newBufferedReader(Paths.get(Constants.DATA_CSV_PATH))) {
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
			for (CSVRecord csvRecord : csvParser) {
				hm = csvRecord.toMap();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return hm;
	}
}

package com.dykm.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	
	public static String getBirthOrFoundation(String text) {
        Pattern pattern = Pattern.compile("\\b(\\d{4})\\b");
        Matcher matcher = pattern.matcher(text);
        List<Integer> years = new ArrayList<>();

        while (matcher.find()) {
            years.add(Integer.parseInt(matcher.group()));
        }
        if (years.isEmpty()) {
			return "unknow";
		}else {
			return String.valueOf(years.get(0));
		}
    }
}

package com.CRUD.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CircularUtils {
	
	
	 public static Set<Long> convertStringToSet(String userIds) {
	        if (userIds == null || userIds.isEmpty()) {
	            return new HashSet<>();
	        }
	        return Arrays.stream(userIds.split(","))
	                     .map(Long::parseLong)
	                     .collect(Collectors.toSet());
	    }
	 
	  // Convert Set<Long> to comma-separated String
	    public static String convertSetToString(Set<Long> userIds) {
	        return userIds.stream()
	                      .map(String::valueOf)
	                      .collect(Collectors.joining(","));
	    }

	
}

package com.joalvarez.keycloakauth.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
public class Utils {

	public static String objectToJson(Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException("Error converting object to json");
		}
	}

	public static <T> T jsonToObject(String json, Class<T> tClass) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(json, tClass);
		} catch (Exception e) {
			throw new RuntimeException("Error converting json to pretty json");
		}
	}

	public static <T, U, V> V jsonToObjectMixing(String json, Class<T> targetClass, Class<U> mixingClass, Class<V> returnClass) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper
				.addMixIn(targetClass, mixingClass)
				.readValue(json, returnClass);
		} catch (Exception e) {
			throw new RuntimeException("Error converting json to pretty json");
		}
	}

	public String prettyJson(String json) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Object jsonObject = objectMapper.readValue(json, Object.class);
			return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
		} catch (Exception e) {
			throw new RuntimeException("Error converting json to pretty json");
		}
	}

}

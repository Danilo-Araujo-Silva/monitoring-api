package com.n26.finance.monitoring.api.model.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 *
 */
public class JsonUtil {

	/**
	 *
	 */
	private final ObjectMapper objectMapper;

	/**
	 *
	 */
	private JsonUtil() {
		objectMapper = new ObjectMapper();
//		objectMapper.setTimeZone(TimeZone.getDefault());
//		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
//		objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	/**
	 *
	 */
	private static class Singleton {
		private static final JsonUtil INSTANCE = new JsonUtil();
	}

	/**
	 *
	 * @return
	 */
	public static JsonUtil getInstance() {
		return Singleton.INSTANCE;
	}

	/**
	 *
	 * @param target
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String toJson(Object target) throws JsonProcessingException {
		return getInstance().objectMapper.writeValueAsString(target);
	}

	/**
	 *
	 * @param json
	 * @param returningClass
	 * @param <R>
	 * @return
	 * @throws IOException
	 */
	public static <R> R fromJson(String json, Class<R> returningClass) throws IOException {
		return getInstance().objectMapper.readValue(json, returningClass);
	}
}

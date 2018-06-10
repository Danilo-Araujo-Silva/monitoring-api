package com.n26.finance.monitoring.api.model.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.TimeZone;

/**
 *	A class with useful methods to operate JSONs.
 *	Created using thread-safe singleton approach.
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
		objectMapper.setTimeZone(TimeZone.getTimeZone("UTC"));
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
	 *	Converts an object to a JSON string.
	 *
	 * @param target
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String toJson(Object target) throws JsonProcessingException {
		return getInstance().objectMapper.writeValueAsString(target);
	}

	/**
	 *	Convert a JSON string to an object.
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

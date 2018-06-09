package com.n26.finance.monitoring.api.model.repository;

/**
 *
 */
public class Repository {

	/**
	 *
	 */
	private Repository() {
	}

	/**
	 *
	 */
	private static class Singleton {
		private static final Repository INSTANCE = new Repository();
	}

	/**
	 *
	 * @return
	 */
	public static Repository getInstance() {
		return Singleton.INSTANCE;
	}
}

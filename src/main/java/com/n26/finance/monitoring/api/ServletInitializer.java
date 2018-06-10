package com.n26.finance.monitoring.api;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Class to configure the servlets initialization.
 */
public class ServletInitializer extends SpringBootServletInitializer {

	/**
	 *	Method to configure the servlet initialization. It calls the main application class.
	 *
	 * @param application
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
}

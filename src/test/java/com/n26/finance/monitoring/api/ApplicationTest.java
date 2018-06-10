package com.n26.finance.monitoring.api;

import com.n26.finance.monitoring.api.model.test.AbstractTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The main application test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest extends AbstractTest {

	/**
	 * Check if the application context loads.
	 */
	@Test
	public void contextLoads() {
	}

}

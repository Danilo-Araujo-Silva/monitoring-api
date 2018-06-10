package com.n26.finance.monitoring.api.model.repository;

import com.n26.finance.monitoring.api.model.configuration.Properties;
import com.n26.finance.monitoring.api.model.pojo.StatisticPOJO;
import com.n26.finance.monitoring.api.model.pojo.TransactionPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.LinkedList;

/**
 *
 */
@Component
public class Repository {

	/**
	 *
	 */
	@Autowired
	private Properties properties;

	/**
	 *
	 */
	private LinkedList<StatisticPOJO> list = new LinkedList<>();

	/**
	 *
	 */
	@PostConstruct
	private void initialize() {
		Integer length = properties.getSecondsThreshold() + properties.getSecondsBreathe();

		for (Integer i = 0; i < length; i++) {
			StatisticPOJO statisticPOJO = getDefaultPojo();

			getList().add(statisticPOJO);
		}
	}

	/**
	 *
	 * @return
	 */
	public synchronized LinkedList<StatisticPOJO> getList() {
		return list;
	}

	/**
	 *
	 * @return
	 */
	public StatisticPOJO getDefaultPojo() {
		return new StatisticPOJO(0d, 0d, 0d, Double.POSITIVE_INFINITY, 0L);
	}

	/**
	 *
	 * @return
	 */
	public StatisticPOJO removeLast() {
		return getList().removeLast();
	}

	/**
	 *
	 * @param timestamp
	 */
	public void add(Long timestamp) {
		Integer position = getPosition(timestamp);

		if (getList().get(position) == null) {
			getList().add(position, getDefaultPojo());
		}
	}

	/**
	 *
	 * @param transactionPOJO
	 */
	public void add(TransactionPOJO transactionPOJO) {
		Integer position = getPosition(transactionPOJO.getTimestamp());

		StatisticPOJO statisticPOJO = getList().get(position);
		if (statisticPOJO == null) {
			statisticPOJO = getDefaultPojo();
			getList().add(position, statisticPOJO);
		}

		statisticPOJO.setSum(statisticPOJO.getSum() + transactionPOJO.getAmount());
		statisticPOJO.setCount(statisticPOJO.getCount() + 1);

		if (transactionPOJO.getAmount() > statisticPOJO.getMax()) {
			statisticPOJO.setMax(transactionPOJO.getAmount());
		}

		if (transactionPOJO.getAmount() < statisticPOJO.getMin()) {
			statisticPOJO.setMin(transactionPOJO.getAmount());
		}

		if (statisticPOJO.getCount() > 0) {
			statisticPOJO.setAvg(statisticPOJO.getSum() / statisticPOJO.getCount());
		}
	}

	/**
	 *
	 * @return
	 */
	public StatisticPOJO addFirst() {
		return addFirst(getDefaultPojo());
	}

	/**
	 *
	 * @param statisticPOJO
	 * @return
	 */
	public StatisticPOJO addFirst(StatisticPOJO statisticPOJO) {
		getList().addFirst(statisticPOJO);

		return statisticPOJO;
	}

	/**
	 * Find the element position adding a breathe for it.
	 *
	 * Note: considering that the timestamp does not exceed the limits of an integer.
	 *
	 * @param timestamp
	 * @return
	 */
	public Integer getPosition(Long timestamp) {
		return Math.max(0, (int) ((timestamp - Instant.now().toEpochMilli()) / 1000)) + properties.getSecondsBreathe();
	}

	/**
	 *
	 * @return
	 */
	public StatisticPOJO getCurrentSummary() {
		StatisticPOJO summary = getDefaultPojo();

		Integer start = properties.getSecondsBreathe();
		Integer end =  start + properties.getSecondsThreshold();

		for (Integer i = start; i < end; i++) {
			StatisticPOJO item = getList().get(i);

			summary.setSum(summary.getSum() + item.getSum());
			summary.setCount(summary.getCount() + item.getCount());

			if (item.getMax() > summary.getMax()) {
				summary.setMax(item.getMax());
			}

			if (item.getMin() < summary.getMin()) {
				summary.setMin(item.getMin());
			}
		}

		if (summary.getCount() > 0) {
			summary.setAvg(summary.getSum() / summary.getCount());
		}

		return summary;
	}

	/**
	 *
	 */
	@Scheduled(fixedRateString = "${n26.finance.monitoring.api.maintain-interval}")
	private void maintain() {
		addFirst();
		removeLast();
	}
}

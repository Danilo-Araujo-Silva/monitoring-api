package com.n26.finance.monitoring.api.model.repository;

import com.n26.finance.monitoring.api.model.configuration.Properties;
import com.n26.finance.monitoring.api.model.pojo.StatisticPOJO;
import com.n26.finance.monitoring.api.model.pojo.TransactionPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * An in memory repository to store transactions statistics information.
 */
@Component
public class Repository {

	/**
	 *
	 */
	@Autowired
	private Properties properties;

	/**
	 * An list with transaction data.
	 */
	private CopyOnWriteArrayList<StatisticPOJO> list;

	/**
	 * The method that initialize the repository.
	 */
	@PostConstruct
	private void initialize() {
		list = new CopyOnWriteArrayList<>();

		Integer length = properties.getLength() + properties.getSpace();

		for (Integer i = 0; i < length; i++) {
			StatisticPOJO statisticPOJO = getDefaultPojo();

			getList().add(statisticPOJO);
		}
	}

	/**
	 *	Gets the repository list.
	 *
	 * @return
	 */
	public CopyOnWriteArrayList<StatisticPOJO> getList() {
		return list;
	}

	/**
	 *	A method that create a default statistic POJO to be used.
	 *
	 * @return
	 */
	public StatisticPOJO getDefaultPojo() {
		return new StatisticPOJO(0d, 0d, 0d, Double.POSITIVE_INFINITY, 0L);
	}

	/**
	 *	Remove the last element of the list.
	 *
	 * @return
	 */
	public StatisticPOJO removeLast() {
		return getList().remove(getList().size() - 1);
	}

	/**
	 *	Add a new element to the list by positioning it according to a timestamp.
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
	 *	Add a new transaction to the list, finding its correct place.
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
	 *	Add a new element at the beginning of the list.
	 *
	 * @return
	 */
	public StatisticPOJO addFirst() {
		return addFirst(getDefaultPojo());
	}

	/**
	 *	Add a new statistic pojo at the beginning of the list.
	 *
	 * @param statisticPOJO
	 * @return
	 */
	public StatisticPOJO addFirst(StatisticPOJO statisticPOJO) {
		getList().add(0, statisticPOJO);

		return statisticPOJO;
	}

	/**
	 * Find the position of an element receding it into a certain clearence.
	 *
	 * Note: this method assumes that the timestamp value does not exceed the limits of an integer.
	 *
	 * @param timestamp
	 * @return
	 */
	public Integer getPosition(Long timestamp) {
		return Math.max(0, (int) ((timestamp - Instant.now().toEpochMilli()) / properties.getUnit())) + properties.getSpace();
	}

	/**
	 *	Get the most up-to-date statistical summary.
	 *	Summarize the information of the present moment until the threshold.
	 *
	 * @return
	 */
	public StatisticPOJO getCurrentSummary() {
		StatisticPOJO summary = getDefaultPojo();

		Integer start = properties.getSpace();
		Integer end =  start + properties.getLength();

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
	 *	An automated task to maintain the list.
	 *	It adds and removes elements from the list to each unit of time.
	 */
	@Scheduled(fixedRateString = "${n26.finance.monitoring.api.repository.unit}")
	private void maintain() {
		addFirst();
		removeLast();
	}
}

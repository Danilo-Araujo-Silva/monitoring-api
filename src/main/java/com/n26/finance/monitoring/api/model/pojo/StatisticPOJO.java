package com.n26.finance.monitoring.api.model.pojo;

/**
 * POJO representing a statistic endpoint response.
 */
public class StatisticPOJO extends AbstractPOJO {

	/**
	 * Sum.
	 */
	private Double sum;

	/**
	 * Average.
	 */
	private Double avg;

	/**
	 * Maximum value.
	 */
	private Double max;

	/**
	 * Minimum value.
	 */
	private Double min;

	/**
	 * Quantity.
	 */
	private Long count;

	/**
	 *
	 * @param sum
	 * @param avg
	 * @param max
	 * @param min
	 * @param count
	 */
	public StatisticPOJO(Double sum, Double avg, Double max, Double min, Long count) {
		this.sum = sum;
		this.avg = avg;
		this.max = max;
		this.min = min;
		this.count = count;
	}

	/**
	 *
	 */
	public StatisticPOJO() {
	}

	/**
	 *
	 * @return
	 */
	public Double getSum() {
		return sum;
	}

	/**
	 *
	 * @param sum
	 */
	public void setSum(Double sum) {
		this.sum = sum;
	}

	/**
	 *
	 * @return
	 */
	public Double getAvg() {
		return avg;
	}

	/**
	 *
	 * @param avg
	 */
	public void setAvg(Double avg) {
		this.avg = avg;
	}

	/**
	 *
	 * @return
	 */
	public Double getMax() {
		return max;
	}

	/**
	 *
	 * @param max
	 */
	public void setMax(Double max) {
		this.max = max;
	}

	/**
	 *
	 * @return
	 */
	public Double getMin() {
		return min;
	}

	/**
	 *
	 * @param min
	 */
	public void setMin(Double min) {
		this.min = min;
	}

	/**
	 *
	 * @return
	 */
	public Long getCount() {
		return count;
	}

	/**
	 *
	 * @param count
	 */
	public void setCount(Long count) {
		this.count = count;
	}
}

package com.n26.finance.monitoring.api.model.pojo;

/**
 *
 */
public class StatisticPOJO extends AbstractPOJO {

	/**
	 *
	 */
	private Double sum;

	/**
	 *
	 */
	private Double avg;

	/**
	 *
	 */
	private Double max;

	/**
	 *
	 */
	private Double min;

	/**
	 *
	 */
	private Long count;

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

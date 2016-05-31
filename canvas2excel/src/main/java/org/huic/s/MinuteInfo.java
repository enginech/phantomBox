package org.huic.s;
/**
 * @author chenhui
 * @date 2016年5月30日
 */
public class MinuteInfo {
	private String time;
	private String nowPrice;
	private String avgPrice;
	private String quantity;
	private String margin;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(String nowPrice) {
		this.nowPrice = nowPrice;
	}
	public String getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(String avgPrice) {
		this.avgPrice = avgPrice;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getMargin() {
		return margin;
	}
	public void setMargin(String margin) {
		this.margin = margin;
	}
	
	@Override
	public String toString() {
		return String.format("time: %s, nowP: %s, avgP: %s, quantity: %s, margin: %s", time, nowPrice, avgPrice, quantity, margin);
	}
}

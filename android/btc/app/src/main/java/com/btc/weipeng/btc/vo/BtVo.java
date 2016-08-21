package com.btc.weipeng.btc.vo;

/**
 * Created by weipeng on 16/8/11.
 */
public class BtVo {
    private String name;
    private String currentIsBuy;
    private double currentPrice;
    private double dayNumber;
    private double high;
    private double low;
    private double totalBtc;
    private Long lastTime;
    private boolean isUp;


    public String getCurrentIsBuy() {
        return currentIsBuy;
    }

    public void setCurrentIsBuy(String currentIsBuy) {
        this.currentIsBuy = currentIsBuy;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(double dayNumber) {
        this.dayNumber = dayNumber;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getTotalBtc() {
        return totalBtc;
    }

    public void setTotalBtc(double totalBtc) {
        this.totalBtc = totalBtc;
    }

    public Long getLastTime() {
        return lastTime;
    }

    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }


    public boolean isUp() {
        return isUp;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BtVo{" +
                "name='" + name + '\'' +
                ", currentIsBuy='" + currentIsBuy + '\'' +
                ", currentPrice=" + currentPrice +
                ", dayNumber=" + dayNumber +
                ", high=" + high +
                ", low=" + low +
                ", totalBtc=" + totalBtc +
                ", lastTime=" + lastTime +
                ", isUp=" + isUp +
                '}';
    }
}

package com.angus.model;

import java.util.Calendar;
import java.util.Date;

import com.alibaba.fastjson.JSON;

public class Log {
	// status
	public static final int STATUS_INIT = -1;
	public static final int STATUS_WAITPAY = 0;
	public static final int STATUS_NOTIFY_SUCCESS = 1;
	public static final int STATUS_BOOKED = 2;
	public static final int STATUS_FAIL = 3;
	public static final int STATUS_CREATED = 4;
	public static final int STATUS_GAME_REJECT = 5;
	public static final int STATUS_NOTIFY_FAIL = 6;
	// 98，产品编号不一致，有可能被攻击
	public static final int STATUS_EXCEPTION = 98;
	public static final int STATUS_DISCARD = 99;

	// CHARGE_LOG_ID NUMBER(20) 充值流水ID 主键
	private long chargeLogId;
	// TRADE_NO VARCHAR2(64) XGSDK订单号 主键
	private String tradeNo;
	// CHANNEL_APP_ID VARCHAR2(64) 运营渠道分配的游戏ID
	private String channelAppId;
	// UID VARCHAR2(128) 渠道的用户编号
	private String uid;
	// ZONE_ID VARCHAR2(32) 玩家游戏区ID
	private String zoneId;
	// SERVER_ID VARCHAR2(32) 玩家游戏服ID
	private String serverId;
	// ROLE_ID VARCHAR2(32) 游戏角色编号
	private String roleId;
	// ROLE_NAME VARCHAR2(64) 角色名称
	private String roleName;
	// ROLE_LEVEL VARCHAR2(32) 角色等级
	private String roleLevel;
	// ROLE_VIP_LEVEL VARCHAR2(32) 角色VIP等级
	private String roleVipLevel;
	// CURRENCY_NAME VARCHAR2(64) 支付货币名称
	private String currencyName;
	// CHANNEL_ID VARCHAR2(32) 运营渠道编号
	private String channelId;
	// PRODUCT_ID VARCHAR2(64) 商品编号
	private String productId;
	// PRODUCT_NAME VARCHAR2(64) 商品名称
	private String productName;
	// PRODUCT_DESC VARCHAR2(128) 商品描述
	private String productDesc;
	// PRODUCT_QUANTITY NUMBER(10) 商品数量
	private int productQuantity;
	// PRODUCT_UNIT_PRICE NUMBER(10) 商品单价(单位分)
	private int productUnitPrice;
	// TOTAL_AMOUNT NUMBER(10) 总面额(单位分)
	private int totalAmount;
	// PAID_AMOUNT NUMBER(10) 总支付金额(单位分)
	private int paidAmount;
	// CREATE_TIME DATE 订单创建时间
	private Date createTime;
	// PAID_TIME DATE 订单支付时间
	private Date paidTime;
	// FINISH_TIME DATE 游戏到账时间
	private Date finishTime;
	// STATUS NUMBER(2) 交易状态
	// -1 初始状态(client还未返回支付成功更新，超过1天后，将会搬迁到作废日志表)
	// 0 等待支付(client已经返回支付成功更新)
	// 1 待通知成功
	// 2 支付成功，已入账
	// 3 支付失败
	// 4 生成二维码
	// 5 游戏拒绝接收
	// 6 待通知失败
	// 98 异常，疑似被攻击
	// 99 作废
	private int status;
	// CHARGE_CHANNEL_ID VARCHAR(64) 支付渠道ID
	private String chargeChannelId;
	// CHARGE_CHANNEL_TYPE VARCHAR2(128) 支付渠道类型(银联或支付宝)
	private String chargeChannelType;
	// CHARGE_CHANNEL_INST VARCHAR2(128) 支付渠道详细信息(中国工商银行)
	private String chargeChannelInst;
	// CHANNEL_TRADE_NO VARCHAR(64) 渠道订单号
	private String channelTradeNo;
	// SEARCH_CHANNEL_ORDER_TIMES NUMBER(2)
	// 查询渠道订单状态次数(以1分钟为基数，次方递增，1分钟轮询一次，2、4、8、16...分钟继续轮询）
	private int searchChannelOrderTimes;
	// GAME_NOTIFY_TIMES NUMBER(2)
	// 通知游戏次数(以1分钟为基数，次方递增，1分钟轮询一次，2、4、8、16...分钟继续轮询）
	private int gameNotifyTimes;
	// XG_APP_ID VARCHAR2(64) XGSDK分配的游戏编号
	private String xgAppId;
	// PLAN_ID VARCHAR(20) 发布计划编号 必输
	private String planId;
	// BUILD_NUMBER VARCHAR2(64) XGSDK发布小版本号
	private String buildNumber;
	// DEVICE_ID VARCHAR2(100) 设备编号
	private String deviceId;
	// DEVICE_IP VARCHAR2(128) 设备IP
	private String deviceIp;
	// DEVICE_BRAND VARCHAR2(100) 设备提供商： 小米、华为、三星、苹果
	private String deviceBrand;
	// DEVICE_MODEL VARCHAR2(100) 设备型号：小米note、华为meta7 iphone 6 plus等
	private String deviceModel;
	// GAME_TRADE_NO VARCHAR2(64) 游戏订单号
	private String gameTradeNo;
	// GAME_CALLBACK_URL VARCHAR2(128) 游戏回调地址
	private String gameCallbackUrl;
	// CUSTOM_INFO VARCHAR2(2000) 游戏方自定义字段
	private String customInfo;
	// VOUCHER_AMOUNT NUMBER(1) 代金券金额
	private int voucherAmount;
	// EXCEPTION_INFO VARCHAR2(256) 异常信息
	private String exceptionInfo;
	// TOKEN_ID VARCHAR2(512) 渠道登陆会话编号
	private String tokenId;
	// CHANNEL_BONUS_AMOUNT NUMBER(10) 渠道赠送金额(单位分)
	private int channelBonusAmount;
	// XG_CUSTOM_INFO VARCHAR2(2000) xg自定义字段
	private String xgCustomInfo;
	// STATE_CODE VARCHAR2(16) 异常错误码
	private String stateCode = "";

	/**
	 * @return the chargeLogId
	 */
	public long getChargeLogId() {
		return chargeLogId;
	}

	/**
	 * @param chargeLogId
	 *            the chargeLogId to set
	 */
	public void setChargeLogId(long chargeLogId) {
		this.chargeLogId = chargeLogId;
	}

	/**
	 * @return the tradeNo
	 */
	public String getTradeNo() {
		return tradeNo;
	}

	/**
	 * @param tradeNo
	 *            the tradeNo to set
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	/**
	 * @return the channelAppId
	 */
	public String getChannelAppId() {
		return channelAppId;
	}

	/**
	 * @param channelAppId
	 *            the channelAppId to set
	 */
	public void setChannelAppId(String channelAppId) {
		this.channelAppId = channelAppId;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the zoneId
	 */
	public String getZoneId() {
		return zoneId;
	}

	/**
	 * @param zoneId
	 *            the zoneId to set
	 */
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	/**
	 * @return the serverId
	 */
	public String getServerId() {
		return serverId;
	}

	/**
	 * @param serverId
	 *            the serverId to set
	 */
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the roleLevel
	 */
	public String getRoleLevel() {
		return roleLevel;
	}

	/**
	 * @param roleLevel
	 *            the roleLevel to set
	 */
	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}

	/**
	 * @return the roleVipLevel
	 */
	public String getRoleVipLevel() {
		return roleVipLevel;
	}

	/**
	 * @param roleVipLevel
	 *            the roleVipLevel to set
	 */
	public void setRoleVipLevel(String roleVipLevel) {
		this.roleVipLevel = roleVipLevel;
	}

	/**
	 * @return the currencyName
	 */
	public String getCurrencyName() {
		return currencyName;
	}

	/**
	 * @param currencyName
	 *            the currencyName to set
	 */
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	/**
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId
	 *            the channelId to set
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productDesc
	 */
	public String getProductDesc() {
		return productDesc;
	}

	/**
	 * @param productDesc
	 *            the productDesc to set
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	/**
	 * @return the productQuantity
	 */
	public int getProductQuantity() {
		return productQuantity;
	}

	/**
	 * @param productQuantity
	 *            the productQuantity to set
	 */
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	/**
	 * @return the productUnitPrice
	 */
	public int getProductUnitPrice() {
		return productUnitPrice;
	}

	/**
	 * @param productUnitPrice
	 *            the productUnitPrice to set
	 */
	public void setProductUnitPrice(int productUnitPrice) {
		this.productUnitPrice = productUnitPrice;
	}

	/**
	 * @return the totalAmount
	 */
	public int getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount
	 *            the totalAmount to set
	 */
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the paidAmount
	 */
	public int getPaidAmount() {
		return paidAmount;
	}

	/**
	 * @param paidAmount
	 *            the paidAmount to set
	 */
	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the paidTime
	 */
	public Date getPaidTime() {
		return paidTime;
	}

	/**
	 * @param paidTime
	 *            the paidTime to set
	 */
	public void setPaidTime(Date paidTime) {
		this.paidTime = paidTime;
	}

	/**
	 * @return the finishTime
	 */
	public Date getFinishTime() {
		return finishTime;
	}

	/**
	 * @param finishTime
	 *            the finishTime to set
	 */
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the chargeChannelId
	 */
	public String getChargeChannelId() {
		return chargeChannelId;
	}

	/**
	 * @param chargeChannelId
	 *            the chargeChannelId to set
	 */
	public void setChargeChannelId(String chargeChannelId) {
		this.chargeChannelId = chargeChannelId;
	}

	/**
	 * @return the chargeChannelType
	 */
	public String getChargeChannelType() {
		return chargeChannelType;
	}

	/**
	 * @param chargeChannelType
	 *            the chargeChannelType to set
	 */
	public void setChargeChannelType(String chargeChannelType) {
		this.chargeChannelType = chargeChannelType;
	}

	/**
	 * @return the chargeChannelInst
	 */
	public String getChargeChannelInst() {
		return chargeChannelInst;
	}

	/**
	 * @param chargeChannelInst
	 *            the chargeChannelInst to set
	 */
	public void setChargeChannelInst(String chargeChannelInst) {
		this.chargeChannelInst = chargeChannelInst;
	}

	/**
	 * @return the channelTradeNo
	 */
	public String getChannelTradeNo() {
		return channelTradeNo;
	}

	/**
	 * @param channelTradeNo
	 *            the channelTradeNo to set
	 */
	public void setChannelTradeNo(String channelTradeNo) {
		this.channelTradeNo = channelTradeNo;
	}

	/**
	 * @return the searchChannelOrderTimes
	 */
	public int getSearchChannelOrderTimes() {
		return searchChannelOrderTimes;
	}

	/**
	 * @param searchChannelOrderTimes
	 *            the searchChannelOrderTimes to set
	 */
	public void setSearchChannelOrderTimes(int searchChannelOrderTimes) {
		this.searchChannelOrderTimes = searchChannelOrderTimes;
	}

	/**
	 * @return the gameNotifyTimes
	 */
	public int getGameNotifyTimes() {
		return gameNotifyTimes;
	}

	/**
	 * @param gameNotifyTimes
	 *            the gameNotifyTimes to set
	 */
	public void setGameNotifyTimes(int gameNotifyTimes) {
		this.gameNotifyTimes = gameNotifyTimes;
	}

	/**
	 * @return the xgAppId
	 */
	public String getXgAppId() {
		return xgAppId;
	}

	/**
	 * @param xgAppId
	 *            the xgAppId to set
	 */
	public void setXgAppId(String xgAppId) {
		this.xgAppId = xgAppId;
	}

	/**
	 * @return the planId
	 */
	public String getPlanId() {
		return planId;
	}

	/**
	 * @param planId
	 *            the planId to set
	 */
	public void setPlanId(String planId) {
		this.planId = planId;
	}

	/**
	 * @return the buildNumber
	 */
	public String getBuildNumber() {
		return buildNumber;
	}

	/**
	 * @param buildNumber
	 *            the buildNumber to set
	 */
	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId
	 *            the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the deviceIp
	 */
	public String getDeviceIp() {
		return deviceIp;
	}

	/**
	 * @param deviceIp
	 *            the deviceIp to set
	 */
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	/**
	 * @return the deviceBrand
	 */
	public String getDeviceBrand() {
		return deviceBrand;
	}

	/**
	 * @param deviceBrand
	 *            the deviceBrand to set
	 */
	public void setDeviceBrand(String deviceBrand) {
		this.deviceBrand = deviceBrand;
	}

	/**
	 * @return the deviceModel
	 */
	public String getDeviceModel() {
		return deviceModel;
	}

	/**
	 * @param deviceModel
	 *            the deviceModel to set
	 */
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	/**
	 * @return the gameTradeNo
	 */
	public String getGameTradeNo() {
		return gameTradeNo;
	}

	/**
	 * @param gameTradeNo
	 *            the gameTradeNo to set
	 */
	public void setGameTradeNo(String gameTradeNo) {
		this.gameTradeNo = gameTradeNo;
	}

	/**
	 * @return the gameCallbackUrl
	 */
	public String getGameCallbackUrl() {
		return gameCallbackUrl;
	}

	/**
	 * @param gameCallbackUrl
	 *            the gameCallbackUrl to set
	 */
	public void setGameCallbackUrl(String gameCallbackUrl) {
		this.gameCallbackUrl = gameCallbackUrl;
	}

	/**
	 * @return the customInfo
	 */
	public String getCustomInfo() {
		return customInfo;
	}

	/**
	 * @param customInfo
	 *            the customInfo to set
	 */
	public void setCustomInfo(String customInfo) {
		this.customInfo = customInfo;
	}

	/**
	 * @return the voucherAmount
	 */
	public int getVoucherAmount() {
		return voucherAmount;
	}

	/**
	 * @param voucherAmount
	 *            the voucherAmount to set
	 */
	public void setVoucherAmount(int voucherAmount) {
		this.voucherAmount = voucherAmount;
	}

	/**
	 * @return the exceptionInfo
	 */
	public String getExceptionInfo() {
		return exceptionInfo;
	}

	/**
	 * @param exceptionInfo
	 *            the exceptionInfo to set
	 */
	public void setExceptionInfo(String exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}

	/**
	 * @return the tokenId
	 */
	public String getTokenId() {
		return tokenId;
	}

	/**
	 * @param tokenId
	 *            the tokenId to set
	 */
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	/**
	 * @return the channelBonusAmount
	 */
	public int getChannelBonusAmount() {
		return channelBonusAmount;
	}

	/**
	 * @param channelBonusAmount
	 *            the channelBonusAmount to set
	 */
	public void setChannelBonusAmount(int channelBonusAmount) {
		this.channelBonusAmount = channelBonusAmount;
	}

	/**
	 * @return the xgCustomInfo
	 */
	public String getXgCustomInfo() {
		return xgCustomInfo;
	}

	/**
	 * @param xgCustomInfo
	 *            the xgCustomInfo to set
	 */
	public void setXgCustomInfo(String xgCustomInfo) {
		this.xgCustomInfo = xgCustomInfo;
	}

	/**
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * @param stateCode
	 *            the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	/**
	 * @param tableNamePostfix
	 *            the tableNamePostfix to set
	 */
	public void setTableNamePostfix(String tableNamePostfix) {
		this.tableNamePostfix = tableNamePostfix;
	}

	private static final char[] MONTH_CHAR = { '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c' };

	public static String getCurrentTradeNo(long chargeLogId) {
		int month = Calendar.getInstance().get(Calendar.MONTH);
		return "" + MONTH_CHAR[month] + chargeLogId;
	}

	public static String getTradeNoPrefix(int tableNamePostfix) {
		return "" + MONTH_CHAR[tableNamePostfix - 1];
	}

	private String tableNamePostfix;

	public String getTableNamePostfix() {
		return getRechargeTableNamePostfix(tradeNo);
	}

	public static String getRechargeTableNamePostfix(String tradeNo) {
		if (tradeNo == null || tradeNo.length() < 1) {
			return "1";
		}
		char first = tradeNo.charAt(0);

		if (first >= '1' && first <= '9')
			return String.valueOf(first);
		if (first >= 'a' && first <= 'c')
			return String.valueOf(first - 'a' + 10);
		if (first >= 'A' && first <= 'C')
			return String.valueOf(first - 'A' + 10);

		return "1";
	}

	public void setTableNamePostfix(int tableNamePostfix) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}

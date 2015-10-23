/**
 * 
 */
package com.angus.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.angus.model.Log;

/**
 * @author LUCHUNLIANG
 *
 */
public interface LogMapper {

	@Insert("insert into recharge_log_${tableNamePostfix} (charge_log_id,trade_no,channel_app_id,uid,zone_id,server_id,role_id,role_name,role_level,role_vip_level,currency_name,channel_id,product_id,product_name,product_desc,product_quantity,product_unit_price,total_amount,paid_amount,create_time,paid_time,finish_time,status,charge_channel_id,charge_channel_type,charge_channel_inst,channel_trade_no,search_channel_order_times,game_notify_times,xg_app_id,plan_id,build_number,device_id,device_ip,device_brand,device_model,game_trade_no,game_callback_url,custom_info,voucher_amount,exception_info,token_id,channel_bonus_amount,xg_custom_info,state_code) "
			+ "values (#{chargeLogId}, #{tradeNo}, #{channelAppId,jdbcType=VARCHAR},#{uid,jdbcType=VARCHAR},#{zoneId,jdbcType=VARCHAR},#{serverId,jdbcType=VARCHAR},#{roleId,jdbcType=VARCHAR},"
			+ "#{roleName,jdbcType=VARCHAR},#{roleLevel,jdbcType=VARCHAR},#{roleVipLevel,jdbcType=VARCHAR},#{currencyName,jdbcType=VARCHAR},#{channelId,jdbcType=VARCHAR},#{productId,jdbcType=VARCHAR},"
			+ "#{productName,jdbcType=VARCHAR},#{productDesc,jdbcType=VARCHAR},#{productQuantity},#{productUnitPrice},#{totalAmount},#{paidAmount},#{createTime},#{paidTime},now(),#{status},"
			+ "#{chargeChannelId,jdbcType=VARCHAR},#{chargeChannelType,jdbcType=VARCHAR},#{chargeChannelInst,jdbcType=VARCHAR},#{channelTradeNo,jdbcType=VARCHAR},#{searchChannelOrderTimes},"
			+ "#{gameNotifyTimes},#{xgAppId,jdbcType=VARCHAR},#{planId,jdbcType=VARCHAR},#{buildNumber,jdbcType=VARCHAR},#{deviceId,jdbcType=VARCHAR},#{deviceIp,jdbcType=VARCHAR},"
			+ "#{deviceBrand,jdbcType=VARCHAR},#{deviceModel,jdbcType=VARCHAR},#{gameTradeNo,jdbcType=VARCHAR},#{gameCallbackUrl,jdbcType=VARCHAR},#{customInfo,jdbcType=VARCHAR},#{voucherAmount},#{exceptionInfo,jdbcType=VARCHAR},#{tokenId,jdbcType=VARCHAR},#{channelBonusAmount},#{xgCustomInfo,jdbcType=VARCHAR},#{stateCode,jdbcType=VARCHAR} )")
	int create(Log rechargeLog);

	@Select("select charge_log_id,trade_no,channel_app_id,uid,zone_id,server_id,role_id,role_name,role_level,role_vip_level,currency_name,channel_id,product_id,product_name,product_desc,product_quantity,product_unit_price,total_amount,paid_amount,create_time,paid_time,finish_time,status,charge_channel_id,charge_channel_type,charge_channel_inst,channel_trade_no,search_channel_order_times,game_notify_times,xg_app_id,plan_id,build_number,device_id,device_ip,device_brand,device_model,game_trade_no,game_callback_url,custom_info,voucher_amount,exception_info,token_id,channel_bonus_amount,xg_custom_info,state_code "
			+ " from recharge_log_${tableNamePostfix} " + " where trade_no=#{tradeNo} limit 1")
	Log queryByTradeNo(Log rechargeLog);

}

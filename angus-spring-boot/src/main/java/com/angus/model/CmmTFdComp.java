/**
 * 
 */
package com.angus.model;

import com.alibaba.fastjson.JSON;

/**
 * @author weipeng 组件配置CMMTFDCOMP
 */
public class CmmTFdComp {
	// FUND_CORG,PRD_TYP,TX_TYP,COMP_NM,COMP_DESC,PORTAL,TM_SMP
	private String fundCorg;
	private String prdTyp;
	private String txTyp;
	private String compNm;
	private String compDesc;
	private String portal;
	private String tmSmp;

	/**
	 * @return the fundCorg
	 */
	public String getFundCorg() {
		return fundCorg;
	}

	/**
	 * @param fundCorg
	 *            the fundCorg to set
	 */
	public void setFundCorg(String fundCorg) {
		this.fundCorg = fundCorg;
	}

	/**
	 * @return the prdTyp
	 */
	public String getPrdTyp() {
		return prdTyp;
	}

	/**
	 * @param prdTyp
	 *            the prdTyp to set
	 */
	public void setPrdTyp(String prdTyp) {
		this.prdTyp = prdTyp;
	}

	/**
	 * @return the txTyp
	 */
	public String getTxTyp() {
		return txTyp;
	}

	/**
	 * @param txTyp
	 *            the txTyp to set
	 */
	public void setTxTyp(String txTyp) {
		this.txTyp = txTyp;
	}

	/**
	 * @return the compNm
	 */
	public String getCompNm() {
		return compNm;
	}

	/**
	 * @param compNm
	 *            the compNm to set
	 */
	public void setCompNm(String compNm) {
		this.compNm = compNm;
	}

	/**
	 * @return the compDesc
	 */
	public String getCompDesc() {
		return compDesc;
	}

	/**
	 * @param compDesc
	 *            the compDesc to set
	 */
	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}

	/**
	 * @return the portal
	 */
	public String getPortal() {
		return portal;
	}

	/**
	 * @param portal
	 *            the portal to set
	 */
	public void setPortal(String portal) {
		this.portal = portal;
	}

	/**
	 * @return the tmSmp
	 */
	public String getTmSmp() {
		return tmSmp;
	}

	/**
	 * @param tmSmp
	 *            the tmSmp to set
	 */
	public void setTmSmp(String tmSmp) {
		this.tmSmp = tmSmp;
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

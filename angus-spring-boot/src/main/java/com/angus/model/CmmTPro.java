/**
 * 
 */
package com.angus.model;

import com.alibaba.fastjson.JSON;

/**
 * @author weipeng
 *
 */
public class CmmTPro {
	// PRD_ID, FUND_CORG, ORG_PRD_ID, PRD_TYP, PRD_NM, PRD_ABBR, PRD_STS,
	// PRD_TOT_AMT, PRD_TOT_CNT,
	// PRD_PRT_AMT, PRD_RAT, PERD_TYP, PERD_DAY, PERD_MAX_CNT, PURC_MAX_CNT,
	// PURC_MIN_CNT, BRT_DAY, PURC_BEG_DT, PURC_BEG_TM,
	// PURC_END_DT, PURC_END_TM, PURC_TOT_CNT, PURC_TOT_AMT, HOLD_TYP,
	// HOLD_PERD, REDP_TYP, REDP_CTL_FLG, CRE_DT, CRE_TM,
	// LST_UPD_DT, LST_UPD_TM, PRD_DESC_URL, REDP_DESC_URL, PRD_TAG1,
	// MRK_UPD_DT, BUS_STSW, TM_SMP, REPAY_MENT_TYP, FLOAT_RAT,
	// BIND_DESC_URL,extension_mark
	private String prdId;
	private String fundCorg;
	private String prdTyp;

	/**
	 * @return the prdId
	 */
	public String getPrdId() {
		return prdId;
	}

	/**
	 * @param prdId
	 *            the prdId to set
	 */
	public void setPrdId(String prdId) {
		this.prdId = prdId;
	}

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

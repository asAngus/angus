/**
 * 
 */
package com.angus.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.angus.model.CmmTPro;

/**
 * @author weipeng
 *
 */
public interface CmmTProMapper {
	@Select("SELECT PRD_ID ,FUND_CORG ,PRD_TYP FROM CMMTPRO WHERE PRD_ID=#{prdId}")
	CmmTPro query(CmmTPro pro);

	@Select("SELECT PRD_ID ,FUND_CORG ,PRD_TYP FROM CMMTPRO WHERE PRD_TYP=#{prdTyp}")
	List<CmmTPro> queryByPrdType(CmmTPro pro);

	@Select("SELECT PRD_ID ,FUND_CORG ,PRD_TYP FROM CMMTPRO")
	List<CmmTPro> queryAll();

	@Insert("INSERT INTO CMMTPRO (PRD_ID,FUND_CORG,PRD_TYP) VALUES (#{prdId,jdbcType=VARCHAR}, #{fundCorg,jdbcType=VARCHAR}, #{prdTyp,jdbcType=VARCHAR})")
	int saveAndFlush(CmmTPro prd);
}

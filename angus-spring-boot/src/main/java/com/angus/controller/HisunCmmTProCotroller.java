/**
 * 
 */
package com.angus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.angus.dao.mapper.CmmTProMapper;
import com.angus.model.CmmTPro;

/**
 * 产品管理
 * 
 * @author weipeng
 *
 */
@RestController
@RequestMapping("/cmmtpro")
public class HisunCmmTProCotroller {
	@Autowired
	private CmmTProMapper proMapper;

	@RequestMapping(method = RequestMethod.POST, value = "/addPro")
	public int addPro(@RequestBody CmmTPro prd) {
		return proMapper.saveAndFlush(prd);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/find/{prdTyp}/prdTyp")
	public List<CmmTPro> findProByPrdType(@PathVariable String prdTyp) {
		CmmTPro pro = new CmmTPro();
		pro.setPrdTyp(prdTyp);
		return proMapper.queryByPrdType(pro);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public List<CmmTPro> find() {
		List<CmmTPro> query = proMapper.query();

		return query;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/find/{prdId}/prdId")
	public CmmTPro findProByPrdId(@PathVariable String prdId) {
		CmmTPro pro = new CmmTPro();
		pro.setPrdId(prdId);
		return proMapper.query(pro);
	}
}

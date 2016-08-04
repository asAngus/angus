/**
 * 
 */
package com.github.tomakehurst.wiremock.stubbing;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.github.tomakehurst.wiremock.common.Json;

/**
 * @author weipeng
 *
 */
@SuppressWarnings("deprecation")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder({ "mappings" })
public class Mappings {
	private List<StubMapping> mappings;

	/**
	 * @return the mappings
	 */
	public List<StubMapping> getMappings() {
		return mappings;
	}

	/**
	 * @param mappings
	 *            the mappings to set
	 */
	public void setMappings(List<StubMapping> mappings) {
		this.mappings = mappings;
	}

	public static Mappings buildFrom(String mappingSpecJson) {
		return Json.read(mappingSpecJson, Mappings.class);
	}
}

package com.angus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 */
@ConfigurationProperties(prefix = "xgsdk.tomcat", ignoreUnknownFields = true)
public class AccessLogConfig {

	private boolean accessLogEnabled;
	private String accessLogDirectory;
	private String fileDateFormat;
	private String accessLogPattern;
	private int accessLogHistory;
	private String accessLogDateFormat;

	/**
	 * @return the accessLogEnabled
	 */
	public boolean isAccessLogEnabled() {
		return accessLogEnabled;
	}

	/**
	 * @param accessLogEnabled
	 *            the accessLogEnabled to set
	 */
	public void setAccessLogEnabled(boolean accessLogEnabled) {
		this.accessLogEnabled = accessLogEnabled;
	}

	/**
	 * @return the accessLogDirectory
	 */
	public String getAccessLogDirectory() {
		return accessLogDirectory;
	}

	/**
	 * @param accessLogDirectory
	 *            the accessLogDirectory to set
	 */
	public void setAccessLogDirectory(String accessLogDirectory) {
		this.accessLogDirectory = accessLogDirectory;
	}

	/**
	 * @return the fileDateFormat
	 */
	public String getFileDateFormat() {
		return fileDateFormat;
	}

	/**
	 * @param fileDateFormat
	 *            the fileDateFormat to set
	 */
	public void setFileDateFormat(String fileDateFormat) {
		this.fileDateFormat = fileDateFormat;
	}

	/**
	 * @return the accessLogPattern
	 */
	public String getAccessLogPattern() {
		return accessLogPattern;
	}

	/**
	 * @param accessLogPattern
	 *            the accessLogPattern to set
	 */
	public void setAccessLogPattern(String accessLogPattern) {
		this.accessLogPattern = accessLogPattern;
	}

	/**
	 * @return the accessLogHistory
	 */
	public int getAccessLogHistory() {
		return accessLogHistory;
	}

	/**
	 * @param accessLogHistory
	 *            the accessLogHistory to set
	 */
	public void setAccessLogHistory(int accessLogHistory) {
		this.accessLogHistory = accessLogHistory;
	}

	/**
	 * @return the accessLogDateFormat
	 */
	public String getAccessLogDateFormat() {
		return accessLogDateFormat;
	}

	/**
	 * @param accessLogDateFormat
	 *            the accessLogDateFormat to set
	 */
	public void setAccessLogDateFormat(String accessLogDateFormat) {
		this.accessLogDateFormat = accessLogDateFormat;
	}

}

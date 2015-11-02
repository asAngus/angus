/**
 * 
 */
package com.angus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author weipeng
 *
 */
@ConfigurationProperties(prefix = "ssh.config", ignoreUnknownFields = false)
public class SSHConfig {

}

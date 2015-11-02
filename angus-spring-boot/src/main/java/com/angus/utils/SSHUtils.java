/**
 * 
 */
package com.angus.utils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.tools.ant.taskdefs.optional.ssh.SSHExec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.angus.config.SSHConfig;

/**
 * @author weipeng
 *
 */
@EnableConfigurationProperties(SSHConfig.class)
@Service
public class SSHUtils {
	@Autowired
	private SSHConfig sshConfig;
	private SSHExec sshExec;

	@PostConstruct
	public void init() {
		sshExec = new SSHExec();
		sshExec.setHost("");
		sshExec.setUsername("");
		sshExec.setPassword("");
		sshExec.setTrust(true);
	}

	public void sshCommand(String commands) {
		sshExec.setCommand(commands);
		sshExec.execute();
	}

	@PreDestroy
	public void dostory() {
	}
}

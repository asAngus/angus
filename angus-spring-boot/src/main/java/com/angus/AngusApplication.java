package com.angus;

import javax.annotation.PostConstruct;

import org.apache.catalina.valves.AccessLogValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.alibaba.dcm.DnsCacheManipulator;
import com.angus.config.AccessLogConfig;

@EnableAutoConfiguration
@Component
@ComponentScan
@EnableConfigurationProperties(AccessLogConfig.class)
public class AngusApplication {
	@Autowired
	private AccessLogConfig accessLogConfig;

	@PostConstruct
	public void setMockServer() {
		// 设置dns指向
		DnsCacheManipulator.setDnsCache("usrsys.inner.bbk.com", "");
	}

	public static void main(String[] args) {
		SpringApplication.run(AngusApplication.class, args);
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		if (accessLogConfig.isAccessLogEnabled()) {
			AccessLogValve valve = new AccessLogValve();
			valve.setDirectory(accessLogConfig.getAccessLogDirectory());
			valve.setPrefix("");
			valve.setSuffix("");
			valve.setBuffered(false);
			valve.setEnabled(true);
			valve.setEncoding("UTF-8");
			valve.setFileDateFormat(accessLogConfig.getFileDateFormat());
			valve.setPattern(accessLogConfig.getAccessLogPattern());
			factory.addContextValves(valve);
		}
		return factory;
	}
}

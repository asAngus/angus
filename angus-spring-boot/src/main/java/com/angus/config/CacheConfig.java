package com.angus.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@EnableCaching(proxyTargetClass = true)  
@Component
public class CacheConfig {
	
	@Bean
	public CacheManager cacheManager(){
		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("tokenCache");
		return cacheManager;
	}
}

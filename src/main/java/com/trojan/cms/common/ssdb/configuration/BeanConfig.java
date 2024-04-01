package com.trojan.cms.common.ssdb.configuration;


import com.trojan.cms.common.ssdb.SSDBClient;
import com.trojan.cms.common.ssdb.properties.SSDBProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


@Configuration
public class BeanConfig {

    @Resource
    private SSDBProperties ssdbProperties;

    @Bean
    public SSDBConfig ssdbConfig() {
        SSDBConfig ssdbConfig = new SSDBConfig();
        ssdbConfig.setMaxActive(ssdbProperties.getMaxActive());
        ssdbConfig.setTestWhileIdle(true);
        return ssdbConfig;
    }

    @Bean
    public SSDBClient ssdbClient(SSDBConfig ssdbConfig) {
        SSDBClient ssdbClient = new SSDBClient();
        ssdbClient.setHost(ssdbProperties.getHost());
        ssdbClient.setPort(ssdbProperties.getPort());
        ssdbClient.setTimeout(ssdbProperties.getTimeout());
        ssdbClient.setAuth(ssdbProperties.getPassword().getBytes());
        ssdbClient.setConfig(ssdbConfig);
        ssdbClient.init();
        return ssdbClient;
    }
}

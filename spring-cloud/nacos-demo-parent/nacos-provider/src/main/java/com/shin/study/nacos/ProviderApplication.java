package com.shin.study.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: fanAo
 * @date: 2021/7/15 16:04
 */
@SpringBootApplication
// 启用服务发现
//@EnableDiscoveryClient
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

    @RestController
    @RequestMapping("/config")
    // 实时刷新配置
    @RefreshScope
    class ConfigController {

        @Value("${useLocalCache:false}")
        private boolean useLocalCache;

        @RequestMapping("/get")
        public boolean get() {
                return useLocalCache;
        }
    }
}

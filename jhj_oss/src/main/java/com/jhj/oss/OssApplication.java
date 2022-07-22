package com.jhj.oss;

import com.jhj.oss.config.ExclusionUrl;
import com.jhj.oss.config.TestPro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;
/**
 * @author Jeremy
 */
@SpringBootApplication(scanBasePackages = "com.jhj", exclude = FreeMarkerAutoConfiguration.class)
@CrossOrigin
@EnableFeignClients
@EnableJpaAuditing
@EnableDiscoveryClient
@EnableConfigurationProperties({ExclusionUrl.class, TestPro.class})
@EnableScheduling //开启定时任务
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}


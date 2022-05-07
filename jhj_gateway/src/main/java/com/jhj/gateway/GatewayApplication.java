package com.jhj.gateway;


//import com.jhj.gateway.config.ExclusionUrl;
import com.jhj.gateway.config.WhiteList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin
@SpringBootApplication(scanBasePackages = "com.jhj", exclude = {FreeMarkerAutoConfiguration.class, DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
//@EnableConfigurationProperties({ExclusionUrl.class, WhiteList.class})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }


}

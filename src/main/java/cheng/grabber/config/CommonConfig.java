package cheng.grabber.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CommonConfig {

    @Bean
    public String zhipinBaseUrl() {
        return "https://www.zhipin.com/web/geek/job";
    }

    @Bean
    public String zhipinQueryParam() {
        return "query=%s&city=%s&page=%d";
    }

    @Bean(name = "cityMap")
    public Map<String, String> cityMap() {
        return new HashMap<>();
    }
}

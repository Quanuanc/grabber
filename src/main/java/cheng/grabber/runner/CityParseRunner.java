package cheng.grabber.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class CityParseRunner implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ResourceLoader resourceLoader;
    private final Map<String, String> cityMap;

    public CityParseRunner(ResourceLoader resourceLoader, @Qualifier("cityMap") Map<String, String> cityMap) {
        this.resourceLoader = resourceLoader;
        this.cityMap = cityMap;
    }

    @Override
    public void run(String... args) {
        String cityFile = "city.txt";
        Resource resource = resourceLoader.getResource("classpath:" + cityFile);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String key = parts[1].trim().replace("'", "").replace(",", "");
                    String value = parts[0].trim();
                    cityMap.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Parse {} city to map", cityMap.size());
    }
}

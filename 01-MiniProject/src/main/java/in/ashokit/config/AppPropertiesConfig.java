package in.ashokit.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "plan-api")
@Configuration
@Data
public class AppPropertiesConfig {
	Map<String, String> messages = new LinkedHashMap<>();
}

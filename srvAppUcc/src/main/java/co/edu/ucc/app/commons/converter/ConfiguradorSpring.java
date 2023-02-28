package co.edu.ucc.app.commons.converter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("co.edu.ucc.app")
@PropertySource("classpath:application.properties")
public class ConfiguradorSpring {
}

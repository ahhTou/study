package U14_AOP_Demo;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "U14_AOP_Demo")
@EnableAspectJAutoProxy
@Import(JdbcConfig.class)
@PropertySource("classpath:JdbcConfig.properties")
public class SpringConfiguration {
}

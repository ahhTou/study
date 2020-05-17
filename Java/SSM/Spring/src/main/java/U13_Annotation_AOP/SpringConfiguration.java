package U13_Annotation_AOP;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "U13_Annotation_AOP")
@EnableAspectJAutoProxy
public class SpringConfiguration {
}

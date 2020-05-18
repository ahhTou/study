package U18_Annotation_tx_Plus.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("U18_Annotation_tx_Plus")
@Import({JdbcConfig.class,TransactionConfig.class})
@PropertySource("classpath:JdbcConfig.properties")
@EnableTransactionManagement // 开启事务支持
public class SpringConfiguration {

}

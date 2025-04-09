package in.kpmg.cm.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "aarogyaEntityManagerFactory",
		transactionManagerRef = "aarogyaTransactionManager",
		basePackages = {"in.kpmg.cm.aarogya.repo"}
		)
public class AarogyaConfig {
	
	@Bean(name="aarogyaDataSource")
	@ConfigurationProperties(prefix = "spring.aarogya.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	

	@Bean(name="aarogyaEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("aarogyaDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource)
				      .packages("in.kpmg.cm.aarogya.models")
				      .persistenceUnit("aarogya")
				      .build();
	}
	
	@Primary
	@Bean(name="aarogyaTransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("aarogyaEntityManagerFactory") EntityManagerFactory aarogyaEntityManagerFactory) {
		return new JpaTransactionManager(aarogyaEntityManagerFactory);
	}

}

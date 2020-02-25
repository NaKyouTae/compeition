package com.competition.config;

import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        basePackages = "com.competition.jpa.repository", // TODO Repository 패키지 지정
//        transactionManagerRef = "transactionManager",
//        entityManagerFactoryRef = "entityManagerFactory"
//)
public class JPAConfig {
//	@Primary
//    @Bean(name = "mariadb")
//    @ConfigurationProperties("spring.data.mariadb")
//    public DataSource mariaDataSource() {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
//
//    @Primary
//    @Bean(name = "entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            EntityManagerFactoryBuilder builder,
//            @Qualifier("mariadb") DataSource dataSource) {
//        Map<String, String> map = new HashMap<>();
//        map.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
//        map.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
//        return builder.dataSource(dataSource)
//                .packages("com.competition.jpa.model") // TODO Model 패키지 지정
//                .properties(map)
//                .build();
//    }
//
//    @Primary
//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager transactionManager(
//            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory);
//        return transactionManager;
//    }
}

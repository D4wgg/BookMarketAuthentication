package ru.dawgg.bookmarket.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ru.dawgg.bookmarket.repository")
@EntityScan(basePackages = "ru.dawgg.bookmarket.model")
public class DataSourceConfig {
}

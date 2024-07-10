package com.jo0oy.api.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.jo0oy.db")
@EnableJpaRepositories(basePackages = "com.jo0oy.db")
@Configuration
public class JpaConfig {
}

package com.htet.employeemanagementapi.config;

import com.htet.employeemanagementapi.repositories.base.BaseRepoImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = BaseRepoImpl.class, basePackages = "com.htet.employeemanagementapi.repositories")
@EnableJpaAuditing(modifyOnCreate = false)
public class JpaConfig {
}

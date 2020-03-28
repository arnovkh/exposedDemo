package com.exposed.demo.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource


@Configuration
open class DatabaseConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.primary")
    open fun provideDatasource(): DataSource {
        return DataSourceBuilder.create().build()
    }
}
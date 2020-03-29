package com.exposed.demo.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
    @ConfigurationProperties(prefix = "datasource.primary")
    open class DataSourceConfiguration {
        lateinit var jdbc_url: String
        lateinit var username: String
    }

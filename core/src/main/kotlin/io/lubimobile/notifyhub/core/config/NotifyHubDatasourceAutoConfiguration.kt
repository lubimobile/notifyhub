package io.lubimobile.notifyhub.core.config

import io.lubimobile.notifyhub.core.config.properties.NotifyHubDatasourceProperties
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource

@Configuration
class NotifyHubDatasourceAutoConfiguration(private val properties: NotifyHubDatasourceProperties) {

    @Bean
    @ConditionalOnMissingBean(DataSource::class)
    fun dataSource(): DataSource  = DriverManagerDataSource().apply {
            url = properties.url
            username = properties.username
            password = properties.password
            setDriverClassName(properties.driverClassName)
        }
}
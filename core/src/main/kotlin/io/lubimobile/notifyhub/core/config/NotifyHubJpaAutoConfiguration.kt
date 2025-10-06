package io.lubimobile.notifyhub.core.config

import io.lubimobile.notifyhub.core.config.properties.NotifyHubDatasourceProperties
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = ["io.lubimobile.notifyhub.core.repository"],
)
class NotifyHubJpaAutoConfiguration(
    private val dataSource: DataSource,
    private val properties: NotifyHubDatasourceProperties
) {

    @Bean
    @ConditionalOnMissingBean(LocalContainerEntityManagerFactoryBean::class)
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val vendorAdapter = HibernateJpaVendorAdapter().apply {
            setShowSql(properties.jpa.hibernate.showSql)
            setGenerateDdl(properties.jpa.properties.hibernate.generateDdl)
        }

        return LocalContainerEntityManagerFactoryBean().apply {
            dataSource = this@NotifyHubJpaAutoConfiguration.dataSource
            setPackagesToScan("io.lubimobile.notifyhub.core")
            jpaVendorAdapter = vendorAdapter
            jpaPropertyMap = mapOf(
                "hibernate.hbm2ddl.auto" to properties.jpa.hibernate.ddlAuto,
            )
        }
    }
}
package io.lubimobile.notifyhub.core.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "notifyhub.datasource")
data class NotifyHubDatasourceProperties(
    var url: String = "jdbc:h2:mem:notifyhubdb;DB_CLOSE_DELAY=-1",
    var username: String = "sa",
    var password: String = "",
    var driverClassName: String = "org.h2.Driver",
    var h2: H2 = H2(),
    var sql: SQL = SQL(),
    var jpa: Jpa = Jpa(),
) {

    data class H2(
        var console: Console = Console()
    ) {
        data class Console(
            var enabled: Boolean = true,
            var path: String = "/h2-console",
            var settings: Settings = Settings()
        ) {
            data class Settings(
                var trace: Boolean = false,
                var webAllowOthers: Boolean = false
            )
        }
    }

    data class SQL(
        var init: Init = Init()
    ) {
        data class Init(
            var mode: String = "never",
        )
    }

    data class Jpa(
        var hibernate: Hibernate = Hibernate(),
        var properties: Properties = Properties(),
        var openInView: Boolean = false
    ) {

        data class Hibernate(
            var ddlAuto: String = "update",
            var showSql: Boolean = false,
            var formatSql: Boolean = true,
            var generateDdl: Boolean = true,
        )

        data class Properties(
            var hibernate: Hibernate = Hibernate(),
        )
    }
}

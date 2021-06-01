/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package javasurvival

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.checks.inGuild
import com.kotlindiscord.kord.extensions.utils.loadModule
import javasurvival.config.BotConfig
import javasurvival.extensions.*
import org.koin.dsl.bind

suspend fun main() {
    val config = BotConfig()

    val bot = ExtensibleBot(config.botToken) {
        slashCommands {
            check(inGuild(config.botGuild))

            enabled = true
        }

        extensions {
            // add(::TestExtension)
            add(::LoggingExtension)
            add(::UserExtension)
            add(::ModExtension)
            add(::PronounExtension)
            add(::ReactionRoleExtension)
        }

        hooks {
            afterKoinSetup {
                loadModule {
                    single { config } bind BotConfig::class
                }
            }
        }
    }

    bot.start()
}

/*
 *
 *  * Copyright (c) 2020. ForteScarlet All rights reserved.
 *  * Project  simple-robot
 *  * File     MiraiAvatar.kt
 *  *
 *  * You can contact the author through the following channels:
 *  * github https://github.com/ForteScarlet
 *  * gitee  https://gitee.com/ForteScarlet
 *  * email  ForteScarlet@163.com
 *  * QQ     1149159218
 *
 */

package love.forte.simbot.component.mirai.configuration

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import love.forte.common.ioc.annotation.Depend
import love.forte.simbot.component.mirai.utils.MiraiBotEventRegistrar
import love.forte.simbot.core.TypedCompLogger
import love.forte.simbot.core.configuration.ComponentBeans
import love.forte.simbot.listener.ListenerManager
import love.forte.simbot.listener.ListenerRegistered
import love.forte.simbot.listener.MsgGetProcessor
import net.mamoe.mirai.Bot
import net.mamoe.mirai.closeAndJoin
import net.mamoe.mirai.join

/**
 *
 * @author ForteScarlet -> https://github.com/ForteScarlet
 */
@ComponentBeans("miraiListenerRegistered")
public class MiraiListenerRegistered : ListenerRegistered {
    private companion object : TypedCompLogger(MiraiListenerRegistered::class.java)

    @Depend
    lateinit var msgGetProcessor: MsgGetProcessor

    @Depend
    lateinit var miraiBotEventRegistrar: MiraiBotEventRegistrar

    /**
     * 当所有的监听函数都注册完成后,
     * 为所有的bot注册监听事件。
     */
    override fun onRegistered(manager: ListenerManager) {
        // 注册Mirai的所有bot事件。
        Bot.forEachInstance { miraiBotEventRegistrar.registerSimbotEvents(it, msgGetProcessor) }

        val botAliveThread = BotAliveThread("mirai-bot-alive").apply { start() }

        // 注册一个 ctrl+c钩子来关闭所有的bot。
        Runtime.getRuntime().addShutdownHook(Thread {
            kotlin.runCatching {
                botAliveThread.interrupt()
            }.getOrElse { e ->
                logger.error("mirai-bot-alive thread interrupt failed. try to shutdown.", e)
                kotlin.runCatching {
                    @Suppress("DEPRECATION")
                    botAliveThread.stop()
                }.getOrElse {
                    logger.error("shutdown mirai-bot-alive thread failed.")
                }
            }

            logger.info("try to close all bots...")
            val waiting = mutableListOf<Pair<Long, Deferred<*>>>()
            // close all bot.
            Bot.botInstancesSequence.map {
                it.id to GlobalScope.async {
                    logger.debug("try to close bot(${it.id})")
                    it.closeAndJoin()
                }
            }.forEach {
                waiting.add(it)
            }
            runBlocking {
                waiting.forEach {
                    it.second.await()
                    logger.debug("bot(${it.first}) closed.")
                }

            }
            logger.info(" all bots closed.")
        })
    }
}


private class BotAliveThread(name: String) : Thread(name) {
    override fun run() {
        val bots = Bot.botInstances
        while(!interrupted() && bots.isNotEmpty()) {
            kotlin.runCatching {
                bots.forEach {
                    runBlocking { it.join() }
                }
            }
        }

    }
}

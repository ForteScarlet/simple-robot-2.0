/*
 *
 *  * Copyright (c) 2021. ForteScarlet All rights reserved.
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

package love.forte.simbot.core.dispatcher

import love.forte.common.configuration.annotation.AsConfig
import love.forte.common.ioc.annotation.ConfigBeans
import love.forte.simbot.dispatcher.AbstractDispatcherFactory
import love.forte.simbot.dispatcher.EventDispatcherFactory


/**
 *
 * @author ForteScarlet
 */
@AsConfig(prefix = "simbot.core.dispatcher.event")
@ConfigBeans
public class CoreEventDispatcherFactory : AbstractDispatcherFactory("simbot-core-event-dispatcher"), EventDispatcherFactory
package love.forte.test;

import catcode.CatCodeUtil;
import love.forte.common.configuration.Configuration;
import love.forte.simbot.annotation.SimbotApplication;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.bot.Bot;
import love.forte.simbot.core.SimbotApp;
import love.forte.simbot.core.SimbotContext;
import love.forte.simbot.core.SimbotProcess;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="https://github.com/ForteScarlet"> ForteScarlet </a>
 */
@SimbotApplication
public class Test implements SimbotProcess {
    public static void main(String[] args) {
        SimbotApp.run(Test.class, args);
    }

    @Override
    public void pre(@NotNull Configuration config) {
    }

    @Override
    public void post(@NotNull SimbotContext context) {
        Bot bot = context.getBotManager().getDefaultBot();
        // group 1043409458
        // String path = "/test/testImg.jpg";
        // // String file = "C:\\Users\\Administrator\\Desktop\\表情\\QQ图片20210217201307.jpg";
        // String file = "C:\\Users\\Administrator\\Desktop\\表情\\QQ图片20210217201243.jpg";
        //
        // String fileCat = CatCodeUtil.getInstance().toCat("file", true, "file=" + file, "path=" + path);
        //
        Sender sender = bot.getSender().SENDER;
        //
        // sender.sendGroupMsg(1043409458, fileCat);
        //
        // System.out.println("uploaded");

        CatCodeUtil util = CatCodeUtil.getInstance();

        sender.sendGroupMsg(1043409458, util.toCat("dice", "value=1"));
        sender.sendGroupMsg(1043409458, util.toCat("dice", "value=2"));
        sender.sendGroupMsg(1043409458, util.toCat("dice", "value=3"));
        sender.sendGroupMsg(1043409458, util.toCat("dice", "value=4"));
        sender.sendGroupMsg(1043409458, util.toCat("dice", "value=5"));
        sender.sendGroupMsg(1043409458, util.toCat("dice", "value=6"));

        // for (Bot bot : context.getBotManager().getBots()) {
        //     Sender s = bot.getSender().SENDER;
        //     s.sendPrivateMsg(1149159218, "我好了。" + bot.getSender().GETTER.getAuthInfo().getCookies());
        //     s.sendPrivateMsg(1149159218, CatCodeUtil.getInstance().getStringTemplate().image("classpath:1.jpg"));
        // }
    }
}

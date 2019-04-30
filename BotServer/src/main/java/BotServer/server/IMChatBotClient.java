package BotServer.server;

import BotServer.server.config.AppConfig;
import botapi.IMChatBot;

/**
 * **************************************************************
 * <p>
 * Desc:
 * User: jianguangluo
 * Date: 2018-12-30
 * Time: 03:04
 * <p>
 * **************************************************************
 */
public class IMChatBotClient {
    private static volatile IMChatBot client = null;

    public static IMChatBot getClient() {
        if (client == null) {
            synchronized (IMChatBotClient.class) {
                if (client == null) {
                    client = new IMChatBot(AppConfig.site, AppConfig.appId, AppConfig.appSecret);
                }
            }
        }
        return client;
    }
}

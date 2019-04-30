package BotServer;

import BotServer.server.BotStartServer;

/**
 * **************************************************************
 * <p>
 * Desc:
 * User: jianguangluo
 * Date: 2018-12-27
 * Time: 14:36
 * <p>
 * **************************************************************
 */
public class App {
    public static void main(String[] args) throws Exception {
        while (true) {
            BotStartServer.start();
            Thread.sleep(Long.MAX_VALUE);
        }
    }
}

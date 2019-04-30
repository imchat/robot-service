package BotServer.server.handler;

import BotServer.server.handler.impl.NewChatMemberHandler;
import BotServer.server.handler.impl.TextHandler;
import botapi.handler.HandlerFactory;
import botapi.handler.MessageHandler;
import botapi.types.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * **************************************************************
 * <p>
 * Desc: 消息统一入口
 * User: jianguangluo
 * Date: 2018-12-27
 * Time: 17:44
 * <p>
 * **************************************************************
 */
public class MessageHandlerFactory implements HandlerFactory {
    private static List<MessageHandler> handlerList = null;
    private static volatile MessageHandlerFactory instance;

    public static HandlerFactory getInstance() {
        if (instance == null) {
            synchronized (MessageHandlerFactory.class) {
                if (instance == null) {
                    instance = new MessageHandlerFactory();
                }
            }
        }
        return instance;
    }

    public boolean execute(Message message) {
        List<MessageHandler> handlers = getHandlerList();
        if (handlers != null) {
            for (MessageHandler handler : handlers) {
                handler.execute(message);
            }
        }
        return true;
    }

    public List<MessageHandler> getHandlerList() {
        if (handlerList == null) {
            handlerList = Arrays.asList(new TextHandler(), new NewChatMemberHandler());
        }
        return handlerList;
    }
}

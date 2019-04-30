package BotServer.server.handler.impl;

import BotServer.server.IMChatBotClient;
import BotServer.server.config.Constants;
import BotServer.server.service.BotSettingsService;
import botapi.handler.MessageHandler;
import botapi.model.MessageType;
import botapi.model.RedPackDrawType;
import botapi.model.RedPackSubType;
import botapi.model.RedPackType;
import botapi.resp.ApiResult;
import botapi.types.Chat;
import botapi.types.Message;
import botapi.types.User;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * **************************************************************
 * <p>
 * Desc: 处理加人入群消息
 * User: jianguangluo
 * Date: 2018-12-27
 * Time: 16:34
 * <p>
 * **************************************************************
 */
public class NewChatMemberHandler implements MessageHandler {

    /**
     * 处理消息
     *
     * @param message 消息体
     * @return
     */
    @Override
    public boolean execute(Message message) {
        if (message == null) {
            return false;
        }
        if (message.getType() != MessageType.NewChatMembers.getCode()) {
            return false;
        }
        Chat chat = message.getChat();
        List<User> newChatMembers = message.getNewChatMembers();

        if (newChatMembers == null) {
            return false;
        }

        try {
            JSONObject setting = BotSettingsService.getBotSetting();
            if (setting == null) {
                return false;
            }
            for (User user : newChatMembers) {
                String sender = user.getId();
                String senderNickName = user.getNickName();

                //新用户入群
                List<JSONObject> newUserWelcome = setting.getObject("newUserWelcome", new TypeReference<List<JSONObject>>() {
                });
                if (newUserWelcome != null) {
                    for (JSONObject key : newUserWelcome) {
                        String text = key.getString("send");//入群回复内容
                        int redpack = key.getIntValue("redpack");//是否发红包
                        String redpackName = key.getString("redpackName");//红包名称
                        BigDecimal redpackAmount = key.getBigDecimal("redpackAmount");//红包金额
                        String redpackCoinType = key.getString("redpackCoinType");//红包币种

                        //@新入群用户
                        Set<String> at = new HashSet<>();
                        if (text.contains(Constants.Replace_NickName)) {
                            text = text.replace(Constants.Replace_NickName, "@" + senderNickName);
                            at.add(sender);
                        }

                        //获取红包金额，可配置指定用户红包金额不同
                        redpackAmount = BotSettingsService.getRedPackAmount(sender, redpackAmount);

                        //发红包
                        if (redpack > 0) {
                            ApiResult result = IMChatBotClient.getClient().sendRedPack(chat.getId(), redpackName, redpackCoinType, redpackAmount, 1, 1, RedPackType.Common, RedPackSubType.SELF_SHARE, RedPackDrawType.AVG, sender);
                            if (result.isOk() && result.getErrorCode() == 0) {
                                //回复文字
                                IMChatBotClient.getClient().sendMessage(chat.getId(), text, at);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
}

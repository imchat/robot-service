package BotServer.server.handler.impl;

import BotServer.server.IMChatBotClient;
import BotServer.server.config.Constants;
import BotServer.server.service.BotSettingsService;
import BotServer.server.service.SignInBotService;
import BotServer.server.utils.DateUtils;
import botapi.handler.MessageHandler;
import botapi.model.MessageType;
import botapi.model.RedPackDrawType;
import botapi.model.RedPackSubType;
import botapi.model.RedPackType;
import botapi.types.Chat;
import botapi.types.Message;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * **************************************************************
 * <p>
 * Desc: 处理文本消息
 * User: jianguangluo
 * Date: 2018-12-27
 * Time: 15:19
 * <p>
 * **************************************************************
 */
public class TextHandler implements MessageHandler {

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
        if (message.getType() != MessageType.Text.getCode()) {
            return false;
        }
        String date = DateUtils.dateToStr(new Date(), "yyyy-MM-dd");
        try {
            JSONObject setting = BotSettingsService.getBotSetting();
            if (setting == null) {
                return false;
            }
            String content = message.getText();
            String senderNickName = message.getFrom().getNickName();
            String sender = message.getFrom().getId();
            Chat chat = message.getChat();
            //每日签到
            List<JSONObject> dailySignIn = setting.getObject("dailySignIn", new TypeReference<List<JSONObject>>() {
            });
            if (dailySignIn != null) {
                for (JSONObject key : dailySignIn) {
                    String keyword = key.getString("keyword");//匹配关键字
                    String text = key.getString("send");//签到成功发送
                    String onDuplicated = key.getString("onDuplicated");//重复签到发送

                    Set<String> at = new HashSet<>();
                    //匹配关键字
                    if (StringUtils.isEmpty(keyword) || !keyword.equalsIgnoreCase(content)) {
                        continue;
                    }

                    //@签到人
                    if (text.contains(Constants.Replace_NickName)) {
                        text = text.replace(Constants.Replace_NickName, "@" + senderNickName);
                        at.add(sender);
                    }

                    boolean isDuplicated = false;

                    //重复签到
                    if (SignInBotService.get(date, sender) != null) {
                        text = onDuplicated.replace(Constants.Replace_NickName, "@" + senderNickName);
                        isDuplicated = true;
                    }

                    //回复文字
                    IMChatBotClient.getClient().sendMessage(chat.getId(), text, at);

                    //签到成功，发红包
                    if (!isDuplicated) {
                        int redpack = key.getIntValue("redpack");//是否发红包
                        String redpackName = key.getString("redpackName");//红包名称
                        BigDecimal redpackAmount = key.getBigDecimal("redpackAmount");//红包金额
                        String redpackCoinType = key.getString("redpackCoinType");//红包币种
                        if (redpack > 0) {
                            IMChatBotClient.getClient().sendRedPack(chat.getId(), redpackName, redpackCoinType, redpackAmount, 1, 1, RedPackType.Common, RedPackSubType.SELF_SHARE, RedPackDrawType.AVG, sender);
                        }
                    }

                    //记录今日签到人
                    SignInBotService.add(date, sender);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
}

package BotServer.server;

import BotServer.server.config.Constants;
import BotServer.server.handler.MessageHandlerFactory;
import BotServer.server.service.BotSettingsService;
import BotServer.server.service.BotTimerService;
import BotServer.server.utils.DateUtils;
import botapi.resp.ApiResult;
import botapi.types.Chat;
import botapi.types.Message;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * **************************************************************
 * <p>
 * Desc:
 * User: jianguangluo
 * Date: 2018-12-27
 * Time: 17:48
 * <p>
 * **************************************************************
 */
public class BotStartServer {

    public static void start() {
        ScheduledExecutorService schedules = Executors.newScheduledThreadPool(2);

        //接收消息，并处理
        schedules.scheduleWithFixedDelay(() -> {
            try {
                ApiResult<List<Message>> res = IMChatBotClient.getClient().getUpdates(100, 5);
                if (res != null && res.getErrorCode() == 0) {
                    List<Message> list = res.getResult();
                    if (list != null && list.size() > 0) {
                        list.forEach(mess -> MessageHandlerFactory.getInstance().execute(mess));
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }, 2, 2, TimeUnit.SECONDS);

        //定时发消息
        schedules.scheduleWithFixedDelay(() -> {
            try {
                JSONObject setting = BotSettingsService.getBotSetting();
                if (setting != null) {
                    List<JSONObject> timer = setting.getObject("timer", new TypeReference<List<JSONObject>>() {
                    });
                    if (timer != null) {
                        String date = DateUtils.dateToStr(new Date(), "yyyy-MM-dd");
                        for (JSONObject key : timer) {
                            String time = key.getString("time");
                            String frequency = key.getString("frequency");
                            String send = key.getString("send");
                            //每天定时发送
                            if ("daily".equals(frequency)) {
                                String execuTimeStr = date + " " + time;
                                //今天已发送过，不再发送
                                if (BotTimerService.get(execuTimeStr)) {
                                    continue;
                                }
                                //已到指定时间，发送消息
                                Date execuTime = DateUtils.strToDate(execuTimeStr);
                                if (execuTime != null && execuTime.getTime() <= new Date().getTime()) {
                                    Set<String> at = new HashSet<>();
                                    at.add(Constants.AT_ALL);//@all
                                    //所有子群@All
                                    List<Chat> chats = IMChatBotClient.getClient().getChats().getResult();
                                    if (chats != null && chats.size() > 0) {
                                        chats.forEach(chat -> {
                                            try {
                                                ApiResult res = IMChatBotClient.getClient().sendMessage(chat.getId(), send, at);
                                                if (res != null && res.getErrorCode() == 0) {
                                                    BotTimerService.add(execuTimeStr);
                                                }
                                            } catch (Exception ex) {
                                                ex.printStackTrace();
                                            }
                                        });
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }, 1, 1, TimeUnit.MINUTES);
    }
}

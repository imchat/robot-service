package BotServer.server.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * **************************************************************
 * <p>
 * Desc:
 * User: jianguangluo
 * Date: 2018-12-27
 * Time: 16:50
 * <p>
 * **************************************************************
 */
public class BotSettingsService {
    private static volatile JSONObject config = null;
    private static volatile List<JSONObject> webUsers = null;

    public static JSONObject getBotSetting() {
        return getSystemConfig();
    }

    /**
     * WebUser配置文件
     * @return
     */
    public static List<JSONObject> getWebUsers() {
        if (webUsers == null) {
            synchronized (BotSettingsService.class) {
                if (webUsers == null) {
                    try {
                        webUsers = new ArrayList<>();
                        String fileName ="./web_users.json";//获取文件路径
                        FileInputStream input = new FileInputStream(fileName);
                        int len = input.available();
                        byte[] bytes = new byte[len];
                        input.read(bytes, 0, len);

                        String s = new String(bytes);
                        if (StringUtils.isNotEmpty(s)) {
                            JSONObject res = JSONObject.parseObject(s);
                            if (res != null) {
                                List<JSONObject> array = res.getObject("list",new TypeReference<List<JSONObject>>(){});
                                if (array != null) {
                                    array.forEach(o -> {
                                        webUsers.add(o);
                                    });
                                }
                            }
                            System.out.println("WebUserConfig:" + webUsers == null ? "" : webUsers);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return webUsers;
    }

    /**
     * 读WebUser配置文中的用户红金额
     * @param openId
     * @param amount
     * @return
     */
    public static BigDecimal getRedPackAmount(String openId, BigDecimal amount) {
        for (JSONObject user : getWebUsers()) {
            if (user.getString("openId").equals(openId)) {
                return user.getBigDecimal("amount");
            }
        }
        return amount;
    }

    private static JSONObject getSystemConfig() {
        if (config == null) {
            synchronized (BotSettingsService.class) {
                if (config == null) {
                    try {
                        String fileName ="./setting.json";//获取文件路径
                        FileInputStream input = new FileInputStream(fileName);
                        int len = input.available();
                        byte[] bytes = new byte[len];
                        input.read(bytes, 0, len);

                        String s = new String(bytes);
                        if (StringUtils.isNotEmpty(s)) {
                            config = JSONObject.parseObject(s);
                            System.out.println("Config:" + config == null ? "" : config.toJSONString());
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return config;
    }
}

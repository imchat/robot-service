package BotServer.server.service;

import BotServer.server.utils.DBUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * **************************************************************
 * <p>
 * Desc:
 * User: jianguangluo
 * Date: 2018-12-29
 * Time: 14:16
 * <p>
 * **************************************************************
 */
public class BotTimerService {
    private static final String dbName = "BotTimer";

    public static boolean add(String date) {
        JSONObject data = new JSONObject();
        data.put("succ", true);
        return DBUtils.writeDB(dbName, date, data) > 0;
    }

    public static boolean get(String date) {
        JSONObject res = DBUtils.readDB(dbName, date);
        return res != null && res.getBooleanValue("succ");
    }
}

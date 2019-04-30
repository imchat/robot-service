package BotServer.server.utils;

import com.alibaba.fastjson.JSONObject;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

public class DBUtils {
    static String dbPath = "db/";//数据库文件目录
    //连接池
    final static ConcurrentHashMap<String, RocksDB> connetionPool = new ConcurrentHashMap();

    static {
        try {
            RocksDB.loadLibrary();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static synchronized RocksDB getDBConnetion(String tableName) {
        try {
            String dir = dbPath + tableName;
            RocksDB rocksDB = connetionPool.get(dir);
            if (rocksDB == null) {
                String path = "./" + dir;
                System.out.println("path1:" + path);
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdirs();
                }

                Options options = new Options();
                options.setCreateIfMissing(true);
                rocksDB = RocksDB.open(options, path);
                if (rocksDB != null) {
                    connetionPool.put(dir, rocksDB);
                }
            }
            return rocksDB;


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static long writeDB(String tableName, String id, JSONObject detail) {
        RocksDB db = getDBConnetion(tableName);
        try {

            if (db != null) {
                db.put(id.getBytes(), detail.toJSONString().getBytes());
                return 1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public static JSONObject readDB(String tableName, String id) {
        RocksDB db = getDBConnetion(tableName);
        try {
            if (db != null) {
                byte[] bytes = db.get(id.getBytes());
                if (bytes != null) {
                    return JSONObject.parseObject(new String(bytes));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}

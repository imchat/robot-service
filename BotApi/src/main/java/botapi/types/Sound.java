package botapi.types;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * **************************************************************
 * <p>
 * Desc:
 * User: jianguangluo
 * Date: 2019-03-04
 * Time: 14:37
 * <p>
 * **************************************************************
 */
public class Sound {
    @JSONField(name = "file_path")
    private String filePath;
    private int length;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

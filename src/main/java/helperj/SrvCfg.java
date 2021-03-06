package helperj;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class SrvCfg {

    private static Map<String, Object> data = null;
    private static String _path = "server.json";

    private static void loadConfig(String path) throws FileNotFoundException {
        if(path != null)
            _path = path;

        data = new Gson().fromJson(
                new FileReader(_path),
                new TypeToken<Map<String, Object>>(){}.getType());
    }

    public static String getProdHostName() {
        return (String)data.get("prod_hostname");
    }

    public static Map<String, Object> get(String path) throws FileNotFoundException {
        if(data == null)
            loadConfig(path);
        return data;
    }

    public static Map<String, Object> get() throws FileNotFoundException {
        return get(null);
    }

    public static Map<String, Object> db() throws FileNotFoundException {
        return (Map<String, Object>) get().get("db");
    }

    public static Map<String, Object> dblogs() throws FileNotFoundException {
        return (Map<String, Object>) db().get("logs");
    }

    public static Map<String, Object> dbrbtlong_prod() throws FileNotFoundException {
        return (Map<String, Object>) db().get("rbtlong_prod");
    }

    public static Map<String, Object> dbrbtlong_dev() throws FileNotFoundException {
        return (Map<String, Object>) db().get("rbtlong_dev");
    }


}

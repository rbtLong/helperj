package helperj.db;

import helperj.SrvCfg;

import java.io.FileNotFoundException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Map;

public class Db {
    public static boolean isProd() throws UnknownHostException {
        return SrvCfg.getProdHostName().equals(Inet4Address.getLocalHost().getHostName());
    }

    public static String makeConnStr(Map<String, Object> db) {
        String host = (String) db.get("host");
        String username = (String) db.get("user");
        String password = (String) db.get("password");
        String database = (String) db.get("database");
        return "jdbc:mysql://" + host
                + "/" + database
                + "?user=" + username
                + "&password=" + password;
    }

    public static DbCmd Logs() throws FileNotFoundException {
        return new DbCmd(makeConnStr(SrvCfg.dblogs()));
    }

    public static DbCmd rbtLong() throws UnknownHostException, FileNotFoundException {
        Map<String, Object> db = null;

        if(isProd())
            db = SrvCfg.dbrbtlong_prod();
        else
            db = SrvCfg.dbrbtlong_dev();

        return new DbCmd(makeConnStr(db));
    }

}

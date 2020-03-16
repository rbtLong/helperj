package helperj.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface ICommandCallBack {
    public Object call(Connection conn) throws SQLException;
}

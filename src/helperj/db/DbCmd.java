package helperj.db;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DbCmd {

    private String url = "";
    private String mysql_classname = "com.mysql.cj.jdbc.Driver";

    public DbCmd(String url) {
        this.url = url;
    }

    public Object command(@NotNull ICommandCallBack cb) throws SQLException, ClassNotFoundException {
        Class.forName(mysql_classname);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            return cb.call(conn);
        } finally {
            if(conn != null && !conn.isClosed())
                conn.close();
        }
    }

    public ArrayList<Map<String, Object>> rows(String query, Object... params)
            throws SQLException, ClassNotFoundException {
        Class.forName(mysql_classname);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(query);
            for(int i=0; i<params.length; ++i)
                stmt.setObject(i+1, params[i]);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsinfo = rs.getMetaData();

            ArrayList<String> _cols = new ArrayList<>();
            ArrayList<Map<String, Object>> _rows = new ArrayList<>();

            for(int i=0; i<rsinfo.getColumnCount(); ++i) {
                _cols.add(rsinfo.getColumnName(i+1));
            }

            while(rs.next()){
                Map<String, Object> row = new HashMap<>();
                for(String c : _cols) {
                    row.put(c, rs.getObject(c));
                }
                _rows.add(row);
            }

            if(_rows.size() == 0)
                return null;

            return _rows;
        } finally {
            if(conn != null && !conn.isClosed())
                conn.close();
        }
    }

    public Map<String, Object> row(String query, Object... params) throws SQLException, ClassNotFoundException {
        ArrayList<Map<String, Object>> result = rows(query, params);

        if(result != null && result.size() > 0)
            return result.get(0);
        return null;
    }

    public int update(String cmd, @NotNull Object... params) throws ClassNotFoundException, SQLException {
        Class.forName(mysql_classname);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(cmd);
            for(int i=0; i<params.length; ++i)
                stmt.setObject(i+1, params[i]);
            return stmt.executeUpdate();
        } finally {
            if(conn != null && !conn.isClosed())
                conn.close();
        }
    }

}

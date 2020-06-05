package az.maqa.project.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Utility {
    public static void closeJDBC(Connection c, PreparedStatement ps, ResultSet rs) throws Exception {

        if (c != null) {
            c.close();
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

}


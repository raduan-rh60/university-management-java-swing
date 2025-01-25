package university;

import java.sql.*;

public class ST_DB_Connection {

    Connection conn;
    Statement st;

    public ST_DB_Connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_management_system", "root", "root");
//            st = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

package dbtest.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
  public Connection dbConn;

  public Connection getConnection() {
    Connection connect = null;
    try {
      String user = "nabi";
      String pw = "tiger";
      String url = "jdbc:oracle:thin:@192.168.10.85:1521:orcl11";
      // String url = "jdbc:oracle:thin:@localhost:1521:orcl11";

      Class.forName("oracle.jdbc.driver.OracleDriver");
      connect = DriverManager.getConnection(url, user, pw);
    } catch (ClassNotFoundException cnfe) {
      System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
    } catch (SQLException sqle) {
      System.out.println("DB 접속실패 : " + sqle.toString());
    } catch (Exception e) {
      System.out.println("Unkonwn error");
      e.printStackTrace();
    }
    return connect;
  }

}

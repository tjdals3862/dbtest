package dbtest.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Select {

  DBCon dbcon = new DBCon();
  Connection conn = dbcon.getConnection();
  DBFreeCon dfc = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;

  public void frsearch() {
    try {

      StringBuilder sql = new StringBuilder();
      sql.append("select id from friend  ");
      pstmt = conn.prepareStatement(sql.toString());
      rs = pstmt.executeQuery();

      while (rs.next()) {
        String id = rs.getString("id");
        System.out.println(id);
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      dfc.freeConnection(conn, pstmt, rs);
    }

  }

  public static void main(String[] args) {
    Select select = new Select();
    select.frsearch();
  }

}

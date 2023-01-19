package dbtest.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DmlFun {

  DBCon db = null;
  Connection conn = null;
  PreparedStatement pstmt = null;
  Statement stmt = null;
  ResultSet rs = null;
  DBFreeCon dfc = null;

  public void dml(String query) {
    db = new DBCon();
    dfc = new DBFreeCon();

    try {
      conn = db.getConnection();
      pstmt = conn.prepareStatement(query);
      int r = pstmt.executeUpdate();
    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }

    finally {
      try {
        dfc.freeConnection(conn, pstmt);
      } catch (Exception ie) {
        throw new RuntimeException(ie.getMessage());
      }
    }
  }

  public String Insert() {

    try {
      StringBuilder sql = new StringBuilder();
      sql.append("insert into friend values  ");
      sql.append(" ('abc', 'test', '여은경') ");
      String result = sql.toString();

      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  public String Update() {
    String query = "";

    return query;
  }

  public String Delete() {
    String query = "";

    return query;
  }

  public static void main(String[] args) {
    DmlFun df = new DmlFun();

    // Insert
    String insert = df.Insert();
    df.dml(insert);

    // update
    String update = df.Update();
    // df.dml(update);

    // delete
    String delete = df.Delete();
    // df.dml(delete);
  }

}

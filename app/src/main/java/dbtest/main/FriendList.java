package dbtest.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.awt.Font;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import dbtest.oracle.DBCon;

public class FriendList extends JPanel implements MouseListener, ActionListener {
  // 이름 출력해주고, id 값 저장해서 버튼에 id로 다이얼로그 띄우기
  DBCon dbcon = new DBCon();

  MainForm mf = null;

  List<String[]> fr_list;
  DefaultListModel<String> dlm_frnd;
  JScrollPane jsp;
  JList jl_frnd;

  String name = null;
  String userID = null;
  String id = "abc";

  Connection conn = null;
  PreparedStatement pstm = null;
  ResultSet rs = null;

  DBCon db = new DBCon();

  JPanel frnd_north;
  JTextField jtf_search;
  JButton fr_search;
  JButton fr_add;

  Font font;

  public FriendList() {
    InitDisplay();
  }

  public void InitDisplay() {
    dlm_frnd = new DefaultListModel<>();
    getDB();

    frnd_north = new JPanel();
    jtf_search = new JTextField("", 23);
    fr_search = new JButton("검 색");
    fr_search.setBackground(Color.pink);
    fr_add = new JButton("추 가");
    fr_add.setBackground(Color.pink);

    fr_add.addActionListener(this);

    // font = cVO.getFontc();
    jl_frnd = new JList(dlm_frnd);
    jsp = new JScrollPane(jl_frnd);

    this.setLayout(new BorderLayout());

    fr_search.addActionListener(this);

    jl_frnd.addMouseListener(this);
    jl_frnd.setFixedCellWidth(392);
    jl_frnd.setFixedCellHeight(57);
    jl_frnd.setVisibleRowCount(6);
    jl_frnd.setSize(390, 200);

    jl_frnd.setFont(font);

    frnd_north.add("North", jtf_search);
    frnd_north.add("North", fr_search);
    frnd_north.add("North", fr_add);
    frnd_north.setBackground(new Color(155, 138, 124));
    this.add("North", frnd_north);
    this.add("Center", jsp);

    this.setSize(426, 380);
    this.setVisible(true);

  }

  public void searchFriend(String str) {
    dlm_frnd.clear();
    fr_list = new Vector<>();

    try {
      String sql = String.format("SELECT * FROM friend WHERE id = '%s' AND fr_name like '%%%s%%' ",
          id,
          str);
      conn = dbcon.getConnection();
      pstm = conn.prepareStatement(sql);
      rs = pstm.executeQuery();

      while (rs.next()) {
        String name = rs.getString("FR_NAME");
        String ID = rs.getString("FR_ID");
        String[] data = { name, ID };
        fr_list.add(data);
      }

      for (int i = 0; i < fr_list.size(); i++) {
        int a = fr_list.size();
        String[] data = new String[a];
        data = fr_list.get(i);

        dlm_frnd.add(0, data[0]); // data[0] : name, data[1] : ID
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void getDB() {
    dlm_frnd.clear();
    fr_list = new Vector<>();

    try {
      String sql = "SELECT FR_ID, FR_NAME FROM friend where ID = '" + id + "'";
      conn = dbcon.getConnection();
      pstm = conn.prepareStatement(sql);
      rs = pstm.executeQuery();
      while (rs.next()) {
        String name = rs.getString("FR_NAME");
        String ID = rs.getString("FR_ID");
        String[] data = { name, ID };
        fr_list.add(data);
      }

      for (int i = 0; i < fr_list.size(); i++) {
        int a = fr_list.size();
        String[] data = new String[a];
        data = fr_list.get(i);

        dlm_frnd.add(0, data[0]); // data[0] : name, data[1] : ID
      }
    } catch (Exception e) {
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();
    if (fr_search == obj) { // 검색 버튼 클릭
      if ("".equals(jtf_search.getText())) {
        getDB();
      } else {
        searchFriend(jtf_search.getText());
        jtf_search.setText("");
      }
    }

  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

}

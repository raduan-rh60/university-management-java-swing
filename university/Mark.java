package university;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Name
 */
public class Mark extends javax.swing.JFrame {

    ST_DB_Connection cdb = new ST_DB_Connection();

    /**
     * Creates new form Mark
     */
    public Mark() {
        initComponents();
        new ST_DB_Connection();
        dept_Load();
        Examtype_Load();
//        Mark_Load();
        semister_Load();
        setIconImage();
        setTitle("Mark page");
      
    }
    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));

    }

    int id;
    String uname;
    String usertype;

    public Mark(int id, String username, String utype) {
        setIconImage();
        setTitle("Mark page");
        initComponents();
        new ST_DB_Connection();
        dept_Load();
        Examtype_Load();
//        Mark_Load();
        semister_Load();

        this.uname = username;
        jLabel20.setText(uname);

        this.usertype = utype;
        jLabel30.setText(utype);

        this.id = id;
        if (utype.equals("Student")) {
            savebutton.setEnabled(false);
            editbutton.setEnabled(false);
            deletebutton.setEnabled(false);
            clearbutton.setEnabled(false);
        }

        if (utype.equals("Guest")) {
            savebutton.setEnabled(false);
            editbutton.setEnabled(false);
            deletebutton.setEnabled(false);
            clearbutton.setEnabled(false);

        } else {
            savebutton.setEnabled(true);
            editbutton.setEnabled(true);
            deletebutton.setEnabled(true);
            clearbutton.setEnabled(true);
        }

    }

    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel d;

    public void course_load() {

        try {
            String deptname = txtdept.getSelectedItem().toString();
            pst = cdb.conn.prepareStatement("select subjectname from subject where dept_name = ?");
            pst.setString(1, deptname);
            rs = pst.executeQuery();
            

            ArrayList<String> arr = new ArrayList<>();
            while (rs.next()) {
                arr.add(rs.getString("subjectname"));
            }
            for (String a : arr) {
                txtcoursename.addItem(a);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void course_code() {
        try {
            String subname = txtcoursename.getSelectedItem().toString();
            pst = cdb.conn.prepareStatement("select subjectcode from subject where subjectname = ?");
            pst.setString(1, subname);
            rs = pst.executeQuery();
            if (rs.next()) {
                coursecode.setText(rs.getString("subjectcode"));
            }
            coursecode.setEnabled(false);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void dept_Load() {

        try {
            pst = cdb.conn.prepareStatement("select Distinct dept_name from class");
            rs = pst.executeQuery();

            //txtclass.removeAllItems();
            while (rs.next()) {
                txtdept.addItem(rs.getString("dept_name"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Examtype_Load() {

        try {
            txtexmtype.removeAllItems();
            pst = cdb.conn.prepareStatement("select Distinct examterm from exam");
            rs = pst.executeQuery();

            //txtclass.removeAllItems();
            while (rs.next()) {
                txtexmtype.addItem(rs.getString("examterm"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void semister_Load() {

        try {
            txtsemister.removeAllItems();
            pst = cdb.conn.prepareStatement("select Distinct examsemister from exam");
            rs = pst.executeQuery();

            //txtclass.removeAllItems();
            while (rs.next()) {
                txtsemister.addItem(rs.getString("examsemister"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void term_load() {
        try {
            String semister = txtsemister.getSelectedItem().toString();
            pst = cdb.conn.prepareStatement("select Distinct examterm from exam where examsession= ?");
            pst.setString(1, semister);
            rs = pst.executeQuery();

            //txtclass.removeAllItems();
            while (rs.next()) {
                txtexmtype.addItem(rs.getString("examterm"));
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }

    }

    public void Mark_Load() {
        int c;
        try {
            int id = Integer.parseInt(txtid.getText());
            pst = cdb.conn.prepareStatement("select * from mark where studentid = ?");
            pst.setInt(1, id);
            rs = pst.executeQuery();

            d = (DefaultTableModel) jTable1.getModel();
            d.setRowCount(0);
            while (rs.next()) {
                Vector<String> v2 = new Vector<>();
                v2.add(rs.getString("studentid"));
                v2.add(rs.getString("studentname"));
                v2.add(rs.getString("department"));
                v2.add(rs.getString("semister"));
                v2.add(rs.getString("course_code"));
                v2.add(rs.getString("course_name"));
                v2.add(rs.getString("exam_type"));
                v2.add(rs.getString("marks"));

                d.addRow(v2);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        coursecode = new javax.swing.JTextField();
        txtexmtype = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        txtdept = new javax.swing.JComboBox<>();
        txtcoursename = new javax.swing.JComboBox<>();
        searchButton = new javax.swing.JLabel();
        clearbutton = new javax.swing.JButton();
        savebutton = new javax.swing.JButton();
        deletebutton = new javax.swing.JButton();
        editbutton = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtsemister = new javax.swing.JComboBox<>();
        txtmark1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mark Details:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        coursecode.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        coursecode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(coursecode, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 530, 240, 30));

        txtexmtype.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtexmtype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtexmtypeActionPerformed(evt);
            }
        });
        jPanel2.add(txtexmtype, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 240, 30));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Exam Type");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 170, -1));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Student ID");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Student Name");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("department");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Semister");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 130, -1));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Course Code");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, 160, -1));

        txtid.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jPanel2.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 240, 30));

        txtname.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jPanel2.add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 240, 30));

        txtdept.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtdept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdeptActionPerformed(evt);
            }
        });
        jPanel2.add(txtdept, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 240, 30));

        txtcoursename.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtcoursename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcoursenameActionPerformed(evt);
            }
        });
        jPanel2.add(txtcoursename, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 240, 30));

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        searchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchButtonMouseClicked(evt);
            }
        });
        jPanel2.add(searchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 30, 30));

        clearbutton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clearbutton.setText("CLEAR");
        clearbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(clearbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 680, 80, 40));

        savebutton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        savebutton.setText("SAVE");
        savebutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        savebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebuttonActionPerformed(evt);
            }
        });
        jPanel2.add(savebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 680, 80, 40));

        deletebutton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deletebutton.setText("DELETE");
        deletebutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deletebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebuttonActionPerformed(evt);
            }
        });
        jPanel2.add(deletebutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 680, 80, 40));

        editbutton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        editbutton.setText("EDIT");
        editbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbuttonActionPerformed(evt);
            }
        });
        jPanel2.add(editbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 680, 80, 40));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mark.png"))); // NOI18N
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 50, 50));

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Course Name");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 190, -1));

        txtsemister.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtsemister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsemisterActionPerformed(evt);
            }
        });
        jPanel2.add(txtsemister, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 240, 30));

        txtmark1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jPanel2.add(txtmark1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 600, 240, 30));

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Marks");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 580, 80, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 740));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("USER TYPE:");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 90, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("LOGOUT");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 20, 60, 30));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setText("USERNAME");
        jLabel30.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 90, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("USERNAME:");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 90, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("USERNAME");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 90, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name", "Department", "Semister", "Course Code", "Course Name", "Exam Type", "Marks"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(90);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(120);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 850, 620));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 740));

        jMenu1.setText("Main Menu");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        Login l = new Login();
        l.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_jLabel2MouseClicked

    private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseClicked
        try {
            // TODO add your handling code here:
            int sid = Integer.parseInt(txtid.getText());

            pst = cdb.conn.prepareStatement("select studentname,studentdepartment from student where studentid=?");
            pst.setInt(1, sid);
            rs = pst.executeQuery();

            if (rs.next() == false) {
                JOptionPane.showMessageDialog(this, "Student NOT FOUND");
                txtname.setText("");
            } else {
                String name = rs.getString("studentname");
                String deptName = rs.getString("studentdepartment");

                txtname.setText(name);
                txtdept.removeAllItems();
                txtdept.addItem(deptName);

            }
             Mark_Load();
        } catch (SQLException ex) {
            Logger.getLogger(Mark.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchButtonMouseClicked

    private void savebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebuttonActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            int id = Integer.parseInt(txtid.getText());
            String name = txtname.getText();
            String dept = txtdept.getSelectedItem().toString();
            String semister = txtsemister.getSelectedItem().toString();
            String courseCode = coursecode.getText();
            String courseName = txtcoursename.getSelectedItem().toString();
            String term = txtexmtype.getSelectedItem().toString();
            String mark = txtmark1.getText();
            

            pst = cdb.conn.prepareStatement("insert into mark(studentid,studentname,department,semister,course_code,course_name,exam_type,marks)values(?,?,?,?,?,?,?,?)");
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, dept);
            pst.setString(4, semister);
            pst.setString(5, courseCode);
            pst.setString(6, courseName);
            pst.setString(7, term);
            pst.setString(8, mark);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Marks details added successfully...");

           
            txtcoursename.setSelectedIndex(-1);
            coursecode.setText("");
            txtexmtype.setSelectedIndex(-1);
            txtsemister.setSelectedIndex(-1);

            txtid.requestFocus();

            Mark_Load();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_savebuttonActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        d = (DefaultTableModel) jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        txtid.setText(d.getValueAt(selectIndex, 0).toString());
        txtname.setText(d.getValueAt(selectIndex, 1).toString());
        txtdept.setSelectedItem(d.getValueAt(selectIndex, 2).toString());
        txtsemister.setSelectedItem(d.getValueAt(selectIndex, 3).toString());
        coursecode.setText(d.getValueAt(selectIndex, 4).toString());
        txtcoursename.setSelectedItem(d.getValueAt(selectIndex, 5).toString());
        txtexmtype.setSelectedItem(d.getValueAt(selectIndex, 6).toString());
        txtmark1.setText(d.getValueAt(selectIndex, 7).toString());
        savebutton.setEnabled(false);
    }//GEN-LAST:event_jTable1MouseClicked

    private void clearbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearbuttonActionPerformed
        // TODO add your handling code here:
        txtid.setText("");
        txtname.setText("");
        txtdept.setSelectedIndex(-1);
        txtcoursename.setSelectedIndex(-1);
        coursecode.setText("");
        txtexmtype.setSelectedIndex(-1);
        txtmark1.setText("");
        txtsemister.setSelectedIndex(-1);

        txtid.requestFocus();
        
        d.setRowCount(0);
        savebutton.setEnabled(true);

    }//GEN-LAST:event_clearbuttonActionPerformed

    private void editbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbuttonActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            d = (DefaultTableModel) jTable1.getModel();
            int selectIndex = jTable1.getSelectedRow();

            int id = Integer.parseInt(txtid.getText());
            String name = txtname.getText();
            String dept = txtdept.getSelectedItem().toString();
            String courseName = txtcoursename.getSelectedItem().toString();
            String semister = txtsemister.getSelectedItem().toString();
            String exmType = txtexmtype.getSelectedItem().toString();
            String corseCode = coursecode.getText();
            String mark = txtmark1.getText();

            pst = cdb.conn.prepareStatement("update mark set studentid=?,studentname=?,department=?,semister=?,course_name=?,course_code=?,exam_type=?,marks=? where studentid=?");
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setString(3, dept);
            pst.setString(4, semister);
            pst.setString(5, courseName);
            pst.setString(6, corseCode);
            pst.setString(7, exmType);
            pst.setString(8, mark);
            pst.setInt(9, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Mark details edited successfully...");
            savebutton.setEnabled(true);

            txtid.setText("");
            txtname.setText("");
            txtdept.setSelectedIndex(-1);
            txtcoursename.setSelectedIndex(-1);
            coursecode.setText("");
            txtmark1.setText("");
            txtexmtype.setSelectedIndex(-1);
            txtsemister.setSelectedIndex(-1);

            txtid.requestFocus();

            Mark_Load();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_editbuttonActionPerformed

    private void deletebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebuttonActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            d = (DefaultTableModel) jTable1.getModel();
            int selectIndex = jTable1.getSelectedRow();

            int id = Integer.parseInt(d.getValueAt(selectIndex, 0).toString());

            pst = cdb.conn.prepareStatement("delete from mark where studentid=?");
            pst.setInt(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Mark details deleted successfully...");
            savebutton.setEnabled(true);
            txtid.setText("");
            txtname.setText("");
            txtdept.setSelectedIndex(-1);
            txtcoursename.setSelectedIndex(-1);
            coursecode.setText("");
            txtexmtype.setSelectedIndex(-1);
            txtsemister.setSelectedIndex(-1);
            txtmark1.setText("");

            txtid.requestFocus();

            Mark_Load();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deletebuttonActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
        Main m = new Main(id, uname, usertype);
        m.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenu1MouseClicked

    private void txtexmtypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtexmtypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtexmtypeActionPerformed

    private void txtdeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdeptActionPerformed
        // TODO add your handling code here:
        txtcoursename.removeAllItems();
        course_load();
    }//GEN-LAST:event_txtdeptActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        Login l = new Login();
        l.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void txtsemisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsemisterActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtsemisterActionPerformed

    private void txtcoursenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcoursenameActionPerformed
        // TODO add your handling code here:
       course_code();
    }//GEN-LAST:event_txtcoursenameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Mark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mark.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mark().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearbutton;
    private javax.swing.JTextField coursecode;
    private javax.swing.JButton deletebutton;
    private javax.swing.JButton editbutton;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton savebutton;
    private javax.swing.JLabel searchButton;
    private javax.swing.JComboBox<String> txtcoursename;
    private javax.swing.JComboBox<String> txtdept;
    private javax.swing.JComboBox<String> txtexmtype;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtmark1;
    private javax.swing.JTextField txtname;
    private javax.swing.JComboBox<String> txtsemister;
    // End of variables declaration//GEN-END:variables
}

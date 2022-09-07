package com.patikadev.View;

import com.oracle.tools.packager.Log;
import com.patikadev.Helper.*;
import com.patikadev.Model.Course;
import com.patikadev.Model.Operator;
import com.patikadev.Model.Patika;
import com.patikadev.Model.Users;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class OperatorGUI extends JFrame {

    private JPanel wrapper;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JScrollPane scrll_userList;
    private JPanel pnl_userList;
    private JTable tbl_userList;
    private JPanel pnl_addUser;
    private JLabel lbl_userName;
    private JTextField fld_userName;
    private JLabel lbl_password;
    private JTextField fld_password;
    private JComboBox cmb_type;
    private JButton btn_add;
    private JLabel fld_userID;
    private JTextField tfld_userID;
    private JButton btn_delete;
    private JTextField fld_sh_username;
    private JComboBox cmb_sh_type;
    private JButton btn_sh_user;
    private JPanel pnl_patika_list;
    private JScrollPane scrl_patika_list;
    private JTable tbl_patika_list;
    private JPanel pnl_patika_add;
    private JTextField fld_patika_name;
    private JButton btn_patika_add;
    private JPanel pnl_course_list;
    private JScrollPane scrl_course_pane;
    private JTable tbl_course_list;
    private JPanel pnl_course_add;
    private JTextField fld_course_name;
    private JTextField fld_course_lang;
    private JComboBox cmb_course_patika;
    private JComboBox cmb_course_user;
    private JButton btn_course_add;
    private DefaultTableModel mdl_userList;
    private Object[] row_userList;
    private final Operator operator;
    private DefaultTableModel mdl_patika_list;
    private Object[] row_patika_list;
    private JPopupMenu patikaMenu;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;


    public OperatorGUI(Operator operator) {
        this.operator = operator;

        Helper.setLayout();
        add(wrapper);
        setSize(1000, 500);
        int x = Helper.screenCenter("x", getSize());
        int y = Helper.screenCenter("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Welcome " + operator.getUser_name());

        //ModelUserList
        mdl_userList = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_userList = {"ID", "Username", "Password", "Type"};
        mdl_userList.setColumnIdentifiers(col_userList);
        row_userList = new Object[col_userList.length];
        loadUserModel();

        tbl_userList.setModel(mdl_userList);
        tbl_userList.getTableHeader().setReorderingAllowed(false);

        tbl_userList.getModel().addTableModelListener(e -> {
            if(e.getType() == TableModelEvent.UPDATE) {
                int user_id = Integer.parseInt(tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 0).toString());
                String user_name = tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 1).toString();
                String user_password = tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 2).toString();
                String user_type = tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 3).toString();

                if (Users.update(user_id, user_name, user_password, user_type)) {
                    Helper.showMsg("Successfully updated");

                }
                loadUserModel();
                loadEducatorCombo();
                loadCourseModel();
            }
        });

        tbl_userList.getSelectionModel().addListSelectionListener(e -> {
            try {
                String selected_user_id = tbl_userList.getValueAt(tbl_userList.getSelectedRow(), 0).toString();
                tfld_userID.setText(selected_user_id);
            } catch (Exception exception) {

            }

        });

        //PatikaList

        //courseList

        mdl_course_list = new DefaultTableModel();
        Object[] col_courseList = {"ID", "Course Name", "Programing Language", "Patika", "Educator"};
        mdl_course_list.setColumnIdentifiers(col_courseList);
        row_course_list = new Object[col_courseList.length];
        loadCourseModel();
        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        loadPatikaCombo();
        loadEducatorCombo();

        patikaMenu = new JPopupMenu();
        JMenuItem updateMenu = new JMenuItem("Update");
        JMenuItem deleteMenu = new JMenuItem("Delete");
        patikaMenu.add(updateMenu);
        patikaMenu.add(deleteMenu);



        updateMenu.addActionListener(e -> {
            int select_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(), 0).toString());
            UpdatePatikaGUI updateGUI = new UpdatePatikaGUI(Patika.getFetch(select_id));
            updateGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPatikaModel();
                    loadPatikaCombo();
                    loadCourseModel();
                }
            });
        });

        deleteMenu.addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int select_id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(), 0).toString());
                if (Patika.delete(select_id)) {
                    Helper.showMsg("done");
                    loadPatikaModel();
                    loadPatikaCombo();
                    loadCourseModel();
                }
                else {
                    Helper.showMsg("error");
                }
            }
        });

        mdl_patika_list = new DefaultTableModel();
        Object[] col_patika_list = {"ID", "Patika Name"};
        mdl_patika_list.setColumnIdentifiers(col_patika_list);
        row_patika_list = new Object[col_patika_list.length];
        loadPatikaModel();


        tbl_patika_list.setModel(mdl_patika_list);
        tbl_patika_list.setComponentPopupMenu(patikaMenu);
        tbl_patika_list.getTableHeader().setReorderingAllowed(false);
        tbl_patika_list.getColumnModel().getColumn(0).setMaxWidth(75);

        tbl_patika_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int selected_row = tbl_patika_list.rowAtPoint(point);
                tbl_patika_list.setRowSelectionInterval(selected_row, selected_row);
            }
        });
        btn_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_userName) || Helper.isFieldEmpty(fld_password)) {
                Helper.showMsg("fill");
            }
            else {
                String user_name = fld_userName.getText();
                String user_password = fld_password.getText();
                String user_type = cmb_type.getSelectedItem().toString();
                if (Users.add(user_name, user_password, user_type)) {
                    Helper.showMsg("done");
                    loadUserModel();
                    loadEducatorCombo();
                    fld_userName.setText(null);
                    fld_password.setText(null);
                }
            }
        });
        btn_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(tfld_userID)) {
                Helper.showMsg("fill");
            }
            else {
                if (Helper.confirm("sure")) {
                    int user_id = Integer.parseInt(tfld_userID.getText());
                    if (Users.delete(user_id)) {
                        Helper.showMsg("Delete successful");
                        loadUserModel();
                        loadEducatorCombo();
                        loadCourseModel();
                        tfld_userID.setText(null);
                    }
                    else {
                        Helper.showMsg("error");
                    }
                }
            }
        });
        btn_sh_user.addActionListener(e -> {
            String username = fld_sh_username.getText();
            String type = cmb_sh_type.getSelectedItem().toString();
            String query = Users.searchQuery(username, type);
            ArrayList<Users> searchingUser = Users.getList(query);
            loadUserModel(searchingUser);
        });
        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI login = new LoginGUI();
        });
        btn_patika_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_patika_name)) {
                Helper.showMsg("fill");
            }
            else {
                if (Patika.add(fld_patika_name.getText())) {
                    Helper.showMsg("done");
                    fld_patika_name.setText(null);
                    loadPatikaModel();
                    loadPatikaCombo();
                }
                else {
                    Helper.showMsg("error");
                }
            }
        });
        btn_course_add.addActionListener(e -> {
            Item patikaItem = (Item) cmb_course_patika.getSelectedItem();
            Item userItem = (Item) cmb_course_user.getSelectedItem();
            if(Helper.isFieldEmpty(fld_course_name) || Helper.isFieldEmpty(fld_course_lang)) {
                Helper.showMsg("fill");
            }
            else {
                if (Course.add(userItem.getKey(), patikaItem.getKey(), fld_course_name.getText(), fld_course_lang.getText())) {
                    Helper.showMsg("done");
                    loadCourseModel();
                    fld_course_lang.setText(null);
                    fld_course_name.setText(null);
                }
                else {
                    Helper.showMsg("error");
                }
            }
        });
    }

    private void loadCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (Course obj: Course.getList()) {
         i = 0;
         row_course_list[i++] = obj.getId();
         row_course_list[i++] = obj.getName();
         row_course_list[i++] = obj.getLang();
         row_course_list[i++] = obj.getPatika().getName();
         row_course_list[i++] = obj.getEducator().getUser_name();
         mdl_course_list.addRow(row_course_list);
        }
    }

    private void loadPatikaModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_patika_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (Patika obj: Patika.getList()) {
            i = 0;
            row_patika_list[i++] = obj.getId();
            row_patika_list[i++] = obj.getName();
            mdl_patika_list.addRow(row_patika_list);
        }
    }

    public void loadUserModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_userList.getModel();
        clearModel.setRowCount(0);

        for (Users obj: Users.getList()) {
            row_userList[0] = obj.getId();
            row_userList[1] = obj.getUser_name();
            row_userList[2] = obj.getUser_password();
            row_userList[3] = obj.getType();
            mdl_userList.addRow(row_userList);
        }
    }

    public void loadUserModel(ArrayList<Users> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_userList.getModel();
        clearModel.setRowCount(0);

        for (Users obj: list) {
            row_userList[0] = obj.getId();
            row_userList[1] = obj.getUser_name();
            row_userList[2] = obj.getUser_password();
            row_userList[3] = obj.getType();
            mdl_userList.addRow(row_userList);
        }
    }

    public void loadPatikaCombo() {
        cmb_course_patika.removeAllItems();
        for (Patika obj : Patika.getList()) {
            cmb_course_patika.addItem(new Item(obj.getId(), obj.getName()));
        }
    }

    public void loadEducatorCombo() {
        cmb_course_user.removeAllItems();
        for (Users obj: Users.getOnlyEducator()) {
                cmb_course_user.addItem(new Item(obj.getId(), obj.getUser_name()));

        }
    }

    public static void main(String[] args) {
        Operator op = new Operator();
        OperatorGUI opGUI = new OperatorGUI(op);
    }
}

package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Users {
    private int id;
    private String user_name;
    private String user_password;
    private String type;

    public Users() {

    }
    public Users(int id, String user_name, String user_password, String type) {
        this.id = id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.type = type;
    }

    public static ArrayList<Users> getList() {
        ArrayList<Users> userList = new ArrayList<>();
        String query = "SELECT * FROM users";
        Users obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Users();
                obj.setId(rs.getInt("user_id"));
                obj.setUser_name(rs.getString("user_name"));
                obj.setUser_password(rs.getString("user_password"));
                obj.setType(rs.getString("user_type"));
                userList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }


    public static ArrayList<Users> getList(String query) {
        ArrayList<Users> userList = new ArrayList<>();
        Users obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Users();
                obj.setId(rs.getInt("user_id"));
                obj.setUser_name(rs.getString("user_name"));
                obj.setUser_password(rs.getString("user_password"));
                obj.setType(rs.getString("user_type"));
                userList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }
    public static boolean add(String username, String password, String type) {
        String query = "INSERT INTO users (user_name, user_password, user_type) VALUES (?,?,?)";
        Users findUser = Users.getFetch(username);
        if (findUser != null) {
            Helper.showMsg("This username is used");
            return false;
        }
        boolean key = true;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            pr.setString(3, type);
            key = pr.executeUpdate() != -1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return key;
    }

    public static boolean delete(int userID) {
        String query = "DELETE FROM users WHERE user_id = ?";
        ArrayList<Course> courseList = Course.getListByUser(userID);
        for (Course c : courseList) {
            Course.delete(c.getId());
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, userID);
            return  pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean update(int id, String user_name, String user_password, String user_type) {
        String query = "UPDATE users SET user_name = ?, user_password = ?, user_type = ? WHERE user_id = ?";
        Users findUser = Users.getFetch(user_name);
        if (findUser != null && findUser.getId() != id) {
            Helper.showMsg("This username is used");
            return false;
        }
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, user_name);
            pr.setString(2, user_password);
            pr.setString(3, user_type);
            pr.setInt(4, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static Users getFetch(String user_name) {
        Users obj = null;
        String query = "SELECT * FROM users WHERE user_name = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, user_name);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Users();
                obj.setId(rs.getInt("user_id"));
                obj.setUser_name(rs.getString("user_name"));
                obj.setUser_password(rs.getString("user_password"));
                obj.setType(rs.getString("user_type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static Users getFetch(String user_name, String password) {
        Users obj = null;
        String query = "SELECT * FROM users WHERE user_name = ? AND user_password = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, user_name);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                switch (rs.getString("user_type")) {
                    case "operator":
                        obj = new Operator();
                        break;
                    default:
                        obj = new Users();
                }
                obj.setId(rs.getInt("user_id"));
                obj.setUser_name(rs.getString("user_name"));
                obj.setUser_password(rs.getString("user_password"));
                obj.setType(rs.getString("user_type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static Users getFetch(int id) {
        Users obj = null;
        String query = "SELECT * FROM users WHERE user_id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = new Users();
                obj.setId(rs.getInt("user_id"));
                obj.setUser_name(rs.getString("user_name"));
                obj.setUser_password(rs.getString("user_password"));
                obj.setType(rs.getString("user_type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static String searchQuery(String user_name, String user_type) {
        String query = "SELECT * FROM users WHERE user_name LIKE '%{{user_name}}%' AND user_type LIKE '%{{user_type}}%'";
        query = query.replace("{{user_name}}", user_name);
        query = query.replace("{{user_type}}", user_type);
        return query;
    }

    public static ArrayList<Users> getOnlyEducator() {
        ArrayList<Users> userList = new ArrayList<>();
        String query = "SELECT * FROM users WHERE user_type = 'educator'";
        Users obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new Users();
                obj.setId(rs.getInt("user_id"));
                obj.setUser_name(rs.getString("user_name"));
                obj.setUser_password(rs.getString("user_password"));
                obj.setType(rs.getString("user_type"));
                userList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

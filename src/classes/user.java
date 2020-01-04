/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author azz
 */
public class user {
    public static Connection con;
    public static Statement statement;
    public static PreparedStatement preparedStatement;
    public static ResultSet result;
    
    public static void connectDB()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/restoran", "root", "");            
            
        } catch (Exception e) {
            
            System.out.println(e);
            System.exit(1);
            
        }
    }
    
    
    public static Object[][] getUser()
    {
        int countData = 0;
        Object[][] dataResult = null;
        try {
            
            connectDB();
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM user");
            
            while(result.next()) {
                countData = result.getInt(1);
            }
            
            dataResult = new Object[countData][10];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT * FROM user");
            
            int counter = 0;
            
            while(result.next()) {
                dataResult[counter][0] = result.getString(1);
                dataResult[counter][1] = result.getString(2);
                dataResult[counter][2] = result.getString(3);
                dataResult[counter][3] = result.getString(4);
                dataResult[counter][4] = result.getString(5);
                ++counter;
            }
            
        } catch (Exception e) {
            System.exit(1);
        }
        
        return dataResult;
    }
    
     public static Object[][] getUser2()
    {
        int countData = 0;
        Object[][] dataResult = null;
        try {
            
            connectDB();
            statement = con.createStatement();
            result = statement.executeQuery("SELECT count(*) FROM user");
            
            while(result.next()) {
                countData = result.getInt(1);
            }
            
            dataResult = new Object[countData][4];
            statement = con.createStatement();
            result = statement.executeQuery("SELECT * FROM user");
            
            int counter = 0;
            
            while(result.next()) {
                dataResult[counter][0] = result.getString(1);
                dataResult[counter][1] = result.getString(2);
                dataResult[counter][2] = result.getString(3);
                dataResult[counter][3] = result.getString(4);
                
                ++counter;
            }
            
        } catch (Exception e) {
            System.exit(1);
        }
        
        return dataResult;
    }
    
    public static boolean addUser(String a,String b,String c,String d)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("INSERT INTO user VALUES ('', ?, ?, ?, ?)");
            preparedStatement.setString(1, a);
            preparedStatement.setString(2, b);
            preparedStatement.setString(3, c);
            preparedStatement.setString(4, d);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    public static boolean updateUser(String a,String b,String c,String d,String f)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("UPDATE user SET username = ?, password = ?, nama_user = ?, user_role = ? WHERE id_user = ?");            
            preparedStatement.setString(1, b);
            preparedStatement.setString(2, c);
            preparedStatement.setString(3, d);
            preparedStatement.setString(4, f);
            preparedStatement.setString(5, a);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    
    public static boolean deleteUser(String user_id)
    {
        try {
            connectDB();
            
            preparedStatement = con.prepareStatement("DELETE FROM user WHERE id_user = ?");
            preparedStatement.setString(1, user_id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

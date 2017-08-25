package LoginCheck.DAO;

import LoginCheck.Bean.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by LHF on 2017/6/29.
 */

/**
 * DAO层，用于对数据库的访问。
 */
public class UserDao {
    //查询数据库是否存在目标用户
    private User user=null;
    public boolean CheckUserExit(String Username, String Password){
        ResultSet Result = null;
        Statement stmt=null;
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lhf", "root", "as709394");
            stmt = conn.createStatement();
            String sql = "select * from java where User='" + Username + "' and password='" + Password+"'";

            try {
                Result = stmt.executeQuery(sql);
            } catch (Exception var6) {
                var6.printStackTrace();
            }
            if (Result.next()!=false) {
                user=new User(Result.getString("User"),Result.getString("password"),Result.getInt("class"),Result.getString("email"));
                Result.close();
                stmt.close();
                conn.close();
                return true;
            }
            return false;
        } catch (Exception var7) {
            var7.printStackTrace();
            return false;
        }
    }
    //查询用户，并返回一个该用户的User类
    public User CheckUser(String Username, String Password){
        if(CheckUserExit(Username,Password))
            return user;
        else return null;
    }
    //往数据库添加用户，要求四项必填
    public boolean AddUser(String Username, String Password, String Permit, String Email){
        ResultSet Result = null;
        Statement stmt=null;
        Connection conn=null;
        String sql=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lhf", "root", "as709394");
            stmt = conn.createStatement();
            if (CheckUserExit(Username,Password)) {
                Result.close();
                conn.close();
                stmt.close();
                return false;
            }
            else{
                sql="insert into java values('"+Username+"','"+Password+"','"+Permit+ "','"+Email+"')";
                System.out.println(Username+Password+Email);
                int i=stmt.executeUpdate(sql);
                if(i>0)
                    return true;
            }

        } catch (Exception var7) {
            var7.printStackTrace();
            return false;
        }
        return false;
    }

}

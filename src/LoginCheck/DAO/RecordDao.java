package LoginCheck.DAO;

/**
 * Created by LHF on 2017/7/12.
 */
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class RecordDao {
    //使用的时间类型仍未调试确定。
    static Date Login;
    static Date Logout;
    static Time StudyStart;
    static Time StudyOver;
    static int Points;

    public static void Store(String accessType) throws SQLException {
        HttpServletRequest request= ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        String User=(String) session.getAttribute("Username");
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        String sql=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost/lhf","root","as709394");
            stat=conn.createStatement();
            if (accessType=="Main") sql="insert into Access values('"+User+"','"+accessType+"','"+Login+"','"+Logout+"')";
            else sql=sql="insert into Access values('"+User+"','"+accessType+"','"+StudyStart+"','"+StudyOver+"')";
            rs=stat.executeQuery(sql);
            if (rs.next()) System.out.println("\nsuccess");
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            rs.close();;
            stat.close();
            conn.close();
        }
    }
}

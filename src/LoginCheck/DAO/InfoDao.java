package LoginCheck.DAO;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LHF on 2017/7/10.
 */
public class InfoDao {
    private Connection conn=null;
    private Statement stat=null;
    private ResultSet result=null;
    private List<String> Tables=new ArrayList<String>();
    public List<JSONArray> ListCheck(String UserID) throws SQLException {
        List<JSONArray> dataset=new ArrayList<JSONArray>() ;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc://localhost:3306/lhf","root","as709394");
            stat=conn.createStatement();
            String sql="select * from Access where UserID='"+UserID+"'";
            result=stat.executeQuery(sql);
            //本处进行数据库基本数据获取，直接根据已知表名进行信息提取而不加筛选。此处耦合性较高，不易改动。
            DatabaseMetaData dbmeta=conn.getMetaData();
            ResultSet rs=dbmeta.getTables(null,"lhf",null,new String[]{"table","view"});
            while (rs.next()){
                String tablename=rs.getString("TABLE_NAME");
                Tables.add(tablename);
            }

            if(result.next()!=false){
                JSONArray array=new JSONArray();
                ResultSetMetaData metaData=result.getMetaData();
                int column=metaData.getColumnCount();
                while(result.next()){
                    JSONObject data=new JSONObject();
                    for(int i=0;i<column;i++){
                        String name=metaData.getColumnName(i);
                        String value=result.getString(name);
                        data.put(name,value);
                    }
                    array.add(data);

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataset;
    }
}

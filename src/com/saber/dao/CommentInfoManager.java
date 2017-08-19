package com.saber.dao;

import com.mysql.jdbc.Statement;
import com.saber.databaseUtil.DBUtils;
import com.saber.pojo.CommentInfo;
import org.apache.struts2.interceptor.ApplicationAware;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Saber on 2017/6/30.
 */
public class CommentInfoManager implements ApplicationAware {
    /**
     *获取评论
     */
    public String allComment(){//将评论获取，通过applicationaware发送到前端
        try {
            Connection connection= DBUtils.getConnection();
            Statement statement=(Statement) connection.createStatement();
            String sql="SELECT * FROM commentinfo ORDER  BY date DESC";
            ResultSet resultSet=statement.executeQuery(sql);
            List<CommentInfo> commentInfoList=new ArrayList<>();
            while (resultSet.next()){
                CommentInfo commentInfo=new CommentInfo();
                commentInfo.setName(resultSet.getString("name"));
                commentInfo.setComment(resultSet.getString("comment"));
                commentInfo.setDate(resultSet.getString("date"));
                commentInfoList.add(commentInfo);
                //System.out.println(resultSet.getString("date"));
            }
            application.put("key",commentInfoList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "success";
    }
    private Map<String, Object> application;
    @Override
    public void setApplication(Map<String, Object> map) {
        this.application=map;
    }
}

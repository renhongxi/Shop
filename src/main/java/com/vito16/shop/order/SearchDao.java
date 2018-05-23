package com.vito16.shop.order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchDao {
    public StringBuffer  getText(String req){
        //首先定义下连接数据的URL、用户名、密码
        String url="jdbc:mysql://localhost:3306/shop";
        String user="root";
        String password="123456";
        String sql="select title from t_product a where a.title like ?";
        if(req.trim().length()==0){
            sql=sql+" and 1<>1";
        }
        List strList=new ArrayList();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con= DriverManager.getConnection(url,user,password);
            PreparedStatement pre=con.prepareStatement(sql);
            pre.setString(1,"%"+req.toUpperCase().trim()+"%");
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                String ename=rs.getString("ename");
                strList.add(ename);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        StringBuffer sb=new StringBuffer();
        int size=strList.size();
        for(int i=0;i<size;i++){
            sb.append((String)strList.get(i)+"-");
        }
        return sb;
    }
}

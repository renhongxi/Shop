package com.vito16.shop.order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Search extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String req=request.getParameter("txtSearch");
        PrintWriter out = response.getWriter();
        SearchDao sd=new SearchDao();
        sd.getText(req);
        StringBuffer sb=sd.getText(req);
        out.print(sb.toString());
        out.flush();
    }
}

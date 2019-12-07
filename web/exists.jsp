<%-- 
    Document   : exists
    Created on : Dec 1, 2019, 2:14:39 AM
    Author     : hp
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    try{
        String username=request.getParameter("username");
        Class.forName("com.mysql.jdbc.Driver");
        Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/tinywuzzuf?autoReconnect=true&useSSL=false","root", "Mahmoued23");
        Statement st=con.createStatement();    
        ResultSet rs = st.executeQuery("SELECT * FROM user WHERE " + "email = "+username);
            if(rs.next())
                    {    
                        out.println("<font color=red>");
                        out.println("Name already taken");
                        out.println("</font>");

                    }else {

                        out.println("<font color=green>");
                        out.println("Available");
                        out.println("</font>");

                    }
        }catch (Exception e){
//            System.out.println(e);  
        }
%>
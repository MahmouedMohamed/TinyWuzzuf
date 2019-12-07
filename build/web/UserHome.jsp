<%-- 
    Document   : UserHome
    Created on : Dec 1, 2019, 9:10:05 PM
    Author     : hp
--%>

<%@page import="java.util.Vector"%>
<%@page import="Models.Position"%>
<%@page import="Models.Candidate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            Candidate candidate=new Candidate();
            Vector<Position>position=new Vector<Position>();
        if (request.getAttribute("candidate") != null) {
            candidate = (Candidate) request.getAttribute("candidate");
        }
        if (request.getAttribute("positions") != null) {
            position = (Vector<Position>) request.getAttribute("positions");
        }
        %>
        <h1> Hello <%= candidate.get_username() %> </h1>
        <form action="UserController"> 
        <select name="job">
        <% 
            for(int i=0;i<position.size();i++)
            { 
        %>
        <div>
            <option value="<%= position.elementAt(i).getTitle() %>"><%= position.elementAt(i).getTitle() %></option>
        </div>
        <%
            }
        %>
        </select>
        <input type="submit" name="submit" value="Apply"> 
        </form>
    </body>
</html>

<%-- 
    Document   : UserHome
    Created on : Dec 1, 2019, 9:10:05 PM
    Author     : hp
--%>

<%@page import="Models.Message"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="jdk.jfr.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Vector"%>
<%@page import="Models.Position"%>
<%@page import="Models.Candidate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .notification 
            {
            background-color: #555;
            color: white;
            text-decoration: none;
            padding: 15px 26px;
            position: relative;
            display: inline-block;
            border-radius: 2px;
            }

            .notification:hover {
              background: red;
            }

            .notification .badge {
              position: absolute;
              top: -10px;
              right: -10px;
              padding: 5px 10px;
              border-radius: 50%;
              background: red;
              color: white;
            }
            </style>
            <script>
                
            </script>
    </head>
    <body>
        <% 
            Candidate candidate=new Candidate();
            Vector<Position>position=new Vector<Position>();
            Vector<Message> message=new Vector<Message>();
        if (request.getAttribute("candidate") != null) {
            candidate = (Candidate) request.getAttribute("candidate");
        }
        if (request.getAttribute("positions") != null) {
            position = (Vector<Position>) request.getAttribute("positions");
        }
        if (request.getAttribute("message") != null) {
            message = (Vector<Message>) request.getAttribute("message");
        }
        //message
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
        
            <a href="#" class="notification">
            <span>Inbox</span>
            <span class="badge"><%=message.size() %></span>
            </a>
            <%  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
Date date = new Date();
                %>
                <%=    formatter.format(date) %>

    </body>
</html>

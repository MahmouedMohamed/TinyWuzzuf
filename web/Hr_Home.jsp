<%-- 
    Document   : Hr_Home
    Created on : Nov 30, 2019, 9:02:43 PM
    Author     : hp
--%>

<%@page import="java.util.Vector"%>
<%@page import="Models.Candidate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="jquery-3.4.1.min.js"></script>
        <script>
            function hide(item){
            $(document).ready(function(){
               $(item).hide();
            });}
            </script>
    </head>
    <body>
        <h1> Hello <%=session.getAttribute("username")%> </h1>
       <form type="GET" action="HrController">
        <h1 class="approve">Approve or dis Approve</h1>
        <input class= "approve" type="submit" name="submit" value="approve"/>
        <h1>show Add update remove available exam types</h1>
        <input type="submit" name="submit" value="updateExam"/>
        <h1>show Add update remove questions and answers for available exam type</h1>
        <input type="submit" name="submit" value="updateQ&A"/>
        <h1>Search</h1>
        <input type="submit" name="submit" value="approve"/>
        <h1>See full tests</h1>
        <input type="submit" name="submit" value="See full tests"/>
        <h1>Show Summarized Report</h1>
        <input type="submit" name="submit" value="Report"/>
        </form>
    </body>
</html>

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
            <style>
div.header {
    display: block; text-align: center; 
    position: running(header);
}
div.footer {
    display: block; text-align: center;
    position: running(footer);
}
@page {
    @top-center { content: element(header) }
}
@page { 
    @bottom-center { content: element(footer) }
}
</style>
   <%response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);%>
    </head>
    <body>
        <div class='header'>
            <% if(session.getAttribute("username")!=null)
            { 
                %><h1> Hello <%=session.getAttribute("username")%> </h1><%
            }
            else
            {
                response.sendRedirect("Login.jsp"); 
            }%>
            <a href="Logout">Logout</a> 
        </div>
       <form type="GET" action="HrController">
        <h1 class="approve">Approve or dis Approve</h1>
        <input class= "approve" type="submit" name="decision" value="approve"/>
        <h1>show Add update remove available exam types</h1>
        <input type="submit" name="decision" value="show_exam"/>
        <input type="submit" name="decision" value="add_exam"/>
        <input type="submit" name="decision" value="update_exam"/>
        <input type="submit" name="decision" value="delete_exam"/>
        <h1>show Add update remove questions and answers for available exam type</h1>
        <input type="submit" name="decision" value="show_questions&answers"/>
        <input type="submit" name="decision" value="add_questions&answers"/>
        <input type="submit" name="decision" value="update_questions&answers"/>
        <input type="submit" name="decision" value="delete_questions&answers"/>
        <h1>See full tests</h1>
        <input type="submit" name="decision" value="checkTest&Solution"/>
        <input type="submit" name="decision" value="search"/>
        <h1>Show Summarized Report</h1>
        <input type="submit" name="decision" value="Report"/>
        </form>
    
        <div class='footer'>
        </div>
    </body>
</html>

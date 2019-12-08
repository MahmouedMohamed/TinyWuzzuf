<%-- 
    Document   : Apply
    Created on : Dec 7, 2019, 3:58:05 PM
    Author     : hp
--%>

<%@page import="Models.Exam"%>
<%@page import="java.util.Vector"%>
<%@page import="Models.Candidate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
            <script src="jquery-3.4.1.min.js"></script>
    <script  type="text/javascript"> 
        
        function addOption() { 
            optionText =$("#list option:selected").text(); 
            optionValue = $("#list option:selected").text().toString(); 
  
            $('#select1').append($('<option>').val(optionValue).text(optionText)).select(); 
            event.preventDefault();
        } 
        function removeOption() { 
            $('#select1 option:selected').remove(); 
            event.preventDefault();
        } 
      
function selectAll()
{
	for (var i = 0; i < document.getElementById("select1").options.length; i++)
	{
		document.getElementById("select1").options[i].selected = true;
	}
	return true;
}
    
        
    </script> 
        </script>
    </head>
    <body>
    <h1>Hello <%=session.getAttribute("username")%></h1>     
    <form action="HrController" onsubmit="return selectAll()" method="get">
<%
    if(Integer.parseInt(request.getAttribute("step").toString())==1)
    {
        Vector<String> candidateEmails=new Vector<String>();
        if(request.getAttribute("candidateEmails")!=null)
        {
            candidateEmails=(Vector<String>) request.getAttribute("candidateEmails");
        }
%>
        <h1> Select The applier </h1>
        <select name="candidateName">
            <%
        for(int i=0;i<candidateEmails.size();i++)
        {
            %>
            <option value="<%= candidateEmails.elementAt(i) %>">
            <%= candidateEmails.elementAt(i)%>
            </option><%
        }
            %>
            </select>
            <input type="submit" name="submit" value="submitApplier"/>
            <%
    }

    else if (Integer.parseInt(request.getAttribute("step").toString())==2)
    {
        Candidate candidate=new Candidate();
        if(request.getAttribute("candidate")!=null)
            {
                candidate=(Candidate)request.getAttribute("candidate");
            }
%>
        <h1> Select The job for <%=candidate.get_username()%></h1>
        <input type="text" name="candidateName" value="<%=candidate.get_username()%>" hidden></select>
        <select name="candidateJob">
            <%
        for(int i=0;i<candidate.get_appliedPosition().size();i++)
        {
            %>
            <option value="<%= candidate.get_appliedPosition().elementAt(i).getTitle() %>">
            <%= candidate.get_appliedPosition().elementAt(i).getTitle() %>
            </option><%
        }
            %>
            </select>
            <input type="submit" name="submit" value="DisApprove"/>
            <input type="submit" name="submit" value="submitJob"/>
            <%
    }

    else if (Integer.parseInt(request.getAttribute("step").toString())==3)
    {
        Candidate candidate=new Candidate();
        Vector<Exam> exam=new Vector<Exam>();
        if(request.getAttribute("candidate")!=null)
            {
                candidate=(Candidate)request.getAttribute("candidate");
            }
        if(request.getAttribute("exam")!=null)
            {
                exam=(Vector<Exam>)request.getAttribute("exam");
            }
%>
        <h1> Select The sequence of Exams for <%=candidate.get_username()%> to join <%=request.getAttribute("job")%></h1>
        <input type="text" name="candidateName" value="<%=candidate.get_username()%>" hidden></select>
        <input type="text" name="candidateJob" value="<%=request.getAttribute("job")%>" hidden></select>
        <select id="list" name="Exam">
            <%
        for(int i=0;i<exam.size();i++)
        {
            %>
            <option value="<%= exam.elementAt(i).getExamName() %> ">
             <%= exam.elementAt(i).getExamName() %>
            </option><%
        }
            %>
            </select>
            <select name="chosen" id="select1" multiple="multiple"> 
            </select>
            <button onclick="addOption()"> 
                Add option 
            </button> 
            <button onclick="removeOption()"> 
                Remove option 
            </button> 
            <input type="date" name="deadline">
<input type="submit" name="submit" value="Approve"/>
            <%
    }
%>
</form>
    </body>
</html>

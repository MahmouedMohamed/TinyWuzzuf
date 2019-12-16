/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Interaction;

import Models.Answer;
import Models.Exam;
import Models.Question;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class SolutionDB {
    Connection connection=null;
    ResultSet resultSet=null;
    Statement statement = null;
    public boolean initialSave(String type,Vector<Question> question,String email,String title)
    {	
        boolean flag=false;
        try{
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            for(int i=0;i<question.size();i++)
            {
                String sql="insert into `solution` VALUES("
                       + "'" + email + "'" + ","
                       +"'" + type +"'" + ","
                       + "'" + title + "'" + ","
                       +"'" + question.elementAt(i).getQID() +"'" + ","
                       +"'" + "null" +"'" 
                       +")"; 
               statement.executeUpdate(sql);
            }
            flag=true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
	return (flag);
    }
    public boolean saveAnswer(String email,String type,String QID,String AID,String title)
    {
        boolean flag=false;
        try{
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            String query="UPDATE `solution` SET"
                    + " `AID` = "+AID
                    +" WHERE email = "+email+" AND "
                    +"title = "+title+" AND "
                    +"type = "+type+" AND "
                    +"QID = "+QID;
            statement.executeUpdate(query);
            flag=true;
        }catch(SQLException ex)
        {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    public String getScore(String email,String Relatedexam,String type)
    {
        int score=0;
        Vector<String> AID=new Vector<String>();
        try{
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            String query="select `AID` from"
                    + " `solution` "
                    +" WHERE email = '"+email+"' AND "
                    +"type = '"+type+"' AND "
                    +"title = '"+Relatedexam+"'";
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next())
            {
                AID.add(resultSet.getString(1));
            }
            for(int i=0;i<AID.size();i++)
            {
                query="select status from `answer` where AID = '"+AID.elementAt(i)+"'";
                resultSet=statement.executeQuery(query);
                if(resultSet.next())
                {
                    if(resultSet.getString(1).equals("1"))
                    {
                        score++;
                    }
                }
            }
        }catch(SQLException ex)
        {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return String.valueOf(score+"/"+AID.size()) ;
    }
}

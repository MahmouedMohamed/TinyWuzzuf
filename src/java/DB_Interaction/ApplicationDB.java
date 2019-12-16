/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Interaction;

import Models.Exam;
import Models.Message;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class ApplicationDB {
    Connection connection=null;
    ResultSet resultSet=null;
    Statement statement = null;
    public Exam get(String email,String title) {
        Exam exam=null;
        try {
		String sql = "SELECT * FROM `application` where `email` ='"+email+"' AND "
                        +"title = "+"'"+title+"'";
		connection = DatabaseConnection.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		if(resultSet.next()) {
                        String type[]=resultSet.getString(4).split(" ");
                            exam=new Exam(type[0],resultSet.getString(1));
                            exam.setQuestion(new QuestionDB().getRandQuestion(type[0],email,title));
                        }
            }
        catch(SQLException ex)
        {
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
	return (exam);
    }

    public boolean isThereNext(String email,String title) {
        try {
		String sql = "SELECT * FROM `application` where `email` ='"+email+"' AND "
                        +"title = "+"'"+title+"'";
		connection = DatabaseConnection.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		if(resultSet.next()) {
                        String type[]=resultSet.getString(4).split(" ");
                        if(type.length>1)
                        {
//                            deleteTakenExam(type);
                            String examToTake="";
                            for(int i=1;i<type.length;i++)
                            {
                                examToTake+=type[i];
                                if(i<=type.length)
                                {
                                    examToTake+=" ";
                                }
                            }
                            String query="UPDATE `application` SET"
                                        + " `sequence` = '"+examToTake+"' ,"
                                        + " `deadline` = '"+resultSet.getString(3)+"'"
                                        +" WHERE email = '"+email+"' AND "
                                        +"title = '"+title+"'";
                            statement.executeUpdate(query);
                            return true;
                        }
                        else
                        {
                            String query="UPDATE `application` SET"
                                        + " `sequence` ="+"''"
                                        +" WHERE email = '"+email+"' AND "
                                        +"title = '"+title+"'";
                            statement.executeUpdate(query);
                            return false;
                        }
                }
        }catch(SQLException ex){
            Logger.getLogger(ExamDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
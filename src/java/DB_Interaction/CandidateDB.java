/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Interaction;

import Models.Candidate;
import Models.Position;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author hp
 */
public class CandidateDB{
    Connection connection=null;
    ResultSet resultSet=null;
    Statement statement = null;
    public Vector<Candidate> get() {
        Vector<Candidate> list=null;
        Candidate candidate=null;
        try {	
		list = new Vector<Candidate>();
		String sql = "SELECT * FROM `candidate`";
		connection = DatabaseConnection.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
                        candidate = new Candidate();
                        candidate.setUsername(resultSet.getString(1));
                        candidate.setPrevilige(resultSet.getString(2));
                        candidate.setTelephoneNumber(resultSet.getString(3));
			list.add(candidate);
                }
            }
        catch(Exception ex)
        {
            
        }
	return (list);
    }

    
    public Candidate get(String email) {
        Candidate candidate = null;
		try {
			candidate = new Candidate();
			String sql = "select * from user where email = "+email;
			connection = DatabaseConnection.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
                                candidate.setPrevilige(resultSet.getString(1));
				candidate.setUsername(resultSet.getString(2));
                            }
	}catch(Exception e) {
		e.printStackTrace();
	}
	return candidate;
    }
    
    public Candidate getDetailedCandidate(String email)
    {
        Candidate candidate=null;
        Vector<Position> position=null;
        try{
            candidate=new Candidate();
            position=new Vector<Position>();
            String sql="Select * from candidate where email="+email;
            connection = DatabaseConnection.openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if(resultSet.next())
            {
                candidate.setUsername(resultSet.getString(1));
                candidate.setPrevilige(resultSet.getString(2));
                candidate.setTelephoneNumber(resultSet.getString(3));
            }
            sql="Select * from cv where email="+candidate.get_username();
            resultSet = statement.executeQuery(sql);
            if(resultSet.next())
                {
//                        candidate.setCV(rs.getString(2));
                }
            sql="select title from apply where email= "+candidate.get_username();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next())
            {
                position.add(new Position(resultSet.getString(1)));
            }
            candidate.setAppliedPosition(position);
        }catch(Exception e) {
		e.printStackTrace();
	}
	return candidate;
    }
    
    public boolean save(Candidate candidate) {
        boolean flag = false;
	try {
                connection = DatabaseConnection.openConnection();
                statement = connection.createStatement();
		String query = "insert into `candidate` VALUES("
                    + "'" + candidate.get_username() + "'" + ","
                    +"'" + "user" +"'" + ","+ "'" + candidate.getTelephone() + "'"
                    +")";
		statement.executeUpdate(query);
                query="insert into `user` VALUES("
                    + "'" + "user" + "'" + ","
                    +"'" + candidate.get_username() +"'"
                            +")";
                statement.executeUpdate(query);
                query="insert into `cv` VALUES("
                    + "'" + candidate.get_username() + "'" + ","
                            +"'" + candidate.get_cv() +"'"
                                    +")";
                statement.executeUpdate(query);
		flag = true;
	}
        catch(Exception ex) {
		ex.printStackTrace();
        }
		return flag;
    }

    
    public boolean delete(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public boolean update(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

package it.befloral.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.befloral.beans.CustomerBean;

public class CustomerDAO extends IsDao<CustomerBean>{

	private static final String TABLE_NAME = "customer";
	
	
	public CustomerDAO() {
		super(TABLE_NAME);
	}
	
	@Override
	protected CustomerBean getParam(ResultSet result) throws SQLException {
		CustomerBean temp = new CustomerBean();
		temp.setId(result.getInt("id")); 
        temp.setFristname(result.getString("fristname")); 
        temp.setLastname(result.getString("lastname")); 
        temp.setEmail(result.getString("email")); 
        temp.setGender(result.getInt("gender")); 
        temp.setActive(result.getInt("active")); 
        temp.setNewsletter(result.getString("newsletter")); 
        temp.setPassword(result.getString("password")); 
        temp.setBirthday(result.getDate("birthday")); 
		return temp;
	
	}
	
	@Override
	protected void setParam(CustomerBean valueObject, PreparedStatement stmt) throws SQLException {

        stmt.setInt(1, valueObject.getId()); 
        stmt.setString(2, valueObject.getFristname()); 
        stmt.setString(3, valueObject.getLastname()); 
        stmt.setString(4, valueObject.getEmail()); 
        stmt.setInt(5, valueObject.getGender()); 
        stmt.setInt(6, valueObject.getActive()); 
        stmt.setString(7, valueObject.getNewsletter()); 
        stmt.setString(8, valueObject.getPassword()); 
        stmt.setDate(9, valueObject.getBirthday()); 
		
	}

	public boolean doInsert(CustomerBean valueObject ) throws SQLException {
	
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		boolean flag = false;
		
        try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(inserIntoSQL());

	        stmt.setString(1, valueObject.getFristname()); 
	        stmt.setString(2, valueObject.getLastname()); 
	        stmt.setString(3, valueObject.getEmail()); 
	        stmt.setInt(4, valueObject.getGender()); 
	        stmt.setInt(5, valueObject.getActive()); 
	        stmt.setString(6, valueObject.getNewsletter()); 
	        stmt.setString(7, valueObject.getPassword()); 
	        stmt.setDate(8, valueObject.getBirthday()); 
			
			
			
			 flag  = stmt.execute();
        	
			 return flag;
			 
  
    } finally {
        if (result != null)
            result.close();
        if (stmt != null)
            stmt.close();
        
    }
		
        
		
	}
		
	
	
	
	public CustomerBean doRetriveByEmail(String email) throws SQLException{
		String sql = "SELECT * FROM "+TABLE_NAME+" WHERE email = ? ";
		
        Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		

        try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, email); 
			result = stmt.executeQuery();
        	
        	CustomerBean temp = null;
        	if(result.next()) {
        			temp =getParam(result);
        			
        	}
        	
        	return temp;
  
    } finally {
        if (result != null)
            result.close();
        if (stmt != null)
            stmt.close();
    }
		
		
	}
	
	
	
	@Override
	protected String inserIntoSQL() {
        String sql  = "INSERT INTO "+TABLE_NAME+" ( fristname, lastname, "
    			+ "email, gender, active, "
    			+ "newsletter, password, birthday) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?) ";
		return sql;
	}

	@Override
	protected String updateSQL() {
        String sql  = "UPDATE "+TABLE_NAME+" ( id, fristname, lastname, "
    			+ "email, gender, active, "
    			+ "newsletter, password, birthday) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		return sql;
	}
	

	@Override
	protected String deleteSQL() {
		String sql = "DELETE FROM "+TABLE_NAME+" WHERE (id = ? ) ";
		return sql;
	}

	
	
	
}

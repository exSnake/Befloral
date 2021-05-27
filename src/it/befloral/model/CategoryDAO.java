package it.befloral.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.befloral.beans.Category;
import it.befloral.beans.Order;
import it.befloral.beans.Product;

public class CategoryDAO implements GenericDAO<Category> {
	
	
	private static DataSource ds;
	private static final String TABLE_NAME = "category";
	private static final String PIVOTE_TABLE_NAME = "belongs";

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/befloral");
		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	

	@Override
	public Collection<Category> doRetrieveAll(String order) throws SQLException {
		
		Collection<Category> cat = new LinkedList<>();
		String selectSQL = "SELECT * FROM "+PIVOTE_TABLE_NAME+"AS b JOIN "+TABLE_NAME+" AS c ON c.id=bb.cid ";
		
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				ResultSet rs = stmt.executeQuery();
				int lastCategoryid;
				
				if(rs.next()) {//se c'e almeno una riga
					
					do {
					
					Category bean = new Category();
					bean.setId(rs.getInt("id"));
					bean.setDescription(rs.getString("description"));
					bean.setMetaKeywords(rs.getString("metaKewords"));
					bean.setMetaTitle(rs.getString("metaTitle"));
					bean.setDescription(rs.getString("metaDescription"));								
			
					do {						
						if(bean.getId()==rs.getInt("id"))
							bean.addProductId(rs.getInt("pid"));
						else break;
					}while(rs.next());
					
					cat.add(bean);
						continue;
					}while(rs.next());
					
				}							
					
			}
		}
		return cat;
	}
			
		
	@Override
	public Category doRetriveByKey(int code) throws SQLException {

		
	}

	@Override
	public void doSave(Category dao) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int doUpdate(Category dao) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public Category doRetriveByProduct(int codeProduct) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean doChangeCategory(int codeProduct , int newCode) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}

package it.befloral.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	private static final String TABLE_NAME = "categories";
	private static final String PIVOTE_TABLE_NAME = "categories_products";

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
					bean.setName("name");
					bean.setDescription(rs.getString("description"));
					bean.setMetaKeywords(rs.getString("metaKewords"));						
			
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

		String selectSQL = "SELECT * FROM "+PIVOTE_TABLE_NAME+"AS b JOIN "+TABLE_NAME+" AS c ON c.id=bb.cid WHERE category.id=?";
		
		Category bean = new Category();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				ResultSet rs = stmt.executeQuery();
				
				
				if(rs.next()) {//se c'e almeno una riga
					
					bean.setId(rs.getInt("id"));
					bean.setName("name");
					bean.setDescription(rs.getString("description"));
					bean.setMetaKeywords(rs.getString("metaKewords"));								
					bean.addProductId(rs.getInt("pid"));
					
					do {
						bean.addProductId(rs.getInt("pid"));
					}while(rs.next());

				}												
			}
		}
		return bean;
	}

	@Override
	public void doSave(Category dao) throws SQLException {
		//salvare la categoria
		String insertSQLCategory = "INSERT INTO "+TABLE_NAME+"  (`name`, `description`, `metaKeywords`) VALUES (?,?,?)";
		
				
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(insertSQLCategory)) {
				
				stmt.setString(1, dao.getName());
				stmt.setString(2, dao.getDescription());
				stmt.setString(3, dao.getMetaKeywords());
				
				stmt.execute();
				
			
				}
			}
		}

	/*/String insertSQLBelongs = "INSERT INTO "+PIVOTE_TABLE_NAME+" (`pid`, `cid`) VALUES (?,?);";
	 * 					ArrayList<Integer> arry = dao.getProductsId();
					for (Integer i : arry) {
						
						try (var stmt2 = conn.prepareStatement(insertSQLBelongs)) {
							stmt2.setInt(1, i);
							stmt.setInt(2, dao.getId());
							stmt2.execute();
						}	
					}	
	 * 
	 * */

	
	
	
	
	@Override
	public int doUpdate(Category dao) throws SQLException {
		//Salvare la categoria
		String updateSQL = "UPDATE  SET `name`=?, `description`=?, `metaKeywords`=?  WHERE  `id`=?";
		
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(updateSQL)) {
		
				stmt.setString(1, dao.getName());
				stmt.setString(2, dao.getDescription());
				stmt.setString(3, dao.getMetaKeywords());

				stmt.setInt(4, dao.getId());
				
				return stmt.executeUpdate();
			}
		}
		
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		String deleteSQL = "DELETE FROM "+ TABLE_NAME +" WHERE  `id`= ? ";
		
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(deleteSQL)) {
				
				stmt.setInt(1, code);
				stmt.executeQuery();
				return true;
		
			}
		}
	}

	
	
	public ArrayList<Category> doRetriveByProduct(int codeProduct) throws SQLException {
		//Retrive all category of products
		String selectSQL="SELECT DISTINCT  c.id,c.name,c.description,c.metaKeywords"
				+ " FROM "+PIVOTE_TABLE_NAME+" AS b JOIN "+TABLE_NAME+" AS c ON b.cid=c.id WHERE pid=?";
		
		ArrayList<Category> list = new ArrayList<Category>();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				
				stmt.setInt(1, codeProduct);
				ResultSet rs = stmt.executeQuery();
				
				
				while(rs.next()) {//se c'e almeno una riga
					Category bean = new Category();
					
					bean.setId(rs.getInt("id"));
					bean.setName("name");
					bean.setDescription(rs.getString("description"));
					bean.setMetaKeywords(rs.getString("metaKewords"));							
					list.add(bean);			
				}
				return list;
			}			
		}		
	}
	
	public boolean doChangeCategory(int codeProduct ,int lastCategory , int newCategory) throws SQLException {
		String updateSQL = "UPDATE "+PIVOTE_TABLE_NAME+" SET `cid`=? WHERE  `pid`=? AND `cid`=? ;";


		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(updateSQL)) {
				
				stmt.setInt(1, newCategory);
				stmt.setInt(2, codeProduct);
				stmt.setInt(3, lastCategory);
				ResultSet rs = stmt.executeQuery();
			
				return true;
			}
		}
		
	}
	
	
}

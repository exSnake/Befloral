package it.befloral.dao;

import java.sql.SQLException;
import java.util.Collection;

public interface ModelDAO<T> {
	
	public T doRetriveByKey(int code) throws SQLException;
	
	public Collection<T> doRetrieveAll(String order) throws SQLException;
	
	public void doSave(T dao) throws SQLException;
	
	public void doUpdate(T dao) throws SQLException;
	
	public boolean doDelete(int code) throws SQLException;
	
}

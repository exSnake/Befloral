package it.befloral.model;

import java.sql.SQLException;
import java.util.Collection;

import it.befloral.beans.ProductBean;

public interface GenericDAO<T> {

	Collection<T> doRetrieveAll(String order) throws SQLException;

	T doRetriveByKey(int code) throws SQLException;

	void doSave(T dao) throws SQLException;

	int doUpdate(T dao) throws SQLException;

	boolean doDelete(int code) throws SQLException;

}
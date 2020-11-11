package ca.sheridancollege.tanciong.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.tanciong.beans.Shopping;


@Repository
public class DatabaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	
	public void insertShopping(Shopping shopping) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO shopping(itemName, itemDescription, price, qty, storeName, url) "
				+ "VALUES (:itemName, :itemDescription, :price, :qty, :storeName, :url)";
		namedParameters.addValue("itemName", shopping.getItemName());
		namedParameters.addValue("itemDescription", shopping.getItemDescription());
		namedParameters.addValue("price", shopping.getPrice());
		namedParameters.addValue("qty", shopping.getQty());
		namedParameters.addValue("storeName", shopping.getStoreName());
		namedParameters.addValue("url", shopping.getUrl());
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0)
			System.out.println("Inserted movie review into database.");
	}
	
	public List<Shopping> getShoppingList(){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM shopping ";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Shopping>(Shopping.class));
	}
	
	public List<Shopping> getShoppingListById(Long id){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM shopping WHERE id= :id";
		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Shopping>(Shopping.class));
	}
	
	public List<Shopping> filterShoppingListByStoreName(String storeName){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM shopping WHERE storeName= :storeName";
		namedParameters.addValue("storeName",storeName);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Shopping>(Shopping.class));
	}
	
	public void deleteShoppingById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM shopping WHERE id= :id";
		namedParameters.addValue("id", id);
		int rowsAffected = jdbc.update(query, namedParameters);
		if(rowsAffected>0)
			System.out.println("Deleted shopping item" + id + "from database");
	}
	
	public List<Shopping> orderByStoreName() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM shopping ORDER BY storename";
		
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Shopping>(Shopping.class));
	}
	
}

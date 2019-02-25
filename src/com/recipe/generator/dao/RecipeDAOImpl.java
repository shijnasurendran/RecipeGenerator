package com.recipe.generator.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.recipe.generator.model.Ingredient;
import com.recipe.generator.model.User;

public class RecipeDAOImpl implements RecipeDAO {

	@Autowired
	DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub

		jdbcTemplate = new JdbcTemplate(dataSource);
		String insertQuery = "insert into superchef.user values (?,?,?,?,?)";
		System.out.println(user.toString());
		int rtrn = jdbcTemplate.update(insertQuery, new Object[] { user.getId(), user.getFirstName(),
				user.getLastName(), user.getEmail(), user.getPassword() });
		System.out.println(rtrn);
		return rtrn;
	}

	@Override
	public User checkUser(User user) throws DataAccessException, Exception {
		// TODO Auto-generated method stub
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select * from superchef.user where lastName='" + user.getLastName() + "' and passwords='"
				+ user.getPassword() + "'";
		List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet arg0, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				User u = new User();
				u.setId(arg0.getString("userId"));
				u.setFirstName(arg0.getString("firstName"));
				u.setLastName(arg0.getString("lastName"));
				u.setEmail(arg0.getString("email"));
				// u.setId(arg0.getString("UserId"));
				return u;
			}
		});
		// int userId = jdbcTemplate.queryForObject(sql, null, int.class);
		// User u = new User();
		// u.setId(""+userId);
		return userList.get(0);
	}

	@Override
	public List<Ingredient> getMyFridge(User user) {
		// TODO Auto-generated method stub
		jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select * from superchef.ingredient where IngredientId "
				+ "IN (select IngredientId from superchef.myfridge where UserId =" + user.getId() + ")";

		return jdbcTemplate.query(sql, new ResultSetExtractor<List<Ingredient>>() {

			@Override
			public List<Ingredient> extractData(ResultSet arg0) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<Ingredient> ingredients = new ArrayList<Ingredient>();
				while (arg0.next()) {
					Ingredient i = new Ingredient();
					i.setIngredientId("" + arg0.getInt(1));
					i.setIngredientName(arg0.getString(2));
					ingredients.add(i);
				}
				return ingredients;
			}

		});
	}

	@Override
	public int addIngredients(User user, String[] ingredients) throws DataAccessException {
		// TODO Auto-generated method stub
		jdbcTemplate = new JdbcTemplate(dataSource);
		String insertIngredients = "insert into superchef.ingredient values (?,?)";
		try {
			jdbcTemplate.batchUpdate(insertIngredients, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					// TODO Auto-generated method stub

					String temp = ingredients[i];
					ps.setString(1, null);
					ps.setString(2, temp.trim());
				}

				@Override
				public int getBatchSize() {
					// TODO Auto-generated method stub
					return ingredients.length;
				}
			});
		} catch (Exception ex) {

		}
		List<String> ingredientIds = new ArrayList<String>();
		for (int i = 0; i < ingredients.length; i++) {
			String sql = "select ingredientId from superchef.ingredient where ingredienttName='" + ingredients[i].trim()
					+ "'";
			String id = jdbcTemplate.queryForObject(sql, null, String.class);
			ingredientIds.add(id);
		}

		for (String string : ingredientIds) {
			String sql = "insert into superchef.myfridge values (" + user.getId() + "," + string + ")";
			jdbcTemplate.update(sql);
		}
		return 1;
	}

	@Override
	public int removeIngredient(User user, String ingredient) throws DataAccessException {
		// TODO Auto-generated method stub
		jdbcTemplate = new JdbcTemplate(dataSource);
		String deleteQuery = "delete from superchef.myfridge where ingredientId=(select ingredientID from superchef.ingredient where ingredienttName='"+ingredient.trim()+"')";
		jdbcTemplate.update(deleteQuery);
		return 0;
	}

	@Override
	public String toggleFavorite(User user, String rid) throws DataAccessException {
		// TODO Auto-generated method stub
		jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "insert into superchef.favourite values ("+user.getId()+","+rid+")";
		String delete = "delete from superchef.favourite where userId="+user.getId()+" and recipeId="+rid;
		String result = "";
		try {
			jdbcTemplate.update(query);
			result = "favorite";
		}catch(Exception ex) {
			jdbcTemplate.update(delete);
			result = "unfavorite";
		}
		return result;
	}

	@Override
	public List<String> getFavoriteRecipesIds(User user) throws DataAccessException {
		// TODO Auto-generated method stub
		jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "select recipeId from superchef.favourite where userId="+user.getId();
		List<String> recipeIds = jdbcTemplate.queryForList(query, String.class);
		return recipeIds;
	}
}

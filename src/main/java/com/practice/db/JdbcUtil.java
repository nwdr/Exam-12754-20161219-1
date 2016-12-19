package com.practice.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.practice.db.entities.Film;

public class JdbcUtil {

	public static Statement statement = JdbcConnect.getStatement();

	public static ResultSet getCustomerByName(String name) throws SQLException {
		String sql = "select * from customer where first_name = '" + name + "'";
		ResultSet resultSet = statement.executeQuery(sql);
		return resultSet;
	}

	public static ResultSet getLanguage() throws SQLException {
		String sql = "select * from language";
		ResultSet resultSet = statement.executeQuery(sql);
		return resultSet;
	}

	public static ResultSet getFilms() throws SQLException {
		String sql = "select f.film_id,f.title,f.description,l.name "
				+ "from film f,language l where f.language_id = l.language_id";
		ResultSet resultSet = statement.executeQuery(sql);
		return resultSet;
	}

	public static ResultSet getFilmsById(Film film) throws SQLException {
		String sql = "select f.title,f.description,l.name " + "from film f,language l "
				+ "where f.language_id = l.language_id " + "and f.film_id = " + film.getFilmId();
		ResultSet resultSet = statement.executeQuery(sql);
		return resultSet;
	}

	public static void updateFilm(Film film) throws SQLException {
		String sql = "update film set title = '" + film.getTitle() + "',description = '" + film.getDescription()
				+ "',language_id = " + film.getLanguage().getLanguageId()
				+ " where film_id = " + film.getFilmId();
		statement.executeUpdate(sql);
	}

	public static int deleteFilm(Film film) throws SQLException {
		String sql = "delete from film where film_id = " + film.getFilmId();
		int re = statement.executeUpdate(sql);
		return re;
	}

	public static int addFilm(Film film) throws SQLException {
		String sql = "insert into film(title,description,language_id)"
				+ " values('"+film.getTitle()+"','"
						 + film.getDescription()+"',"
						 + film.getLanguage().getLanguageId()+ ")";
		int re = statement.executeUpdate(sql);
		return re;
	}
	
}

package com.practice.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.practice.db.JdbcUtil;
import com.practice.db.entities.Film;
import com.practice.db.entities.Language;

public class FilmServlet extends HttpServlet {

	private List<Film> films;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String handle = request.getParameter("handle");
		request.setAttribute("username", 
				request.getSession().getAttribute("username"));
		if ("find".equals(handle)) {
			try {
				find(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("delete".equals(handle)) {
			try {
				deleteFilmById(request, response);
			} catch (SQLException e) {
				if(e.getClass().getName().equals("com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException")){
					PrintWriter out = response.getWriter();
					out.println("由于该数据跟其他信息有所关联，故不能删除！<br><br>"
					  + "<a href='/filmServlet?handle=find'>返回查看film</a>");
				}
				e.printStackTrace();
			}
		} else if ("add".equals(handle)){
			try {
				addFilm(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("addFilm".equals(handle)){
			try {
				myAddFilm(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("update".equals(handle)){
			try {
				getFilmById(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("updateFilm".equals(handle)){
			try {
				updateFilm(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void myAddFilm(HttpServletRequest request,
			HttpServletResponse response) 
				throws ServletException, IOException, SQLException{
		Film film = new Film();
		Language language = new Language();
		film.setTitle(request.getParameter("title"));
		film.setDescription(request.getParameter("description"));
		language.setLanguageId(
				Integer.parseInt(request.getParameter("name")));
		film.setLanguage(language);
		JdbcUtil.addFilm(film);
		request.getRequestDispatcher("/filmServlet?handle=find").forward(request, response);
	}
	
	private void addFilm(HttpServletRequest request,
			HttpServletResponse response) 
					throws SQLException, ServletException, IOException{
		request.setAttribute("languages", getLanguages());
		request.getRequestDispatcher("/addFilm.jsp").forward(request, response);
	}
	
	private void updateFilm(HttpServletRequest request,
			HttpServletResponse response) 
					throws SQLException, ServletException, IOException{
		Film film = new Film();
		Language language = new Language();
		film.setFilmId(Integer.parseInt(request.getParameter("filmId")));
		film.setTitle(request.getParameter("title"));
		film.setDescription(request.getParameter("description"));
		language.setLanguageId(
				Integer.parseInt(request.getParameter("name")));
		film.setLanguage(language);
		JdbcUtil.updateFilm(film);
		request.getRequestDispatcher("/filmServlet?handle=find").forward(request, response);
	}

    private void getFilmById(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
    	Film film = new Film();
    	Language language = new Language();
		Integer filmId = Integer.parseInt(request.getParameter("filmId"));
		film.setFilmId(filmId);
		ResultSet rs = JdbcUtil.getFilmsById(film);
		if(rs.next()){
			film.setTitle(rs.getString(1));
			film.setDescription(rs.getString(2));
			language.setName(rs.getString(3));
			film.setLanguage(language);
			request.setAttribute("film", film);
		}
		request.setAttribute("languages", getLanguages());
		request.getRequestDispatcher("/updateFilm.jsp").forward(request, response);
    }
	
    private List<Language> getLanguages() throws SQLException{
    	List<Language> languages = new ArrayList<Language>();
    	ResultSet rs = JdbcUtil.getLanguage();
    	while(rs.next()){
    		Language language = new Language();
    		language.setLanguageId(rs.getInt(1));
    		language.setName(rs.getString(2));
    		languages.add(language);
    	}
    	return languages;
    }
    
	private void deleteFilmById(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		Film film = new Film();
		Integer filmId = Integer.parseInt(request.getParameter("filmId"));
		film.setFilmId(filmId);
		JdbcUtil.deleteFilm(film);
		request.getRequestDispatcher("/filmServlet?handle=find").forward(request, response);
	}

	private void find(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		request.setAttribute("films", getFilms());
		request.getRequestDispatcher("/film.jsp").forward(request, response);
	}

	private List<Film> getFilms() throws SQLException {
		films = new ArrayList<Film>();
		ResultSet rs = JdbcUtil.getFilms();
		while (rs.next()) {
			Film film = new Film();
			Language language = new Language();
			film.setFilmId(rs.getInt(1));
			film.setTitle(rs.getString(2));
			film.setDescription(rs.getString(3));
			language.setName(rs.getString(4));
			film.setLanguage(language);
			films.add(film);
		}
		return films;
	}

}

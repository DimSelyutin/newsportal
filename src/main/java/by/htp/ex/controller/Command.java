package by.htp.ex.controller;



import java.io.IOException;
import java.sql.SQLException;

import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.connection.ConnectionPoolException;
import by.htp.ex.service.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DaoException, ConnectionPoolException, SQLException, ServiceException;
}

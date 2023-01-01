package by.htp.ex.service;

import java.sql.SQLException;
import java.util.List;

import by.htp.ex.bean.Category;
import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.connectionPool.ConnectionPoolException;

public interface INewsService {
    void save(News news);
    void find(int idNews);
    void update(News news) throws SQLException, ConnectionPoolException;
    
    List<Category> findAllCategoryes() throws SQLException;
    List<News> latestList(int count)  throws ServiceException, ConnectionPoolException, SQLException, DaoException;
    List<News> list()  throws ServiceException, ConnectionPoolException, SQLException, DaoException;
    News findById(String id) throws ServiceException, ConnectionPoolException, SQLException, DaoException;	
	
}

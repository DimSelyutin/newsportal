package by.htp.ex.dao;

import java.sql.SQLException;
import java.util.List;

import by.htp.ex.bean.Category;
import by.htp.ex.bean.News;
import by.htp.ex.dao.connectionPool.ConnectionPoolException;


public interface INewsDAO {
    void addNews(News news) throws SQLException;
    void update(News news) throws SQLException, ConnectionPoolException;
    void deleteNews(String idNews) throws SQLException;
    void writeLike(String idNews) throws SQLException;
    News getNews(String id) throws ConnectionPoolException, SQLException, DaoException;
    List<Category> findAllCategory()  throws  SQLException;
    List<News> findByCategory(String category) throws ConnectionPoolException, SQLException, DaoException;
    List<News> getAllNews() throws ConnectionPoolException, SQLException, DaoException;
}

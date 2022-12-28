package by.htp.ex.dao;

import java.sql.SQLException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.connection.ConnectionPoolException;

public interface INewsDAO {
    void addNews();
    
    void deleteNews(News news);
    News getNews(int id) throws ConnectionPoolException, SQLException;
    String createDatePost();
    

    List<News> getAllNews() throws ConnectionPoolException, SQLException;
}

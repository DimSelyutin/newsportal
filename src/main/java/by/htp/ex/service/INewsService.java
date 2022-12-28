package by.htp.ex.service;

import java.sql.SQLException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.connection.ConnectionPoolException;

public interface INewsService {
    void save();
    void find();
    void update();
    
    List<News> latestList(int count)  throws ServiceException, ConnectionPoolException, SQLException;
    List<News> list()  throws ServiceException, ConnectionPoolException, SQLException;
    News findById(int id) throws ServiceException, ConnectionPoolException, SQLException;	
	
}

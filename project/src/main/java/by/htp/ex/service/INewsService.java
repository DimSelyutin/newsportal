package by.htp.ex.service;


import java.util.List;

import by.htp.ex.bean.Category;
import by.htp.ex.bean.News;



public interface INewsService {
    void save(News news) throws ServiceException;
    void find(int idNews) throws ServiceException;
    void update(News news) throws ServiceException;
    void delete(String idNews) throws ServiceException;
    void addLike(String newsId) throws ServiceException;	
    
    List<News> sortByCategory(String category) throws ServiceException;
    List<Category> findAllCategoryes() throws ServiceException;
    List<News> latestList(int count)  throws ServiceException;
    List<News> list()  throws ServiceException;
    News findById(String id) throws ServiceException;
	
}

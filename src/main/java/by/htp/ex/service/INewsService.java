package by.htp.ex.service;


import java.util.List;

import by.htp.ex.bean.Category;
import by.htp.ex.bean.News;



public interface INewsService {
    boolean save(News news) throws ServiceException;
    void find(int idNews) throws ServiceException;
    boolean update(News news) throws ServiceException;
    boolean delete(String idNews) throws ServiceException;
    void addLike(String idUser, String idNews) throws ServiceException;	
    
    List<News> sortByCategory(String category) throws ServiceException;
    List<News> sortByDate(String local) throws ServiceException;
    List<Category> findAllCategoryes() throws ServiceException;
    List<News> latestList(String local, int count)  throws ServiceException;
    List<News> list(String local)  throws ServiceException;
    News findById(String local, String id) throws ServiceException;
	
}

package by.htp.ex.dao;


import java.util.List;

import by.htp.ex.bean.Category;
import by.htp.ex.bean.News;



public interface INewsDAO {
    void addNews(News news) throws DaoException;
    void update(News news) throws DaoException;
    void deleteNews(String idNews) throws DaoException;
    void writeLike(String idNews) throws DaoException;
    News getNews(String id) throws DaoException;
    List<Category> findAllCategory()  throws  DaoException;
    List<News> findByCategory(String category) throws DaoException;
    List<News> getAllNews() throws DaoException;
}

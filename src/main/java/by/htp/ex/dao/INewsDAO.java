package by.htp.ex.dao;


import java.util.List;

import by.htp.ex.bean.Category;
import by.htp.ex.bean.News;



public interface INewsDAO {
    boolean addNews(News news) throws DaoException;
    boolean update(News news) throws DaoException;
    boolean deleteNews(String idNews) throws DaoException;
    boolean saveTranslate(News news) throws DaoException;
    List<String> getLikedNews(String idUser)throws DaoException; 
    int getCountLike(String idNews) throws DaoException;
    boolean writeLike(String idUser, String idNews) throws DaoException;
    News getNews(String local, String id) throws DaoException;
    List<Category> findAllCategory()  throws  DaoException;
    List<News> findByCategory(String category) throws DaoException;
    List<News> getAllNews(String local) throws DaoException;

}

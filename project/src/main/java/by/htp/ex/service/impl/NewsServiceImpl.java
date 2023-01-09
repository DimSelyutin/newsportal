package by.htp.ex.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.Category;
import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.connectionPool.ConnectionPoolException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;

public class NewsServiceImpl implements INewsService{


    private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();


    @Override
    public void save(News news) {
        try {
            newsDAO.addNews(news);
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        
        
    }

    @Override
    public void find(int idNews) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(String idNews) throws SQLException {
       newsDAO.deleteNews(idNews);
        
    }
    
    @Override
    public List<Category> findAllCategoryes()  throws ConnectionPoolException, SQLException, DaoException {

        List<Category> listCateory = newsDAO.findAllCategory();
        return listCateory;
    }
    
    @Override
    public void update(News news) throws SQLException, ConnectionPoolException {
        newsDAO.update(news);
        
    }

    @Override
    public List<News> latestList(int count) throws ServiceException, ConnectionPoolException, SQLException, DaoException {
        List<News> listNews;
        listNews = newsDAO.getAllNews().subList(0, count);
        return listNews;
    }

    @Override
    public List<News> list() throws ServiceException, ConnectionPoolException, SQLException, DaoException {
        List<News> listNews;
        listNews = newsDAO.getAllNews();
        return listNews;
    }

    @Override
    public News findById(String id) throws ServiceException, ConnectionPoolException, SQLException, DaoException {
        News news = newsDAO.getNews(id);
        return news;
    }

    @Override
    public List<News> sortByCategory(String category) throws ServiceException, ConnectionPoolException, SQLException, DaoException {
        List<News> allNews = newsDAO.findByCategory(category);
        return allNews;
    }

    @Override
    public void addLike(String newsId) throws SQLException {
        newsDAO.writeLike(newsId);
        
    }

}

package by.htp.ex.service.impl;


import java.util.List;

import by.htp.ex.bean.Category;
import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;

public class NewsServiceImpl implements INewsService {

    private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();
    private final String MSG_ERR = "Somthing went wrong!";

    @Override
    public void save(News news) throws ServiceException {
        try {
            newsDAO.addNews(news);
        } catch (DaoException e) {

            throw new ServiceException(MSG_ERR, e);
        }

    }

    @Override
    public void find(int idNews) throws ServiceException {
        try {

        } catch (Exception e) {
            throw new ServiceException(MSG_ERR, e);
        }
    }

    @Override
    public void delete(String idNews) throws ServiceException {
        try {
            newsDAO.deleteNews(idNews);
        } catch (DaoException e) {
            throw new ServiceException(MSG_ERR, e);
        }

    }

    @Override
    public List<Category> findAllCategoryes() throws ServiceException {

        try {
            return newsDAO.findAllCategory();

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(News news) throws ServiceException {

        try {
            newsDAO.update(news);
        } catch (DaoException e) {
            throw new ServiceException(e);

        }

    }

    @Override
    public List<News> latestList(int count) throws ServiceException {
        List<News> listNews;
        try {
            listNews = newsDAO.getAllNews().subList(0, count);
            return listNews;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> list() throws ServiceException {
        List<News> listNews;
        try {
            listNews = newsDAO.getAllNews();
            return listNews;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public News findById(String id) throws ServiceException {
        try {
            News news = newsDAO.getNews(id);
            return news;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> sortByCategory(String category) throws ServiceException {
        try {
            List<News> allNews = newsDAO.findByCategory(category);
            return allNews;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addLike(String newsId) throws ServiceException {
        try {
            newsDAO.writeLike(newsId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

}

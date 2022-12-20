package by.htp.ex.service.impl;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;

public class NewsServiceImpl implements INewsService{


    private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();

    @Override
    public void save() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void find() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<News> latestList(int count) throws ServiceException {
        List<News> listNews;

        listNews = newsDAO.getAllNews().subList(1, count);
        return listNews;
    }

    @Override
    public List<News> list() throws ServiceException {
        List<News> listNews;
        listNews = newsDAO.getAllNews();
        return listNews;
    }

    @Override
    public News findById(int id) throws ServiceException {
        // TODO Auto-generated method stub
        return null;
    }

}

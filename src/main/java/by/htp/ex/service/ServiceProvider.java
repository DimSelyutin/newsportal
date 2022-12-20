package by.htp.ex.service;

import by.htp.ex.service.impl.NewsServiceImpl;
import by.htp.ex.service.impl.UserServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    
    
    
    private final INewsService newsService = new NewsServiceImpl();
    private final IUserService userService = new UserServiceImpl();
    
    private ServiceProvider(){}
    
    public IUserService getUserService() {
        return userService;
    }
    
    
    public INewsService getNewsService() {
        return newsService;
    }
    
    public static ServiceProvider getInstance() {
        return instance;
    }
    
}

package by.htp.ex.controller;

import java.util.HashMap;
import java.util.Map;
import by.htp.ex.controller.impl.ChangeLocale;
import by.htp.ex.controller.impl.DoAddComment;
import by.htp.ex.controller.impl.DoAddLike;
import by.htp.ex.controller.impl.DoAddNews;
import by.htp.ex.controller.impl.DoAddTranslate;
import by.htp.ex.controller.impl.DoDeleteComment;
import by.htp.ex.controller.impl.DoDeleteNews;
import by.htp.ex.controller.impl.DoEditNews;
import by.htp.ex.controller.impl.DoRegister;
import by.htp.ex.controller.impl.DoSignIn;
import by.htp.ex.controller.impl.DoSignOut;
import by.htp.ex.controller.impl.GoTo404;
import by.htp.ex.controller.impl.GoToAddNews;
import by.htp.ex.controller.impl.GoToAddTranslate;
import by.htp.ex.controller.impl.GoToEditNews;
import by.htp.ex.controller.impl.GoToMainPage;
import by.htp.ex.controller.impl.GoToNews;
import by.htp.ex.controller.impl.GoToViewNews;
import by.htp.ex.service.ServiceException;

public class CommandProvider {

    private Map<CommandName, Command> commandProvider = new HashMap<>();

    public CommandProvider() {
        commandProvider.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
        commandProvider.put(CommandName.GO_TO_NEWS, new GoToNews());
        commandProvider.put(CommandName.GO_TO_VIEW_NEWS, new GoToViewNews());
        commandProvider.put(CommandName.GO_TO_EDIT_NEWS, new GoToEditNews());
        commandProvider.put(CommandName.GO_TO_ADD_NEWS, new GoToAddNews());
        commandProvider.put(CommandName.DO_REGISTER, new DoRegister());
        commandProvider.put(CommandName.DO_SIGNIN, new DoSignIn());
        commandProvider.put(CommandName.DO_SIGN_OUT, new DoSignOut());
        commandProvider.put(CommandName.DO_EDIT_NEWS, new DoEditNews());
        commandProvider.put(CommandName.DO_ADD_NEWS, new DoAddNews());
        commandProvider.put(CommandName.DO_DELETE_NEWS, new DoDeleteNews());
        commandProvider.put(CommandName.DO_DELETE_COMMENT, new DoDeleteComment());
        commandProvider.put(CommandName.DO_ADD_COMMENT, new DoAddComment());
        commandProvider.put(CommandName.GO_TO_404, new GoTo404());
        commandProvider.put(CommandName.CHANGE_LOCAL, new ChangeLocale());
        commandProvider.put(CommandName.DO_ADD_LIKE, new DoAddLike());
        commandProvider.put(CommandName.GO_TO_ADD_TRANSLATE, new GoToAddTranslate());
        commandProvider.put(CommandName.DO_ADD_TRANSLATE, new DoAddTranslate());
        


    }

    public Command getCommand(String commandInput) throws ServiceException {

        CommandName commandName;
        Command command = null;
        if (commandInput == null || commandInput.equals("")) {
            commandInput = CommandName.GO_TO_MAIN_PAGE.toString();
        }
        
        try {
            commandName = CommandName.valueOf(commandInput.toUpperCase()); 
        } catch (IllegalArgumentException e) {
            throw new ServiceException("Page not found!"); 
        }

        
        command = commandProvider.get(commandName); 
        return command;

    }

}

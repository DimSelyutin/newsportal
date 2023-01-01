package by.htp.ex.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.ex.controller.impl.DoAddNews;
import by.htp.ex.controller.impl.DoEditNews;
import by.htp.ex.controller.impl.DoRegister;
import by.htp.ex.controller.impl.DoSignIn;
import by.htp.ex.controller.impl.DoSignOut;
import by.htp.ex.controller.impl.GoToAddNews;
import by.htp.ex.controller.impl.GoToEditNews;
import by.htp.ex.controller.impl.GoToMainPage;
import by.htp.ex.controller.impl.GoToNews;
import by.htp.ex.controller.impl.GoToViewNews;

public class CommandProvider {

    private Map<CommandName, Command> commandProvider = new HashMap<>();

    public CommandProvider(){
        commandProvider.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPage());
        commandProvider.put(CommandName.GO_TO_NEWS, new GoToNews());
        commandProvider.put(CommandName.GO_TO_VIEW_NEWS, new GoToViewNews());
        commandProvider.put(CommandName.DO_REGISTER, new DoRegister());
        commandProvider.put(CommandName.DO_SIGNIN, new DoSignIn());
        commandProvider.put(CommandName.DO_SIGN_OUT, new DoSignOut());
        commandProvider.put(CommandName.GO_TO_EDIT_NEWS, new GoToEditNews());
        commandProvider.put(CommandName.DO_EDIT_NEWS, new DoEditNews());
        commandProvider.put(CommandName.DO_ADD_NEWS, new DoAddNews());
        commandProvider.put(CommandName.GO_TO_ADD_NEWS, new GoToAddNews());


    }

    public Command getCommand(String commandInput){
        CommandName commandName = CommandName.valueOf(commandInput.toUpperCase());                    //transform command from string in Enum
        Command command = commandProvider.get(commandName);                             //get linkpage instead enum(variable)
        return command;
    }

























   
    
}

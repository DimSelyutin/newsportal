package by.htp.ex.util.messageconst;

public enum MessageType {
    ACCESS("access"),
    EXCEPTION("exception"),
    LINK("link"),
    LOCAL("local"),
    BASELINK("/WEB-INF/pages/layouts/baselayout.jsp");

    private String messageType;

    
    private MessageType(String messageType){
        this.messageType = messageType;
    }

    public String getMessageType(){
        return messageType;
    }
}

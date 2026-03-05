package johnchatbot;

/**
 * Application specific error, represent user input errors or incorrect operations.
 */
public class JohnChatBotException extends Exception{
    /**
     * Constructs an exception specific to the application.
     * @param message a message of the error
     */
    public JohnChatBotException(String message){
        super(message);
    }
}

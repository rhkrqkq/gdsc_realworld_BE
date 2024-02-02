package gdsc.realworld.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("UserNotFound");
    }
}

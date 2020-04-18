package blog.storage;

import blog.exception.UserNotFoundException;
import blog.model.User;

public interface UserStorage {

    void add(User user);

    User getUserByEmailAndPassword(String email, String password) throws UserNotFoundException;

    void printAllUsers();

    boolean isEmpty();


}

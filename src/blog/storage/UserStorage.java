package blog.storage;

import blog.exception.DuplicateUserException;
import blog.exception.UserNotFoundException;
import blog.model.User;

public interface UserStorage {

    void add(User user) throws UserNotFoundException, DuplicateUserException;

    User getUserByEmailAndPassword(String email, String password) throws UserNotFoundException;

    User getByEmail(String email) throws UserNotFoundException;

    void printAllUsers();

    boolean isEmpty();


}

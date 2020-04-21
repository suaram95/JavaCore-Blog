package blog.storage.impl;

import blog.exception.DuplicateUserException;
import blog.exception.UserNotFoundException;
import blog.model.User;
import blog.storage.UserStorage;

public class UserStorageImpl implements UserStorage {

    private User[] users=new User[16];
    private int size=0;

    public void add(User user) throws DuplicateUserException {
       if (getByEmail(user.getEmail())!=null){
           throw new DuplicateUserException("User with email:"+"<"+user.getEmail()+">"+" already exists");
       }
        if (users.length==size){
            extend();
        }
        users[size++]=user;
    }

    private void extend() {
        User[] tmp=new User[users.length+10];
        System.arraycopy(users, 0, tmp,0,users.length);
        users=tmp;
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) throws UserNotFoundException {
        for (int i = 0; i < size; i++) {
            if (users[i].getEmail().equals(email)&&users[i].getPassword().equals(password)){
                return (users[i]);
            }
        }
        throw new UserNotFoundException(String.format("User with email: %s and" +
                                         " password: %s does not exist.",email,password));
    }

    @Override
    public User getByEmail(String email) {
        for (int i = 0; i < size; i++) {
            if (users[i].getEmail().equals(email)) {
                return users[i];
            }
        }
        return null;
    }


    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void printAllUsers() {
        for (int i = 0; i < size; i++) {
            System.out.println(users[i]);
        }

    }




}

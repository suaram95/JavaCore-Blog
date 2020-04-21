package blog;

import blog.exception.DuplicateUserException;
import blog.exception.PostNotFoundException;
import blog.exception.UserNotFoundException;
import blog.model.Category;
import blog.model.Gender;
import blog.model.Post;
import blog.model.User;
import blog.storage.impl.PostStorageImpl;
import blog.storage.impl.UserStorageImpl;

import java.util.Date;
import java.util.Scanner;

public class BlogMain implements Commands {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final PostStorageImpl POST_STORAGE = new PostStorageImpl();
    private static final UserStorageImpl USER_STORAGE = new UserStorageImpl();
    private static User loginedUser;

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            Commands.mainCommands();
            int command;
            try {
                command = Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case EXIT:
                    isRun = false;
                    System.out.println("Program was closed");
                    break;
                case REGISTER:
                    register();
                    break;
                case LOGIN:
                    login();
                    break;
                default:
                    System.err.println("Wrong Command!");
            }
        }
    }

    private static void register() {
        System.out.println("Input data for Registration /Name, Surname, Email, Password, Gender(MALE or FEMALE)/");
        String userStr = SCANNER.nextLine();
        String[] userData = userStr.split(",");
        try {
            User user = new User();
            user.setName(userData[0]);
            user.setSurname(userData[1]);
            user.setEmail(userData[2]);
            user.setPassword(userData[3]);
            user.setGender(Gender.valueOf(userData[4].toUpperCase()));
            USER_STORAGE.add(user);
            System.out.println("Thanks you are registered!");
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("Invalid data!");
            register();
        } catch (DuplicateUserException e){
            e.getMessage();
        }
    }

    private static void login() {
        if (USER_STORAGE.isEmpty()) {
            System.out.println("Please register first!");
            return;
        }
        System.out.println("Please enter email & password to LOGIN");
        try {
            System.out.print("Email: ");
            String userEmail = SCANNER.nextLine();
            System.out.print("Password: ");
            String userPassword = SCANNER.nextLine();
            loginedUser = USER_STORAGE.getUserByEmailAndPassword(userEmail, userPassword);
            System.out.println("You succesfully entered your profile");
            loginedUserCommands();
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void loginedUserCommands() {
        boolean isRun = true;
        while (isRun) {
            Commands.userCommands();
            int command;
            try {
                command = Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    isRun = false;
                    break;
                case ADD_POST:
                    addPost();
                    break;
                case SEARCH_POST:
                    searchPost();
                    break;
                case POSTS_BY_TITLE:
                    postByTitle();
                    break;
                case POSTS_BY_CATEGORY:
                    postsByCategory();
                    break;
                case ALL_POSTS:
                    POST_STORAGE.printAllPosts();
                    break;
                default:
                    System.err.println("Wrong Command!");
            }
        }
    }

    private static void addPost() {

        System.out.println("Input info about post /Title, Text, Category(IT, ART, MUSIC, SPORT, ECONOMICS, POLITICAL)/");
        String postStr = SCANNER.nextLine();
        String[] postData = postStr.split(",");
        try {
            Post post = new Post();
            post.setUser(loginedUser);
            post.setTitle(postData[0]);
            post.setText(postData[1]);
            post.setCategory(Category.valueOf(postData[2].toUpperCase()));
            post.setCreatedDate(new Date());
            POST_STORAGE.add(post);
            System.out.println("Thanks post was added!\n");
            System.out.println(post);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid data!");
            addPost();
        } catch (IllegalArgumentException e) {
            System.out.println("Category " + "<" + postData[2] + ">" + " does not exist, " +
                    "please write one of the above categories.");
            addPost();
        }
    }

    private static void searchPost() {
        if (POST_STORAGE.isEmpty()) {
            System.out.println("Post Storage is empty. Please add post first!");
            addPost();
        }
        System.out.println("Input keyword to search post");
        String keyword = SCANNER.nextLine();
        POST_STORAGE.searchPostsByKeyword(keyword);
    }

    private static void postByTitle() {
        if (POST_STORAGE.isEmpty()) {
            System.out.println("Post Storage is empty. Please add post first!");
            addPost();
        }
        System.out.println("Input title to search post");
        String title = SCANNER.nextLine();
        try {
            POST_STORAGE.getPostByTitle(title);
        } catch (PostNotFoundException e) {
            e.getMessage();
        }
    }

    private static void postsByCategory() {
        if (POST_STORAGE.isEmpty()) {
            System.out.println("Post Storage is empty. Please add post first!");
            addPost();
        }
        System.out.println("Input category name to find posts");
        String categoryStr = SCANNER.nextLine();
        Category category=Category.valueOf(categoryStr.toUpperCase());
        POST_STORAGE.printPostsByCategory(category);
    }
}

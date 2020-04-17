package blog;

import blog.exception.PostNotFoundException;
import blog.model.Post;
import blog.storage.impl.PostStorageImpl;

import java.util.Date;
import java.util.Scanner;

public class BlogMain implements Commands {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final PostStorageImpl POST_STORAGE = new PostStorageImpl();

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            printCommands();
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
                    System.out.println("Wrong Command!");
            }
        }

    }


    private static void printCommands() {
        Commands.printCommands();
    }

    private static void addPost() {
        System.out.println("Input info about post /Title, Text, Category/");
        String postStr = SCANNER.nextLine();
        String[] postData = postStr.split(",");
        try {
            Post post = new Post();
            post.setTitle(postData[0]);
            post.setText(postData[1]);
            post.setCategory(postData[2]);
            post.setCreatedDate(new Date());
            POST_STORAGE.add(post);
            System.out.println("Thanks post was added!");
            System.out.println(post);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid data");
            addPost();
        }
    }

    private static void searchPost() {
        System.out.println("Input keyword to search post");
        String keyword = SCANNER.nextLine();
        POST_STORAGE.searchPostsByKeyword(keyword);
    }

    private static void postByTitle() {
        System.out.println("Input title to search post");
        String title = SCANNER.nextLine();
        try {
            POST_STORAGE.getPostByTitle(title);
        } catch (PostNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Invalid title, please try again");
            postByTitle();
        }
    }

    private static void postsByCategory() {
        System.out.println("Input category name to find posts");
        String category = SCANNER.nextLine();
        POST_STORAGE.printPostsByCategory(category);
    }
}

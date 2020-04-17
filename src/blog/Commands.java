package blog;

public interface Commands {

    int EXIT = 0;
    int ADD_POST = 1;
    int SEARCH_POST = 2;
    int POSTS_BY_TITLE=3;
    int POSTS_BY_CATEGORY = 4;
    int ALL_POSTS = 5;

    static void printCommands() {
        System.out.println("Input " + EXIT + " to EXIT");
        System.out.println("Input " + ADD_POST + " to ADD POST");
        System.out.println("Input " + SEARCH_POST + " to SEARCH POST");
        System.out.println("Input " + POSTS_BY_TITLE + " to SEARCH POSTS BY TITLE");
        System.out.println("Input " + POSTS_BY_CATEGORY + " to SEE POSTS BY CATEGORY");
        System.out.println("Input " + ALL_POSTS + " to SEE ALL POSTS");
    }
}

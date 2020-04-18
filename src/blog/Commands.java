package blog;

public interface Commands {

    int EXIT = 0;
    int REGISTER = 1;
    int LOGIN = 2;
    int LOGOUT = 3;
    int ADD_POST = 4;
    int SEARCH_POST = 5;
    int POSTS_BY_TITLE = 6;
    int POSTS_BY_CATEGORY = 7;
    int ALL_POSTS = 8;

    static void mainCommands(){
        System.out.println("Input " + EXIT + " to EXIT");
        System.out.println("Input " + REGISTER + " to REGISTER");
        System.out.println("Input " + LOGIN + " to LOGIN");
    }

    static void userCommands(){
        System.out.println("Input " + LOGOUT + " to LOGOUT");
        System.out.println("Input " + ADD_POST + " to ADD POST");
        System.out.println("Input " + SEARCH_POST + " to SEARCH POST");
        System.out.println("Input " + POSTS_BY_TITLE + " to SEARCH POSTS BY TITLE");
        System.out.println("Input " + POSTS_BY_CATEGORY + " to SEE POSTS BY CATEGORY");
        System.out.println("Input " + ALL_POSTS + " to SEE ALL POSTS");
    }


}

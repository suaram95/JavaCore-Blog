package blog.storage;

import blog.exception.PostNotFoundException;
import blog.model.Post;

public interface PostStorage {

    void add(Post post);

    Post getPostByTitle(String title) throws PostNotFoundException;

    void searchPostsByKeyword(String keyword);

    void printPostsByCategory(String category);

    void printAllPosts();

    boolean isEmpty();
}

package blog.storage;

import blog.exception.PostNotFoundException;
import blog.model.Category;
import blog.model.Post;

public interface PostStorage {

    void add(Post post);

    Post getPostByTitle(String title) throws PostNotFoundException;

    void searchPostsByKeyword(String keyword);

    void printPostsByCategory(Category category);

    void printAllPosts();

    boolean isEmpty();
}

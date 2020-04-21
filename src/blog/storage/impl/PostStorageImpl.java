package blog.storage.impl;

import blog.exception.PostNotFoundException;
import blog.model.Category;
import blog.model.Post;
import blog.storage.PostStorage;

public class PostStorageImpl implements PostStorage {

    private Post[] posts = new Post[20];
    private int size = 0;

    @Override
    public void add(Post post) {
        if (posts.length == size) {
            extend();
        }
        posts[size++] = post;

    }

    private void extend() {
        Post[] tmp = new Post[posts.length + 10];
        System.arraycopy(posts, 0, tmp, 0, posts.length);
        posts = tmp;
    }

    @Override
    public Post getPostByTitle(String title) throws PostNotFoundException {
        for (int i = 0; i < size; i++) {
            if (posts[i].getTitle().equals(title)) {
                System.out.println(posts[i]);
            }
        }
        throw new PostNotFoundException(String.format("Post with title: %s does not exist", title));
    }

    @Override
    public void searchPostsByKeyword(String keyword) {
        for (int i = 0; i < size; i++) {
            Post post = posts[i];
            if (post.getText().equals(keyword) || post.getTitle().equals(keyword)) {
                System.out.println(post);
            } else {
                System.out.println("Keyword " + "<" + keyword + ">" + " was't found not in Posts title, not text");
            }
        }
    }

    @Override
    public void printPostsByCategory(Category category) {
        for (int i = 0; i < size; i++) {
            Post post = posts[0];
            if (post.getCategory().equals(category)) {
                System.out.println(post);
            } else {
                String format = String.format("Post with %s category doesn't exist", category);
            }
        }
    }

    @Override
    public void printAllPosts() {
        if (isEmpty()) {
            System.out.println("Post Storage is empty. Please add post first!");
        } else {
            for (int i = 0; i < size; i++) {
                System.out.println(posts[i]);
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


}

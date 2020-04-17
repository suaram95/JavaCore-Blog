package blog.storage.impl;

import blog.exception.PostNotFoundException;
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
            if (posts[i].getTitle().contains(title)) {
                return posts[i];
            }
        }
        throw new PostNotFoundException(String.format("Post with %s title does not exist", title));
    }

    @Override
    public void searchPostsByKeyword(String keyword) {
        for (int i = 0; i < size; i++) {
            Post post = posts[i];
            if (post.getTitle().contains(keyword) && post.getText().contains(keyword)) {
                System.out.println(post);
            } else {
                String format = String.format("Added %s keyword was't found not in Posts title, not text", keyword);
            }
        }
    }

    @Override
    public void printPostsByCategory(String category) {
        for (int i = 0; i < size; i++) {
            Post post = posts[i];
            if (post.getCategory().equals(category)) {
                System.out.println(post);
            } else {
                String format = String.format("Post with %s category doesn't exist", category);
            }
        }
    }

    @Override
    public void printAllPosts() {
        for (int i = 0; i < size; i++) {
            System.out.println(posts[i]);
        }
    }
}

package or.sopt.assignment.repository;

import or.sopt.assignment.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {

    List<Post> postList =  new ArrayList<>();

    public void save(Post post) {
        postList.add(post);
    }

    public List<Post> findAll() {
        return postList;
    }

    public Post findById(int id) {

        Post findPost = postList.stream()
                .filter(post -> post.getId() == id)
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("게시글을 못찾겠습니다"));

        return findPost;
    }

    public void deleteById(int id) {

        Post post1 = findById(id);

        try {
            postList.remove(post1);
        } catch (Exception e) {
            throw new IllegalArgumentException("삭제에 실패하였습니다");
        }
    }

    public void update(Post post, String updatedTitle) {
        post.update(updatedTitle);
    }

}

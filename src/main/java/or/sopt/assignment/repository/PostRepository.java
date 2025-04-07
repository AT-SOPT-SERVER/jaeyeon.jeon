package or.sopt.assignment.repository;

import or.sopt.assignment.domain.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PostRepository {

    List<Post> postList =  new ArrayList<>();

    public void save(Post post) {
        postList.add(post);
    }

    public List<Post> findAll() {
        return postList;
    }

    public Post findById(int id) {

        return postList.stream()
                .filter(post -> post.getId() == id)
                .findFirst()
                .orElseGet(() -> {
                    System.err.println("데이터를 찾을 수 없습니다");
                    return null;
                });
    }

    public Post findByTitle(String title) {
        return postList.stream()
                .filter(post -> Objects.equals(post.getTitle(), title))
                .findFirst()
                .orElseGet(() -> {
                    System.err.println("데이터를 찾을 수 없습니다");
                    return null;
                });
    }

    public boolean isValidate(String title) {
        Post result = postList.stream()
                .filter(post -> Objects.equals(post.getTitle(), title))
                .findFirst()
                .orElse(null);

        return result != null;
    }

    public void deleteById(int id) {

        Post post1 = findById(id);

        try {
            postList.remove(post1);
        } catch (Exception e) {
            System.err.println("게시글 삭제에 실패하였습니다");
        }
    }

    public void update(Post post, String updatedTitle) {
        try {
            post.update(updatedTitle);
        } catch (Exception e) {
            System.err.println("게시글 수정에 실패하였습니다");
        }
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postList.stream()
                .filter(post -> post.getTitle() != null && post.getTitle().contains(keyword))
                .toList();
    }
}

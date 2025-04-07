package or.sopt.assignment.domain;


public class Post {


    private int id;

    private String title;



    // Constructor
    public Post(int id, String title) {
        this.id = id;
        this.title = title;
    }


    // Helper Method
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void update(String title){
        this.title = title;
    }
}

package seven.team.entity;

public class RemarkDetails {
    private User remarker;
    private Comment comment;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getRemarker() {
        return remarker;
    }

    public void setRemarker(User remarker) {
        this.remarker = remarker;
    }
}

import java.util.ArrayList;

public class User {
    private static int ID = 0;

    private int userID;
    private User invitedBy;
    private ArrayList<User> invites;

    public User(User invitedBy) {
        this.userID = ++ID;
        this.invites = new ArrayList<User>();
        this.invitedBy = invitedBy;
    }

    // some necessary supporting functionalities

    public int getUserID() {
        return this.userID;
    }

    public void addChildUser(User user) {
        this.invites.add(user);
    }

    // getters

    public ArrayList<User> getInvites() {
        return this.invites;
    }

    public User getInvitedBy() {
        return this.invitedBy;
    }
}

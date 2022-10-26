import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ArrayList<User> parentsUsers = new ArrayList<User>();
        ArrayList<User> childrenUsers = new ArrayList<User>();

        int min, max, time;

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Minimum invites: ");
        min = scanner.nextInt();

        System.out.print("Maximum invites: ");
        max = scanner.nextInt();

        System.out.print("Number of days: ");
        time = scanner.nextInt();

        // initiate the first user on day 1, also the root of the tree
        User firstUser = new User(null);
        parentsUsers.add(firstUser);
        System.out.println("Day 1: #1 created");

        // the rest of the days, creating other users as nodes of the tree
        // now working with a set of different parent users for each different day
        for (int i = 2; i <= time; i++) {

            // now working with each parent user on that day
            for (User user : parentsUsers) {

                // print out the info of each parent user on each day
                int numberOfInvites = random.nextInt(max - min + 1) + min;
                System.out.println(
                        "Day " + i + ": #" + user.getUserID()
                                + " is inviting " + numberOfInvites + " friends");

                // create new invited children users for each parent user
                for (int j = 0; j < numberOfInvites; j++) {
                    user.addChildUser(new User(user));
                }

                // add all newly created children users into a temp list
                // and print out each new children user just created
                for (User childUser : user.getInvites()) {
                    childrenUsers.add(childUser);

                    System.out.println("Day " + i + ": #" + childUser.getUserID()
                            + " created, which was invited by #"
                            + childUser.getInvitedBy().getUserID());
                }
            }

            // make all children users become parent users for the next day
            parentsUsers.clear();
            for (User user : childrenUsers) {
                parentsUsers.add(user);
            }
            childrenUsers.clear();
        }

        scanner.close();
    }
}
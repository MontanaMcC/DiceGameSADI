
import java.util.Scanner;
        import java.util.ArrayList;
        import java.util.Arrays;



public class PostJob {
    public static Scanner object = new Scanner(System.in);
    public static ArrayList<String> allJobs = new ArrayList<String>();


    public static void postJob() {
    	 String title;
    	 String time;
    	 String location;
    	 String prerequisites;
    	 String currentJob;
        do {
            System.out.println("Enter the title of your job: ");
            title=object.nextLine();
            if (title.length() == 0)
                System.out.println("Invalid title, please try again");
        } while(title.length() == 0);

        do {
            System.out.println("Enter time of your job: ");
            time=object.nextLine();
            if (time.length() == 0)
                System.out.println("Invalid time, please try again");
        } while(time.length() == 0);

        do {
            System.out.println("Enter the location of the job: ");
            location=object.nextLine();
            if (location.length() == 0)
                System.out.println("Invalid location, please try again");
        } while(location.length() == 0);

        do {
            System.out.println("Enter the prerequisites of your job (Put 'none' if there is none): ");
            prerequisites=object.nextLine();
            if (prerequisites.length() == 0)
                System.out.println("Invalid prerequisites, please try again");
        } while (prerequisites.length() == 0);


        System.out.println("Title: " + title);
        System.out.println("Time: " + time);
        System.out.println("Location: " + location);
        System.out.println("Prerequisites: " + prerequisites);
        currentJob= title +","+time + ","+location +","+prerequisites;
        allJobs.add(currentJob);

    }
}




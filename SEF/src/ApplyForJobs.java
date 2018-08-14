import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
public class ApplyForJobs {


    public static void searchJobs(String searchTerm) {
        //Casuals can search for jobs using a search Term

        for (int i = 0; i > PostJob.allJobs.size(); i++) {


            if (PostJob.allJobs.contains(searchTerm)) {
                int itemIndx = PostJob.allJobs.indexOf(searchTerm);
                String output = PostJob.allJobs.get(itemIndx);
                System.out.print("Available jobs within your search terms" + output);

            }
        }
    }


        public static void applyForJobs (String searchTerm) {
            ArrayList<String> allApplcations = new ArrayList<String>();
            Scanner object = new Scanner(System.in);
            String name = "";
            String dob = "";
            String email = "";
            //Casuals can apply for job by attaching their resume
            do {
                System.out.println("Enter your full name ");
                name = object.nextLine();
                if (name.length() == 0)
                    System.out.println("Invalid name, please try again");
            } while (name.length() == 0);

            do {
                System.out.println("Enter your date of birth  ");
                dob = object.nextLine();
                if (dob.length() == 0)
                    System.out.println("Invalid DOB, please try again");
            } while (dob.length() == 0);

            do {
                System.out.println("Enter your email address ");
                email = object.nextLine();
                if (email.length() == 0)
                    System.out.println("Invalid location, please try again");
            } while (email.length() == 0);
            String application = name + dob + email;
            allApplcations.add(application);

            Scanner keyboard = new Scanner(System.in);
            System.out.print("Enter the filename of your resume: ");
            String filename = keyboard.nextLine();
            File file = new File(filename);
            // Close the file.
            keyboard.close();

            System.out.println("Thank you for applying, you'll hear back from us through email");
        }
    }

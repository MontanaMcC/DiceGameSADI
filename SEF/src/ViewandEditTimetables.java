import java.util.Scanner;
public class ViewandEditTimetables {
    public static String searchTerm="";
    public static String profileLink= Profiles.searchProfiles(searchTerm);
    public static Scanner object = new Scanner(System.in);


    public static void viewTimetables(String profileLink) {
        //course coords have the ability to view casuals timetables and their own
        //method call from timetables to view their timetable.
        Timetables.viewTimetable(profileLink);
    }

    public static void editTimetables(String profileLink) {
        //course coords have the ability to edit  casuals and their own timetable

        do {
            System.out.println("Enter a class name ");
            Timetables.className=object.nextLine();
            if (Tiemtables.className.length() == 0)
                System.out.println("Invalid class name, please try again");
        } while(Timetables.className.length() == 0);

        do {
            System.out.println("Enter time of your class ");
            Timetables.classTime=object.nextLine();
            if (Timetables.classTime.length() == 0)
                System.out.println("Invalid time, please try again");
        } while(Timetables.classTime.length() == 0);

        do {
            System.out.println("Enter timeslots for your class: ");
            Timetables.timeSlots=object.nextInt();
            if (Timetables.timeSlots == 0)
                System.out.println("Invalid time slot, please try again");
        } while(Timetables.timeSlots == 0);

    }

}

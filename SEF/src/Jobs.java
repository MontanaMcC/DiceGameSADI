import java.util.Scanner;

public class Jobs {
	public static String title="";
	public static String time="";
	public static String location="";
	public static String prerequisites="";
	public static Scanner object = new Scanner(System.in);
	
	public static void jobDescription() {
		// this method is called to display all parts of a job description
		System.out.println(title);
		System.out.println(time);
		System.out.println(location);
		System.out.println(prerequisites);
	}
	
	public static void postJob() {
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
}

	
	
}

	
	

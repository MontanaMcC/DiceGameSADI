import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.*;

public class PostJobTest {
	private ArrayList<String> testJobs;
	private Scanner object;
	
	@Before
	public void ArrayListInit() {
		object = new Scanner(System.in);
		testJobs = new ArrayList<String>();
	}
	
	@Test
	public void testPostJob() {
		String title;
   	 	String time;
   	 	String location;
   	 	String prerequisites;
   	 	String currentJob;
   	 	
   	 	assertTrue(testJobs.isEmpty());
   	 	
  
   	 	System.out.println("Enter the title of your job: ");
   	 	title=object.nextLine();
   	 	assertTrue(title.length() > 0);
   	 

   	 
   	 	System.out.println("Enter time of your job: ");
   	 	time=object.nextLine();
   	 	assertTrue(time.length() > 0);
   	 	

   	 
   	 	System.out.println("Enter the location of the job: ");
   	 	location=object.nextLine();
   	 	assertTrue(location.length() > 0);
   

   	 	
   	 	System.out.println("Enter the prerequisites of your job (Put 'none' if there is none): ");
   	 	prerequisites=object.nextLine();
   		assertTrue(prerequisites.length() > 0);
   	 	


   	 	System.out.println("Title: " + title);
   	 	System.out.println("Time: " + time);
   	 	System.out.println("Location: " + location);
   	 	System.out.println("Prerequisites: " + prerequisites);
   	 	currentJob= title +","+time + ","+location +","+prerequisites;
   	 	testJobs.add(currentJob);
   	 	
   	 	assertFalse(testJobs.isEmpty());
	}

}

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class UsersTest {
	public Scanner object = new Scanner(System.in);
	List<List<String>> profiles = new ArrayList<List<String>>();

	@Before
	public void profileInit() {
		ArrayList<String> profile = new ArrayList<String>();
		profile.add("e123456");
		profile.add("jonhsmith@gmail.com");
		profile.add("John Smith");
		profile.add("123 Flinders Street");
		profile.add("Melbourne");
		profile.add("Course Coordinator");
		profile.add("0400123123");
		
		testStoreProfile(profile);
	}
	
	@Test
	public void testCreateAccount() {
		ArrayList<String> profile = new ArrayList<String>();
		String eNumber;
		String contactEmail;
		
		assertTrue(profile.isEmpty());
		
		System.out.println("Please enter your eNumber");
		eNumber=object.nextLine();
		assertTrue(eNumber.length() > 0);
		System.out.println("Please enter your contact email");
		contactEmail=object.nextLine();
		assertTrue(contactEmail.length() > 0);
		System.out.println("Your password will be sent to your email: "+contactEmail);
		
		profile.add(eNumber);
		profile.add(contactEmail);
		
		assertFalse(profile.isEmpty());
		assertEquals(profile.get(0), eNumber);
		assertEquals(profile.get(1), contactEmail);
		assertNull(profile.get(2));
	}

	@Test
	public void testViewProfile(ArrayList<String> profile) {
		assertFalse(profile.isEmpty());
		
		System.out.println("eNumber" + profile.get(0));
		System.out.println("Name: " + profile.get(1));
		System.out.println("contactEmail: " + profile.get(2));
		System.out.println("phoneNumber: " + profile.get(3));
		System.out.println("address: " + profile.get(4));
		System.out.println("suburb: " + profile.get(5));
		System.out.println("position: " + profile.get(6));
	}

	@Test
	public void testEditProfile(ArrayList<String> profile) {
		assertFalse(profile.isEmpty());
		
		System.out.println("Enter your contact email: ");
		profile.add(1 , object.nextLine());
		assertTrue(profile.get(1).length() > 0);
	
		System.out.println("Enter your name: ");
		profile.add(2, object.nextLine());
		assertTrue(profile.get(2).length() > 0);
		
		System.out.println("Enter your address: ");
		profile.add(object.nextLine());
		assertTrue(profile.get(3).length() > 0);

			
		
		System.out.println("Enter your suburb: ");
		profile.add(object.nextLine());
		assertTrue(profile.get(4).length() > 0);
		
			
		
		System.out.println("Enter your staff position: ");
		profile.add(object.nextLine());
		assertTrue(profile.get(5).length() > 0);
			
			
		
		System.out.println("Enter your phone number: ");
		profile.add(object.nextLine());
		assertTrue(profile.get(6).length() > 0);
			
		System.out.println("eNumber" + profile.get(0));
		System.out.println("contactEmail: " + profile.get(1));
		System.out.println("Name: " + profile.get(2));
		System.out.println("address: " + profile.get(3));
		System.out.println("suburb: " + profile.get(4));
		System.out.println("position: " + profile.get(5));
		System.out.println("phoneNumber: " + profile.get(6));
		System.out.println("Changes are made sucessfully");

		testStoreProfile(profile);
	}

	@Test
	public void testStoreProfile(ArrayList<String> profile) {
		profiles.add(profile);
		
		assertFalse(profiles.isEmpty());
	}

}

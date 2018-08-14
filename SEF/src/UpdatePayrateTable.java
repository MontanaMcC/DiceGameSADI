import java.util.Arrays;
import java.util.Scanner;

public class UpdatePayrateTable {

	public static String course="";
	public static String eNumber="";
	public static float payrate;

	public static Scanner object = new Scanner(System.in);
	
	private static String[] casualStaff = {"e1111111","e2222222","e3333333","e4444444","e5555555"};
	//created e numbers within an array for testing

		public static void updatePayrate() {

			System.out.println("Enter the e number of who you wish to edit payrate: ");
			eNumber = object.nextLine();

			System.out.println(casualStaff[Arrays.binarySearch(casualStaff, eNumber)]);
	
		}
}


public class Applications {

	public static void viewApplication() {
		//method to view casual staff's application
	}
	
	public static boolean approveApplication() {
		System.out.println(CasualStaff.resume);
		//if admin decides, he can either accept or deny application. Program will return true for accept or false for deny
		return true;
	}
}

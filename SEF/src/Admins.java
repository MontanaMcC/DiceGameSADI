
public class Admins extends Accounts{
	public static String searchTerm="";
	public static String profileLink= Profiles.searchProfiles(searchTerm);
	public static String position="";
	
	public static boolean approveCourseCoordinatorsTimetable(String profileLink) {
		//if Administrators approve course coordinators timetable, return true else return false.
		return true;
	}
	public static void categorizeAccounts(String position) {
		//Administrators will keep a spreadsheet with all the staff's position and hence their permisisons will be categorized accordingly.
	}
	
	public static boolean approveJob(){
		//Administrators will be asked to approve or reject course coordinators request for a job posting, return true else return false.
		return false;
	}
	
	public static void assignApprover(){
		System.out.print("Enter profile link of who you would like to set as an approver: ");
		profileLink=object.nextLine();
		
	}
	
}

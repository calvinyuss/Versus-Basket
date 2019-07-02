package Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import lib.Account;
import lib.Lib;
import lib.Schedule;

public class Main {
	public static Map<String,String[][]> schedule_data = new HashMap<String,String[][]>();
	public static ArrayList<String[]>schedule_history_temp = new ArrayList<String[]>();
	public static ArrayList<String[]>schedule_history_notif = new ArrayList<String[]>();
	public static ArrayList<String[]>schedule_history = new ArrayList<String[]>();
	public static Map<String,String[][]> account_datas = new HashMap<String,String[][]>();

	public static void main(String[] args) {			
		Scanner scan = new Scanner(System.in);
		Account user = new Account();
		
		user.add_account("username","password","team_name","leader_name",new String[] {"abc","cde"},"contact");
		user.add_account("username1","password1","team_name1","leader_name1",new String[] {"abc1","cde1"},"contact1");
		user.add_account("username2","password2","team_name2","leader_name2",new String[] {"abc2","cde2"},"contact2");
		
		while(true) {
			user.login();
			Schedule schedule = new Schedule(user);
			lable:
			while(true) {
				Lib.clscr();
				user.refreshData();			
				schedule.ShowNotif(user.get_userdata()[1][0]);
				Lib.clscr();
				System.out.println("1. Create match");
				System.out.println("2. Schedule");
				System.out.println("3. Profile");
				System.out.println("4. my match");
				System.out.println("0. log out");
				System.out.print("choice : ");
				int userInput = scan.nextInt();
				scan.nextLine();
				switch(userInput) {
				case 1:
					schedule.create();
					break;
				case 2:
					schedule.showAllSchedule();
					break;
				case 3:
					user.show_profile();
					break;
				case 4:
					schedule.showHistory();
				case 5:
					schedule.cancleMatch(user.get_userdata()[1][0]);
				case 0:
					break lable;
				default:
					System.out.println("Input 0 - 4");
					Lib.pressAnyKeyToContinue();
				}
			}
		}
	}
}

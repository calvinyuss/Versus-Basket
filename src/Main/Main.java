package Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
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
			Lib.clscr();
			System.out.println("1. Create account");
			System.out.println("2. Login");
			System.out.print("Input : ");
			int userInput = scan.nextInt();
			if(userInput == 1) {
				user.CreateAccount();
			}else if(userInput == 2) {
				lable:
				while(true) {
					if(user.login()==0) {
						break;
					}else {
						Schedule schedule = new Schedule(user);
						while(true) {
							Lib.clscr();
							user.refreshData();			
							schedule.ShowNotif(user.get_userdata()[1][0]);
							Lib.clscr();
							System.out.println("1. Create match");
							System.out.println("2. Schedule");
							System.out.println("3. Profile");
							System.out.println("4. my match");
							System.out.println("5. delete match");
							System.out.println("0. log out");
							System.out.print("choice : ");
							try {
								//error handling infinite loop still error
								int userInput1 = scan.nextInt();
								switch(userInput1) {
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
									break;
								case 5:
									schedule.cancleMatch(user.get_userdata()[1][0]);
									break;
								case 0:
									break lable;
								default:
									System.out.println("Input 0 - 4");
									Lib.pressAnyKeyToContinue();
								}
							}catch(InputMismatchException e) {
								System.out.println("Wrong Input");
							}
						}
					}
				}
			}
		}
	}
}

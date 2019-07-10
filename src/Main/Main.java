package Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import lib.Account;
import lib.Lib;
import lib.Schedule;

public class Main {
	public static Map<String,String[][]> schedule_data = new HashMap<String,String[][]>();
	public static ArrayList<String[]>schedule_history_temp = new ArrayList<String[]>();
	public static ArrayList<String[]>schedule_history_notif = new ArrayList<String[]>();
//	public static ArrayList<String[]>schedule_history = new ArrayList<String[]>();
	public static Map<String,String[][]> account_datas = new HashMap<String,String[][]>();

	public static void main(String[] args) {			
		Scanner scan = new Scanner(System.in);
		Account user = new Account();
		
		user.add_account("username","password","Subur","Evan Subur",new String[] {"Evan","Subur","Cy","jebby","mimin"},"08123456789");
		user.add_account("username1","password1","Galaxy","Ericks",new String[] {"Suliegna","Ferdy","Mantao","Bakpao","Roti Pia"},"0235479541");
		user.add_account("username2","password2","Notrick","Edwin",new String[] {"Irvin","Salted","Egg","Amoy","Dadar"},"02813654687");
		
		while(true) {
			Lib.clscr();
			System.out.println("1. Create account");
			System.out.println("2. Login");
			System.out.print("Input : ");
			try {
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
								System.out.println("4. My match");
								System.out.println("5. Delete match");
								System.out.println("0. Log out");
								System.out.print("Choice : ");
								try {
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
									System.out.println("Input 0 - 4");
									scan.next();
									Lib.pressAnyKeyToContinue();
								}
							}
						}
					}
				}else {
					System.out.println("Input 1 - 2");
					Lib.pressAnyKeyToContinue();
				}
			}catch(InputMismatchException e) {
				System.out.println("Input 1 - 2");
				scan.next();
				Lib.pressAnyKeyToContinue();
			}
			
		}
	}
}

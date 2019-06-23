import java.util.Scanner;

import lib.Account;
import lib.Lib;

public class Main {
	

	public static void main(String[] args) {			
		Scanner scan = new Scanner(System.in);
		Account user = new Account();
		user.add_account("username","password","team_name","leader_name",new String[] {"abc","cde"},"contact");
		user.add_account("username1","password1","team_name1","leader_name1",new String[] {"abc1","cde1"},"contact1");
		user.add_account("username2","password2","team_name2","leader_name2",new String[] {"abc2","cde2"},"contact2");
		
		lable:
		while(true) {
			Lib.clscr();
			user.login();
			
			lable1:
			while(true) {
				Lib.clscr();
				System.out.println("1. Create match");
				System.out.println("2. Schedule");
				System.out.println("3. Profile");
				System.out.println("4. log out");
				System.out.println("choice : ");
				int userInput = scan.nextInt();
				switch(userInput) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					user.show_profile();
					break lable1;
				case 4:
					break lable;
				default:
					System.out.println("Input 1 - 3");
					Lib.pressAnyKeyToContinue();
				}
			}
		}
	}
}

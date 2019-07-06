package lib;
import java.util.*;

import Main.Main;
public class Account extends Main{
	Scanner scan = new Scanner(System.in);
	private String username;
	private String password;
	private String team_name;
	private String leader_name;
	private String player_name[];
	private String contact;
	
	public int set_userdata(String username) {
		String[][] account_data = account_datas.get(username);
		if(account_data == null) {
			return 0;
		}else {
			this.username = username;
			this.password = account_data[0][0];
			this.team_name = account_data[1][0];
			this.leader_name = account_data[2][0];
			this.player_name = account_data[3];
			this.contact = account_data[4][0];
		}
		return 1;
	}
	
	public void CreateAccount() {
		boolean flag = true;
		System.out.print("Username : ");
		String username = scan.next();
		System.out.print("\npassword : ");
		String password = scan.next();
		scan.nextLine();
		System.out.print("\nTeam name : ");
		String teamName = scan.nextLine();
		System.out.print("\nLeader name : ");
		String leaderName = scan.nextLine();
		String[] player = new String[7];
		for (int i=1;i<=7;i++) {
			System.out.print("\nPlayer "+i+": ");
			player[i-1] = scan.nextLine();
		}
		System.out.print("\nContact ");
		String contact = scan.next();
		
		for(String[][] str : account_datas.values()) {
			if(str[2][0]==teamName) {
				System.out.println("Team name already exist");
				flag = false;
				break;
			}else if (account_datas.containsKey(username)){
				System.out.println("Username already exist");
				flag = false;
				break;
			}
		}
		if(flag) {
			add_account(username, password, teamName, leaderName, player, contact);
		}
	}
	
	public void refreshData() {
		String[][] account_data = account_datas.get(this.username);
		this.password = account_data[0][0];
		this.team_name = account_data[1][0];
		this.leader_name = account_data[2][0];
		this.player_name = account_data[3];
		this.contact = account_data[4][0];
	}
	
	public String[][] get_userdata() {
		return account_datas.get(this.username);
	}
	
	public int login() {
			Lib.clscr();
			System.out.print("Username : ");
			String input_username = scan.next();
			System.out.print("Password : ");
			String input_password = scan.next();
			if(set_userdata(input_username)==0) {
				System.out.println("Username or Password incorrect");
				Lib.pressAnyKeyToContinue();
				return 0;
			}else if (input_password.contentEquals(this.password)) {
				return set_userdata(username);
			}else{
				System.out.println("Username or Password incorrect");
				Lib.pressAnyKeyToContinue();
				return 0;
			}
	}
	
	public void show_profile(){
		Lib.clscr();
		System.out.printf("%-15s %s %n","Username",this.username);
		System.out.printf("%-15s %s %n","Team Name",this.team_name);
		System.out.printf("%-15s %s %n","Leader Name",this.leader_name);
		System.out.printf("%-15s ","Player Name");
		for(String i : this.player_name) {
			System.out.printf("|%2s| ",i);
		}
		System.out.printf("%n%-15s %s %n","Contact",this.contact);
		System.out.println("\n1. change account");
		System.out.println("2. Exit");
		int userInput = scan.nextInt();
		try {			
			switch(userInput) {
			case 1:
				change_account();
				break;
			case 2:
				break;
			default:
				System.out.println("Input 1 - 2");
				Lib.pressAnyKeyToContinue();
			}
		}catch(Exception e) {
			System.out.println("Input 1 - 2");
			Lib.pressAnyKeyToContinue();
		}
	}
	
	public void add_account(String username, String password, String team_name, String leader_name, String[] player_name,String contact){
		account_datas.put(username,new String[][] {{password},{team_name},{leader_name},player_name,{contact},{"0"}});
	}
	
	public void change_account() {
		lable:
		while(true) {
			Lib.clscr();
			System.out.println("1. Change password");
			System.out.println("2. Leader name");
			System.out.println("3. Player name");
			System.out.println("4. Contact");
			System.out.println("5. Exit");
			System.out.print("Your choice : ");
			int user_input = scan.nextInt();
			scan.nextLine();
			try {
				switch(user_input){
				case 1 :
					Lib.clscr();
					System.out.println("Change password");
					System.out.print("Current password : ");
					if(scan.next().contentEquals(this.password)) {
						System.out.print("\nPassword : ");
						String inputNewPassword = scan.next();
						account_datas.get(this.username)[0][0] = inputNewPassword;
					}else {
						System.out.print("wrong password");
						Lib.pressAnyKeyToContinue();
						break lable;
					}
					break;
				case 2 :
					Lib.clscr();
					System.out.println("Change Leader name");
					System.out.print("New leader name :");
					String newLeader = scan.next();
					System.out.print("\nCurrent password : ");
					if(scan.next().contentEquals(this.password)) {
						account_datas.get(this.username)[2][0] = newLeader;
					}else {
						System.out.print("wrong password");
						Lib.pressAnyKeyToContinue();
						break lable;
					}
					break;
				case 3 :
					Lib.clscr();
					System.out.println("Change team");
					int count=1;
					for(String i : account_datas.get(this.username)[3]) {
						System.out.printf("\n%d. %s",count,i);
						count++;
					}
					System.out.printf("\n%d Exit",count);
					System.out.print("\nChoice : ");
					int userInput = scan.nextInt();
					if(userInput > 0 || userInput<account_datas.get(this.username)[3].length) {
						System.out.print("New name : ");
						String newName = scan.next();
						System.out.print("\nCurrent password : ");
						if(scan.next().contentEquals(this.password)) {
							account_datas.get(this.username)[3][userInput] = newName;
						}else {
							System.out.print("wrong password");
							Lib.pressAnyKeyToContinue();
							break lable;
						}
					}else {
						System.out.println("Input 1 - "+count);
					}
					break;
				case 4 :
					Lib.clscr();
					System.out.println("Change contact");
					System.out.print("Contact : ");
					String newContact = scan.next();
					System.out.print("\nCurrent password : ");
					if(scan.next().contentEquals(this.password)) {
						account_datas.get(this.username)[4][0] = newContact;
					}else {
						System.out.print("wrong password");
						Lib.pressAnyKeyToContinue();
						break lable;
					}
					break;
				case 5:
					break;
				default : 
					System.out.println("Input 1 - 4");
					break;
				}
			}
			catch(Exception e){
				System.out.println("Wrong input");
				Lib.pressAnyKeyToContinue();
				break lable;
			}
		break;
		}
	}
	
}

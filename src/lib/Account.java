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
		try {
			boolean flag = true;
			System.out.print("Username : ");
			String username = scan.next();
			System.out.print("\npassword : ");
			String password = scan.nextLine();
			scan.nextLine();
			System.out.print("\nTeam name : ");
			String teamName = scan.nextLine();
			System.out.print("\nLeader name : ");
			String leaderName = scan.nextLine();
			String[] player = new String[2]; //length sebanyak basket player
			for (int i=1;i<=2;i++) { //diatas kalo ganti jangan lupa yang ini
				System.out.print("\nPlayer "+i+" : ");
				player[i-1] = scan.nextLine();
			}
			System.out.print("\nContact : ");
			String contact = scan.next();
			long check = Long.parseLong(contact);
			scan.nextLine();
			for(String[][] str : account_datas.values()) {
				if(str[1][0]==teamName) {
					System.out.println("Team name already exist");
					Lib.pressAnyKeyToContinue();
					flag = false;
					break;
				}else if (account_datas.containsKey(username)){
					System.out.println("Username already exist");
					Lib.pressAnyKeyToContinue();
					flag = false;
					break;
				}
			}
			if(flag) {
				System.out.println("Account Created");
				add_account(username, password, teamName, leaderName, player, contact);
				Lib.pressAnyKeyToContinue();
			}
		}catch(InputMismatchException e) {
			System.out.println("Invalid Input Integer Only mismatch");
			scan.next();
			Lib.pressAnyKeyToContinue();
		}catch(NumberFormatException e) {
			System.out.println("Invalid Input Integer Only");
			Lib.pressAnyKeyToContinue();
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
		lable:
		while(true) {
			refreshData();
			Lib.clscr();
			System.out.printf("%-15s : %s %n","Username",this.username);
			System.out.printf("%-15s : %s %n","Team Name",this.team_name);
			System.out.printf("%-15s : %s %n","Leader Name",this.leader_name);
			System.out.printf("%-15s : ","Player Name");
			for(String i : this.player_name) {
				System.out.printf("|%2s| ",i);
			}
			System.out.printf("%n%-15s : %s %n","Contact",this.contact);
			System.out.println("\n1. change account");
			System.out.println("2. Exit");
			try {			
				System.out.print("Choice : ");
				int userInput = scan.nextInt();
				switch(userInput) {
				case 1:
					change_account();
					break;
				case 2:
					break lable;
				default:
					System.out.println("Input 1 - 2");
					Lib.pressAnyKeyToContinue();
				}
			}catch(Exception e) {
				System.out.println("Input 1 - 2");
				scan.next();
				Lib.pressAnyKeyToContinue();
			}
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
			try {
				int user_input = scan.nextInt();
				scan.nextLine();
				switch(user_input){
				case 1 :
					Lib.clscr();
					System.out.println("Change password");
					System.out.print("Current password : ");
					if(scan.nextLine().contentEquals(this.password)) {
						System.out.print("\nPassword : ");
						String inputNewPassword = scan.nextLine();
						account_datas.get(this.username)[0][0] = inputNewPassword;
						break lable;
					}else {
						System.out.println("wrong password");
						Lib.pressAnyKeyToContinue();
						break;
					}
				case 2 :
					Lib.clscr();
					System.out.println("Change Leader name");
					System.out.print("New leader name : ");
					String newLeader = scan.nextLine();
					System.out.print("\nCurrent password : ");
					if(scan.nextLine().contentEquals(this.password)) {
						account_datas.get(this.username)[2][0] = newLeader;
						break lable;
					}else {
						System.out.println("wrong password");
						Lib.pressAnyKeyToContinue();
						break;
					}
				case 3 :
					Lib.clscr();
					System.out.println("Change team");
					int count=1;
					for(String i : account_datas.get(this.username)[3]) {
						System.out.printf("\n%d. %s",count,i);
						count++;
					}
					System.out.printf("\n%d. Exit",count);
					System.out.print("\nChoice : ");
					int userInput = scan.nextInt();
					if(userInput == count) {
						break;
					}else if(userInput > 0  && userInput<account_datas.get(this.username)[3].length) {
						System.out.print("New name : ");
						String newName = scan.nextLine();
						System.out.print("\nCurrent password : ");
						if(scan.nextLine().contentEquals(this.password)) {
							account_datas.get(this.username)[3][userInput] = newName;
							break lable;
						}else {
							System.out.println("wrong password");
							Lib.pressAnyKeyToContinue();
							break;
						}
					}else {
						System.out.println("Input 1 - "+count);
					}
				case 4 :
					Lib.clscr();
					System.out.println("Change contact");
					System.out.print("Contact : ");
					try {
						String newContact = scan.next();
						long check = Long.parseLong(newContact);
						System.out.print("\nCurrent password : ");
						if(scan.nextLine().contentEquals(this.password)) {
							account_datas.get(this.username)[4][0] = newContact;
							break lable;
						}else {
							System.out.println("wrong password");
							Lib.pressAnyKeyToContinue();
							break;
						}
					}catch(NumberFormatException e){
						System.out.println("Invalid Input Integer Only");
						Lib.pressAnyKeyToContinue();
						break;
					}
				case 5:
					break lable;
				default : 
					System.out.println("Input 1 - 5");
					break;
				}
			}
			catch(InputMismatchException e){
				System.out.println("Invalid input Integer only");
				Lib.pressAnyKeyToContinue();
				break;
			}
		}
	}
	
}

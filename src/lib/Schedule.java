package lib;
import java.util.*;
import java.util.Map.Entry;

import Main.Main;
public class Schedule extends Main{
	private String location;
	private String date;
	private String time;
	private String note;
	private String[][] user;
//	public static Map<String,String[][]> schedule_data = new HashMap<String,String[][]>();
//	public static ArrayList<String[]>schedule_history_temp = new ArrayList<String[]>();
//	public static ArrayList<String[]>schedule_history = new ArrayList<String[]>();
	Scanner scan = new Scanner(System.in);
	
	public Schedule(Account userdata){
		this.user = userdata.get_userdata();
	}
	
	public void create() {
		Lib.clscr();
		boolean flag = false;
		for(String[] str : schedule_history_temp) {
			if(str[0].contentEquals(this.user[1][0]) || str[1].contentEquals(this.user[1][0])) {
				flag = true;
				break;
			}
		}
		if(schedule_data.containsKey(this.user[1][0])){
			System.out.println("You can only create 1 schedule");
			Lib.pressAnyKeyToContinue();
		}else if(flag){
			System.out.println("You can only had 1 match");
			Lib.pressAnyKeyToContinue();
		}else {
			System.out.println("Create your schedule");
			System.out.print("\nLocation : ");
			location = scan.nextLine();
			System.out.print("\nDate (dd/mm/yyyy) : ");
			date = scan.next();
			System.out.print("\nTime (hh:mm:ss) : ");
			time = scan.next();
			scan.nextLine();
			System.out.print("\nnote : ");
			note = scan.nextLine();
			addSchedule(location,date,time,note);
			Lib.pressAnyKeyToContinue();
		}
	}
	
	public void addSchedule(String location, String date, String time, String note) {
		String [][] temp = {this.user[1],this.user[2],this.user[3],this.user[4],{location},{date},{time},{note}};
		schedule_data.put(this.user[1][0],temp);
	}
	
	public void removeSchedule(String team_name) {
		schedule_data.remove(team_name);
	}
	
	public void addNotif(String team_name1,String team_name2,String location1,String date1,String time1,String note1,String contact1,String contact2) {
		String [] a = {team_name1,team_name2,location1,date1,time1,note1,contact1,contact2};
		schedule_history_notif.add(a);
	}
	
	public void joinMatch(String team_name) {
		String[][] data = schedule_data.get(team_name);
		System.out.printf("%-10s  : %-10s %n","Location",data[4][0]);
		System.out.printf("%-10s  : %-10s %n","date",data[5][0]);
		System.out.printf("%-10s  : %-10s %n","time",data[6][0]);
		System.out.printf("%-10s  : %-10s %n","note",data[7][0]);
		System.out.printf("%-10s  : %-10s %n","team",data[0][0]);
		System.out.printf("%-10s  : %-10s %n","leader",data[1][0]);
		System.out.printf("%-10s  : %-10s %n","Contact",data[3][0]);
		System.out.printf("%-10s  : ","Player");
		for(String player : data[2]) {
			System.out.printf("|%2s|",player);
		}
		System.out.println("\n===========vs===============");
		System.out.printf("%-10s  : %-10s %n","team",this.user[1][0]);
		System.out.printf("%-10s  : %-10s %n","Leader",this.user[2][0]);
		System.out.printf("%-10s  : ","Player");
		for(String player : this.user[3]) {
			System.out.printf("|%2s|",player);
		}
		System.out.printf("%n%-10s  : %-10s %n","Contact",this.user[4][0]);
		while(true) {
			System.out.println("\n1. Match with this");
			System.out.println("2. Exit");
			System.out.print("Input :");
			//error handling int
			int user_input = scan.nextInt();
			scan.nextLine();
			if (user_input==2){
				break;
			}else if(user_input==1){
				System.out.println("you match to this one");
				String[] arr = {data[0][0],this.user[1][0],data[4][0],data[5][0],data[6][0],data[7][0],data[3][0],this.user[4][0]};
				schedule_history_temp.add(arr);
				addNotif(data[0][0],this.user[1][0],data[4][0],data[5][0],data[6][0],data[7][0],data[3][0],this.user[4][0]);
				removeSchedule(data[0][0]);
				break;
			}else {
				System.out.println("input 1 - 2");
			}
		}
	}
	
	public void ShowNotif(String team_name) {
		for(String[] i : schedule_history_notif) {
			if(i[0]==team_name) {
				System.out.println("woi tolol match anda sudah ada");
				System.out.printf("%-10s  %-10s %n","Team 1",i[0]); //team 1
				System.out.printf("%-10s  %-10s %n","Team 1 Contact",i[6]); //contact team1
				System.out.printf("%-10s  %-10s %n","Team 2",i[1]); //team 2
				System.out.printf("%-10s  %-10s %n","Team 2 Contact",i[7]); //contact team 2
				System.out.printf("%-10s  %-10s %n","Leader",i[2]); // location
				System.out.printf("%-10s  %-10s %n","Date",i[3]); //date
				System.out.printf("%-10s  %-10s %n","Time",i[4]); //time
				System.out.printf("%-10s  %-10s %n","Location",i[5]); //location
				schedule_history_notif.remove(i);
				Lib.pressAnyKeyToContinue();
				break;
			}
		}
	}
	
	public void showHistory() {
		if(schedule_data.containsKey(this.user[1][0])) {
			//jika create match sudah ada dan belum di click user lain
			System.out.printf("%-10s  %-10s %n","Team",schedule_data.get(this.user[1][0])[0][0]);
			System.out.printf("%-10s  %-10s %n","Leader",schedule_data.get(this.user[1][0])[1][0]);
			System.out.printf("%-18s","Player");
			for(String str : schedule_data.get(this.user[1][0])[2]) {
				System.out.printf("|&-2s|",str);
			}
			System.out.printf("%-10s  %-10s %n","Contact",schedule_data.get(this.user[1][0])[3][0]);
			System.out.printf("%-10s  %-10s %n","Location",schedule_data.get(this.user[1][0])[4][0]);
			System.out.printf("%-10s  %-10s %n","Date",schedule_data.get(this.user[1][0])[5][0]);
			System.out.printf("%-10s  %-10s %n","Time",schedule_data.get(this.user[1][0])[6][0]);
			System.out.printf("%-10s  %-10s %n","Note",schedule_data.get(this.user[1][0])[7][0]);
		}else {
			// jika sudah ketemu match dengan user lain
			for(String [] i : schedule_history_temp) {
				if(i[1].contentEquals(this.user[1][0]) || i[0].contentEquals(this.user[1][0])) {
					//tampilkan data ongoing match
					System.out.println("woi tolol match anda sudah ada");
					System.out.printf("%-10s  %-10s %n","Team 1",i[0]); //team 1
					System.out.printf("%-10s  %-10s %n","Team 1 Contact",i[6]); //contact team1
					System.out.printf("%-10s  %-10s %n","Team 2",i[1]); //team 2
					System.out.printf("%-10s  %-10s %n","Team 2 Contact",i[7]); //contact team 2
					System.out.printf("%-10s  %-10s %n","Leader",i[2]); // location
					System.out.printf("%-10s  %-10s %n","Date",i[3]); //date
					System.out.printf("%-10s  %-10s %n","Time",i[4]); //time
					System.out.printf("%-10s  %-10s %n","Location",i[5]); //location
				}			
			}
		}
		// match yang pernah
		System.out.println("=======================================");
		for(String [] i : schedule_history) {
			if(i[0].contentEquals(this.user[1][0])) {
				//show finished match
				System.out.println("=============================");
			}
		}
		Lib.pressAnyKeyToContinue();
	}
	
	public void cancleMatch(String teamName) {
		if(schedule_data.containsKey(this.user[1][0])) {
			System.out.printf("%-10s  %s %n","Team",schedule_data.get(this.user[1][0])[0][0]);
			System.out.printf("%-10s  %s %n","Leader",schedule_data.get(this.user[1][0])[1][0]);
			for(String str : schedule_data.get(this.user[1][0])[2]) {
				System.out.printf("|&-2s|",str);
			}
			System.out.printf("%n%-10s  %s %n","Contact",schedule_data.get(this.user[1][0])[3][0]);
			System.out.printf("%-10s  %s %n","Location",schedule_data.get(this.user[1][0])[4][0]);
			System.out.printf("%-10s  %s %n","Date",schedule_data.get(this.user[1][0])[5][0]);
			System.out.printf("%-10s  %s %n","Time",schedule_data.get(this.user[1][0])[6][0]);
			System.out.printf("%-10s  %s %n","Note",schedule_data.get(this.user[1][0])[7][0]);
			System.out.println("1. Yes");
			System.out.println("2. Exit");
			System.out.println("are sure want to cancle this match : ");
			int userInput = scan.nextInt();
			try {
				if(userInput==1) {
					schedule_data.remove(this.user[1][0]);
					if(schedule_data.containsKey(this.user[1][0])) {
						System.out.println("Somthing wrong can't delete match");
					}else {
						System.out.println("match has been deleted");
					}
				}else if(userInput==2) {
				}else {
					System.out.println("Input 1 - 2");
				}					
			}catch(InputMismatchException e) {
				System.out.println("Integer only");
			}
			
		}else {
			for(String[] i : schedule_history_temp) {
				if(i[0].contentEquals(teamName) || i[1].contentEquals(teamName)) {
					System.out.println("woi tolol match anda sudah ada");
					System.out.printf("%-10s  %-10s %n","Your Team",i[0]); //team 1
					System.out.printf("%-10s  %-10s %n","Your contact",i[6]); //contact team1
					System.out.printf("%-10s  %-10s %n","Enemy Team",i[1]); //team 2
					System.out.printf("%-10s  %-10s %n","Enemy contact",i[7]); //contact team 2
					System.out.printf("%-10s  %-10s %n","Leader",i[2]); // location
					System.out.printf("%-10s  %-10s %n","Date",i[3]); //date
					System.out.printf("%-10s  %-10s %n","Time",i[4]); //time
					System.out.printf("%-10s  %-10s %n","Location",i[5]); //location
					System.out.println("\n1. Yes");
					System.out.println("\n2. Exit");
					System.out.println("are sure want to cancle this match : ");
					int userInput = scan.nextInt();
					try {
						if(userInput==1) {
							if(i[0].contentEquals(teamName)) {
								schedule_history_temp.remove(i);								
							}else {
								String[][] data = account_datas.get(Lib.getKey(account_datas, i[0]));
								String[][] temp = {data[1],data[2],data[3],data[4],{i[2]},{i[3]},{i[4]},{i[5]}};
								schedule_data.put(i[0],temp);
								schedule_history_temp.remove(i);	
							}
							
							if(schedule_history_temp.contains(i)) {
								System.out.println("Somthing wrong can't delete match");
							}else {
								System.out.println("match has been deleted");
							}
							break;
						}else if(userInput==2) {
							break;
						}else {
							System.out.println("Input 1 - 2");
							break;
						}					
					}catch(InputMismatchException e) {
						System.out.println("Invalid Input");
						break;
					}
				}
			}
		}
		Lib.clscr();
	}
	public void refreshHistory() {
		//from temp history to history
	}

	public void showAllSchedule(){
		Lib.clscr();
		if(schedule_data.isEmpty()) {
			System.out.println("No Match");
			Lib.pressAnyKeyToContinue();
		}else {
			while(true) {
				for(Entry<String, String[][]> entry : schedule_data.entrySet()) {
					System.out.printf("%-10s  : %s %n","Team",entry.getValue()[0][0]);
					System.out.printf("%-10s  : %s %n","Location",entry.getValue()[2][0]);
					System.out.printf("%-10s  : %s %n","Date",entry.getValue()[5][0]);
					System.out.printf("%-10s  : %s %n","Time",entry.getValue()[6][0]);
					System.out.printf("%-10s  : %s %n","Leader",entry.getValue()[1][0]);
					System.out.printf("%-10s  : ","Player");
					for(String player : entry.getValue()[2]) {
						System.out.printf("|%2s|",player);
					}
					System.out.printf("%n%-10s  : %-10s %n","Contact",entry.getValue()[3][0]);
					System.out.printf("%-10s  : %-10s %n","Note",entry.getValue()[7][0]);
					System.out.println("==========================");
				}
				System.out.print("\nInput team name to join match [0 to exit]: "); String user_input =scan.nextLine();
				if(schedule_data.containsKey(user_input)) {
					if(this.user[1][0].contentEquals(user_input)){
						System.out.println("\nCan't join your own match");
						Lib.pressAnyKeyToContinue();
					}else {
						joinMatch(user_input);						
					}
					break;
				}else if(user_input.contentEquals("0")) {
					break;
				}else {
					System.out.println("No match found");
					Lib.pressAnyKeyToContinue();
					break;
				}
			}
		}
	}
	
	public String[][] getSchedule() {
		return schedule_data.get(this.user[0][0]);
	}
}

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
		if(schedule_data.containsKey(this.user[1][0])){
			System.out.println("You can only create 1 schedule");
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
	
	public void addNotif(String team_name1,String team_name2,String location1,String date1,String time1,String note1) {
		String [] a = {team_name1,team_name2,location1,date1,time1,note1};
		schedule_history_notif.add(a);
	}
	
	public void joinMatch(String team_name) {
		String[][] data = schedule_data.get(team_name);
		System.out.println("Location :"+data[4][0]);
		System.out.println("date :"+data[5][0]);
		System.out.println("time :"+data[6][0]);
		System.out.println("note :"+data[7][0]);
		System.out.println("team :"+data[0][0]);
		System.out.println("leader :"+data[1][0]);
		for(String str : data[2]) {
			System.out.println(str);
		}
		System.out.println(""+data[3][0]);
		System.out.println("==========================");
		System.out.println("vs");
		System.out.println(this.user[1][0]);
		System.out.println(this.user[2][0]);
		for(String str : this.user[3]) {
			System.out.println(str);
		}
		System.out.println(this.user[4][0]);
		while(true) {
			System.out.println("1. Match with this");
			System.out.println("2. Exit");
			System.out.print("Input :");
			//error handling int
			int user_input = scan.nextInt();
			scan.nextLine();
			if (user_input==2){
				break;
			}else if(user_input==1){
				System.out.println("you match to this one");
				String[] arr = {data[0][0],this.user[1][0],data[4][0],data[5][0],data[6][0],data[7][0]};
				schedule_history_temp.add(arr);
				addNotif(data[0][0],this.user[1][0],data[4][0],data[5][0],data[6][0],data[7][0]);
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
				System.out.println(i[0]);
				System.out.println(i[1]);
				System.out.println(i[2]);
				System.out.println(i[3]);
				System.out.println(i[4]);
				System.out.println(i[5]);
				String[] arr = {i[0],i[1],i[2],i[3],i[4],i[5]};
				schedule_history_notif.remove(i);
				Lib.pressAnyKeyToContinue();
				break;
			}
		}
	}
	
	public void showHistory() {
		for(String [] i : schedule_history_temp) {
			if(i[1].contentEquals(this.user[1][0])) {
				//tampilkan data ongoing match
				System.out.println("enemy "+i[0]);
				System.out.println("my "+i[1]);
			}
		}
		
		System.out.println("=======================================");
		for(String [] i : schedule_history_temp) {
			if(i[0].contentEquals(this.user[1][0])) {
				//show finished match
				System.out.println("=============================");
			}
		}
		Lib.pressAnyKeyToContinue();
	}
	
	public void cancleMatch(String teamName) {
		for(String[] i : schedule_history_temp) {
			if(i[0].contentEquals(teamName) || i[1].contentEquals(teamName)) {
				System.out.printf("%s versus %s",i[0],i[1]);
				System.out.println("Location "+i[2]);
				System.out.println("date"+i[3]);
				System.out.println("date"+i[4]);
				System.out.println("\n1. Yes");
				System.out.println("\n2. Exit");
				System.out.println("are sure want to cancle this match : ");
				int userInput = scan.nextInt();
				try {
					if(userInput==1) {
						System.out.println(schedule_history_temp.remove(i));
						break;
					}else if(userInput==2) {
						break;
					}else {
						System.out.println("Input 1 - 2");
						break;
					}					
				}catch(InputMismatchException e) {
					System.out.println("Integer only");
					break;
				}
			}
		}
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
					System.out.println("date "+entry.getValue()[5][0]);
					System.out.println("time "+entry.getValue()[6][0]);
					System.out.println("note "+entry.getValue()[7][0]);
					System.out.println("team "+entry.getValue()[0][0]);
					System.out.println("leader "+entry.getValue()[1][0]);
					for(String str : entry.getValue()[2]) {
						System.out.println(str);
					}
					System.out.println(""+entry.getValue()[3][0]);
					System.out.println("==========================");
				}
				System.out.print("\nInput team name to join match [0 to exit]: "); String user_input =scan.nextLine();
				if(schedule_data.containsKey(user_input)) {
					joinMatch(user_input);
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

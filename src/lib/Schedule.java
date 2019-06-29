package lib;
import java.util.*;
import java.util.Map.Entry;
public class Schedule{
	private String location;
	private String date;
	private String time;
	private String note;
	private String[][] user;
	private Map<String,String[][]> schedule_data = new HashMap<String,String[][]>();
	private Map<String,String[][]> schedule_history = new HashMap<String,String[][]>();
	Scanner scan = new Scanner(System.in);
	
	public Schedule(Account userdata){
		this.user = userdata.get_userdata();
	}
	
	public void create() {
		Lib.clscr();
		if(schedule_data.containsKey(this.user[1][0])) {
			System.out.println("Create your schedule");
			System.out.print("\n Location :\t");
			location = scan.nextLine();
			System.out.print("\n Date (dd/mm/yyyy):\t");
			date = scan.next();
			System.out.print("\n Time (hh/mm/ss):\t");
			time = scan.next();
			System.out.print("\note :\t");
			note = scan.nextLine();
			addSchedule(location,date,time,note);			
		}else {
			System.out.println("You can only create 1 schedule");
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
	
	public void addtoHistory(String team_name,String [][] data) {
		schedule_history.put(team_name,data);
	}
	
	public void joinMatch(String team_name) {
		String[][] data = schedule_data.get(team_name);
		System.out.println("date "+data[5][0]);
		System.out.println("time "+data[6][0]);
		System.out.println("note "+data[7][0]);
		System.out.println("team "+data[0][0]);
		System.out.println("leader "+data[1][0]);
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
			if (user_input==2){
				break;
			}else if(user_input==1){
				System.out.println("you match to this one");
				removeSchedule(data[0][0]);
				addtoHistory(data[0][0],data);
				break;
			}else {
				System.out.println("input 1 - 2");
			}
		}
	}

	public void showAllSchedule(){
		Lib.clscr();
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
			try {
				System.out.print("\nInput team name to join match [0 to exit]: "); String user_input =scan.nextLine();
				if(schedule_data.containsKey(user_input)) {
					joinMatch(user_input);
					break;
				}else if(user_input=="0") {
					break;
				}else {
					System.out.println("No match found");
					Lib.pressAnyKeyToContinue();
					break;
				}				
			}catch(Exception e) {
				System.out.println("\n=====No match found=====");
			}
		}
		
		
	}
	
	
	public String[][] getSchedule() {
		return schedule_data.get(this.user[0][0]);
	}
}

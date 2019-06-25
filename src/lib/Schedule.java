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
//	private ArrayList<String[][]> scheduledata = new ArrayList<String[][]>();
	Scanner scan = new Scanner(System.in);
	
	public Schedule(Account userdata){
		this.user = userdata.get_userdata();
	}
	
	public void create() {
		Lib.clscr();
		if(schedule_data.containsKey(this.user[0][0])) {
			System.out.println("Create your schedule");
			System.out.print("\n Location : \t");
			location = scan.nextLine();
			System.out.print("\n Date (dd/mm/yyyy): \t");
			date = scan.next();
			System.out.print("\n Time (hh/mm/ss): \t");
			time = scan.next();
			System.out.print("\note : \t");
			note = scan.nextLine();
			addSchedule(location,date,time,note);			
		}else {
			System.out.println("You can only create 1 schedule");
			Lib.pressAnyKeyToContinue();
		}
		
	}
	
	public void addSchedule(String location, String date, String time, String note) {
		String [][] temp = {this.user[1],this.user[2],this.user[3],this.user[4],{location},{date},{time},{note}};
		schedule_data.put(this.user[0][0],temp);
	}
	
	public void showSchedule(){
		Lib.clscr();
		for(Entry<String, String[][]> entry : schedule_data.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue()[0][0]);
			System.out.println(entry.getValue()[1][0]);
		}
		Lib.pressAnyKeyToContinue();
	}
	
	public String[][] getSchedule() {
		return schedule_data.get(this.user[0][0]);
	}
}

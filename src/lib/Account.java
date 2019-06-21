package lib;
import java.util.*;
public class Account {
	private Map<String, String[][]> _account_datas(){
		Map<String,String[][]> account_datas = new HashMap<String,String[][]>();
		account_datas.put("username", new String[][] {{"password"},{"team_name"},{"leader_name"},{"player_name"},{"contact"}});
		account_datas.put("username1", new String[][] {{"password1"},{"team_name1"},{"leader_name1"},{"player_name1"},{"contact1"}});
		account_datas.put("username2", new String[][] {{"password2"},{"team_name2"},{"leader_name2"},{"player_name2"},{"contact2"}});
		return account_datas;
	}
	
	public String username;
	public String password;
	public String team_name;
	public String leader_name;
	public String player_name[];
	public String contact;
	
	public int get_userdata(String username) {
		Map<String, String[][]> account_datas = _account_datas();
		String[][] account_data = account_datas.get(username);
		if(account_data == null) {
			System.out.println(username);
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
	
	public int login() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Username :");
		String input_username = scan.next();
		System.out.println("Password :");
		String input_password = scan.next();
		
		if(get_userdata(input_username)==0) {
			System.out.println("Username or Password incorrect");
			return 0;
		}
		if (input_password.contentEquals(this.password)) {
			return get_userdata(username);
		}
		else{
			System.out.println("Username or Password incorrect");
			return 0;
		}
	}
}

package lib;
import java.util.*;
public class Account {
	
	private Dictionary<String, String[]> _account_datas(){
		Dictionary<String,String[]> account_datas = new Hashtable<String,String[]>();
		account_datas.put("username", new String[] {"password","team_name","leader_name","player_name","contact"});
		account_datas.put("username1", new String[] {"password1","team_name1","leader_name1","player_name1","contact1"});
		account_datas.put("username2", new String[] {"password2","team_name2","leader_name2","player_name2","contact2"});
		return account_datas;
	}
	
	public String username;
	public String password;
	public String team_name;
	public String leader_name;
	public String player_name;
	public String contact;
	
	public int get_userdata(String username) {
		Dictionary<String, String[]> account_datas = _account_datas();
		String[] account_data = account_datas.get(username);
		if(account_data == null) {
			return 0;
		}else {
			this.username = username;
			this.password = account_data[0];
			this.team_name = account_data[1];
			this.leader_name = account_data[2];
			this.player_name = account_data[3];
			this.contact = account_data[4];
		}
		return 1;
	}
	
	public int login(String username,String password) {
		get_userdata(username);
		if(get_userdata(username)==0) {
			System.out.println("Username or Password incorrect");
			return 0;
		}
		
		if (password==this.password) {
			return 1;
		}else{
			System.out.println("Username or Password incorrect");
			return 0;
		}
	}
}

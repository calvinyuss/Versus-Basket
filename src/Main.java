import lib.Account;
import java.util.*;

public class Main {
	

	public static void main(String[] args) {			
		Scanner scan = new Scanner(System.in);
		
		String input_username = scan.next();
		String input_password = scan.next();
		Account user = new Account();
		user.login(input_username,input_password);
	}
}

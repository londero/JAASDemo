package demo.jaas;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class Driver {
	
	public static void main(String[] args) {
		System.setProperty("java.security.auth.login.config", "jaas.config");
		
		LoginContext loginContext = null;
		try {
			loginContext = new LoginContext("SecJaasTutorial", new SecCallbackHandler()); //SecJaasTutorial definido no arquivo jaas.config
		} catch (LoginException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		while (true) {
			try {
				loginContext.login();
				System.exit(0);
			} catch (LoginException e) {
				//e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
		}
	}
}

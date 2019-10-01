package demo.jaas;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class SecLoginModule implements LoginModule {
	
	public static final String TEST_USERNAME = "admin";
	public static final String TEST_PASSWORD = "admin";
	private CallbackHandler callbackHandler = null;
	private boolean authenticationSuccessFlag = false;
	
	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean commit() throws LoginException {
		// TODO Auto-generated method stub
		return authenticationSuccessFlag;
	}

	@Override
	public void initialize(Subject arg0, CallbackHandler callbackHandler, Map<String, ?> arg2, Map<String, ?> arg3) {
		this.callbackHandler = callbackHandler;
	}

	@Override
	public boolean login() throws LoginException {
		Callback[] callbackArray = new Callback[2];
		callbackArray[0] = new NameCallback("User Name:");
		callbackArray[1] = new PasswordCallback("Password:", false);
				
		try {
			callbackHandler.handle(callbackArray);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		} catch (UnsupportedCallbackException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		String name = ((NameCallback) callbackArray[0]).getName();
		String password = new String(((PasswordCallback) callbackArray[1]).getPassword());
		
		if (TEST_USERNAME.equals(name) && TEST_PASSWORD.equals(password)) {
			System.out.println("autentication success ...");
			authenticationSuccessFlag = true;
		} else {
			authenticationSuccessFlag = false;
			throw new FailedLoginException("autentication failure ..."); 
		}
		
		return authenticationSuccessFlag;
	}

	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

}

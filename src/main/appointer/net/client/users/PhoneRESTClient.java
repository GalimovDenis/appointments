package appointer.net.client.users;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import appointer.ui.text.dialogs.UserDialogPhone;


/**
 * Tests connecting to the server, passes phone number to validate;
 */
public class PhoneRESTClient {
	
	public static void sendPhone() {
		String string = UserDialogPhone.phoneFromUser();
		try {
 
			System.out.println(string);
 
			// Step2: Now pass JSON File Data to REST Service
			try {
				URL url = new URL("http://localhost:8080/2018-10-15_appointments_server/api/phone");
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(string);
				out.close();
 
				StringBuilder phoneResponseBuilder = new StringBuilder();
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
						phoneResponseBuilder.append(line);
				}
			
				System.out.println("\nPhone check REST Service Invoked Successfully..");
				System.out.println(phoneResponseBuilder.toString());
				in.close();
			} catch (Exception e) {
				System.out.println("\nError while calling phone check REST Service");
				System.out.println(e);
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
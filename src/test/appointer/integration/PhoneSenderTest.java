package appointer.integration;

import appointer.net.client.users.PhoneRESTClient;

/**
 * Demo of sending phone number to server and getting the answer
 *
 */
public class PhoneSenderTest {
	
	public static void main(String[] args) {
		PhoneRESTClient.sendPhone();
	}

}

package br.udesc.social.computing;
import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.schema.Connections;
import com.google.code.linkedinapi.schema.Person;


public class Linkedin {

	public static void printNetworkData(String consumerKey, String consumerSecret, String token, String tokenSecret) {
		final LinkedInApiClientFactory factory = LinkedInApiClientFactory.newInstance(consumerKey, consumerSecret);
		final LinkedInApiClient client = factory.createLinkedInApiClient(token, tokenSecret);

		Connections connections = client.getConnectionsForCurrentUser();
		System.out.println("Total connections fetched:" + connections.getTotal());
		for (Person person : connections.getPersonList()) {
		    System.out.println(person.getId() + ":" + person.getFirstName() + " " + person.getLastName() + ":" + person.getHeadline());
		}
	}

}

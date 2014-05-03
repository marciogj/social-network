package br.udesc.social.computing;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.NamedFacebookType;
import com.restfb.types.User;


public class Facebook {

	/**
	 * Creates a CSV report using provided token. facebook token might be takeon from here:
	 * https://developers.facebook.com/docs/facebook-login/access-tokens/
	 * https://developers.facebook.com/tools/explorer/?method=GET&path=me%3Ffields%3Did%2Cname
	 * @param token
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void createFriendsProfilesCountReport(String token, String reportPath) throws FileNotFoundException, UnsupportedEncodingException {
		FacebookClient fbClient = new DefaultFacebookClient(token);
		User user = fbClient.fetchObject("me", User.class);
		System.out.println("User name: " + user.getName());
		writeFacebookCSV(fbClient, reportPath);
	}
	
	public static void writeFacebookCSV(FacebookClient fbClient, String path) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(path, "UTF-8");
		Connection<User> myFriends = fbClient.fetchConnection("me/friends", User.class);
		List<User> allFriends = myFriends.getData();
		int i = 1;
		int total = allFriends.size();
		StringBuffer buffer = null;
		writer.println("name;lastname;email;id;gender;birthday;hometown;locale;relashionship;religion;currency;political;work;teams;education;location;hometown;signficantOther");
		for (User friend : allFriends) {
			System.out.println("Processing " + i++ + "/" + total);
			buffer = new StringBuffer();
			User f = fbClient.fetchObject(friend.getId(), User.class);			
			buffer.append(f.getName() == null ? 0 : 1 );
			buffer.append(";");
			buffer.append(f.getLastName()== null ? 0 : 1 );
			buffer.append(";");
			buffer.append(f.getEmail()== null ? 0 : 1 );
			buffer.append(";");
			buffer.append(f.getId()== null ? 0 : 1 );
			buffer.append(";");
			buffer.append(f.getGender()== null ? 0 : 1 );
			buffer.append(";");
			buffer.append(f.getBirthday()== null ? 0 : 1 );
			buffer.append(";");
			buffer.append(f.getHometownName()== null ? 0 : 1 );
			buffer.append(";");
			buffer.append(f.getLocale()== null ? 0 : 1 );
			buffer.append(";");
			buffer.append(f.getRelationshipStatus()== null ? 0 : 1 );
			buffer.append(";");
			buffer.append(f.getReligion()== null ? 0 : 1 );
			buffer.append(";");
			buffer.append(f.getCurrency()== null ? 0 : 1 );
			buffer.append(";");
			buffer.append(f.getPolitical()== null ? 0 : 1 );
			buffer.append(";");
			buffer.append(f.getWork().size() == 0 ? 0 : 1 );
			buffer.append(";");
			buffer.append(f.getFavoriteTeams().size() == 0 ? 0 : 1 );
			buffer.append(";");
			buffer.append(f.getEducation().size() == 0 ? 0 : 1 );
			buffer.append(";");
			buffer.append(f.getLocation()== null ? 0 : 1 );
			buffer.append(";");
			buffer.append(f.getHometown()== null ? 0 : 1 );
			buffer.append(";");
			buffer.append(f.getSignificantOther()== null ? 0 : 1 );
			buffer.append(";");
			
			writer.println(buffer.toString());
		}
		writer.close();
	}
	
	
	public static String getNamedType(List<NamedFacebookType> aList) {
		StringBuffer buffer = new StringBuffer();
		if (aList != null) {
			for (NamedFacebookType item : aList) {
				buffer.append(item.getName());
				buffer.append(", ");
			}
		} else {
			buffer.append("null");
		}
		return buffer.toString();
	}
	

}

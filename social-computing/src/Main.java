import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import br.udesc.social.computing.Facebook;


public class Main {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Facebook.createFriendsProfilesCountReport("token", "facebook-"+System.currentTimeMillis()+".csv");
	}
	

	

}

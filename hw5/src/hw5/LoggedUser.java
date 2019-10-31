package hw5;

import java.util.ArrayList;

public class LoggedUser {
	private static Profiles profile;

	public static Profiles getProfile() {
		return profile;
	}

	public static void setProfile(Profiles profile) {
		LoggedUser.profile = profile;
	}
	
	public static void ClearData()
	{
		profile = null;;
	}
	
	
}

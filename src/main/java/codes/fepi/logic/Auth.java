package codes.fepi.logic;


import codes.fepi.entity.User;
import spark.Request;
import spark.Response;

public class Auth {

	private static final String COOKIE_NAME = "userToken";

	private Auth() {
	}

	public static User getUser(Request request) throws IllegalAccessException {
		final User user = getUserNullable(request);
		if (user == null) {
			throw new IllegalAccessException("Not allowed to access route without user authentication");
		}
		return user;
	}

	public static User getUserNullable(Request request) {
		final String userToken = request.cookie(COOKIE_NAME);
		return UserManager.INSTANCE.getUserFromToken(userToken);
	}

	public static void setToken(Response response, String token) {
		response.cookie("/", COOKIE_NAME, token, -1, false);
	}

	public static void removeToken(Response response) {
		response.removeCookie("/", COOKIE_NAME);
	}
}

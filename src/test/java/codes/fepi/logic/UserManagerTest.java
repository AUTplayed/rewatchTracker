package codes.fepi.logic;

import codes.fepi.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class UserManagerTest {

	@Test
	void test() {
		final User user = new User("test");
		UserManager.INSTANCE.setUsers(Collections.singletonList(user));
		final String base64Token = UserManager.INSTANCE.getTokenFromUser(user);
		System.out.println(base64Token);
		final User userFromToken = UserManager.INSTANCE.getUserFromToken(base64Token);
		Assertions.assertNotNull(userFromToken);
		Assertions.assertEquals(user.getId(), userFromToken.getId());
	}

}
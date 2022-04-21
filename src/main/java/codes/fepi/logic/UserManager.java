package codes.fepi.logic;

import codes.fepi.model.LoginRequestDto;
import codes.fepi.entity.Token;
import codes.fepi.entity.User;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum UserManager {
	INSTANCE;

	private Map<String, User> users;

	UserManager() {
		this.users = new ConcurrentHashMap<>();
	}

	public void setUsers(List<User> users) {
		this.users = users.stream().collect(Collectors.toMap(User::getId, Function.identity(),
				(u1, u2) -> u1, ConcurrentHashMap::new));
	}

	public Map<String, User> getUsers() {
		return new HashMap<>(this.users);
	}

	public User getUserFromToken(String base64Token) {
		if (base64Token == null) {
			return null;
		}
		try (final ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(base64Token)))) {
			final Token token = new Token();
			token.readObject(inputStream);
			return getUserFromToken(token);
		} catch (IOException | ClassNotFoundException e) {
			// ignore faulty token
		}
		return null;
	}

	private User getUserFromToken(Token token) {
		final String userId = token.getUserId();
		// todo token verify?
		return users.get(userId);
	}

	public String getTokenFromUser(User user) {
		final Token token = new Token(user.getId());
		final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		try (final ObjectOutputStream outputStream = new ObjectOutputStream(byteStream)) {
			token.writeObject(outputStream);
			outputStream.flush();

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return Base64.getEncoder().encodeToString(byteStream.toByteArray());
	}

	public User tryLogin(LoginRequestDto loginRequestDto) {
		if (loginRequestDto == null || loginRequestDto.getUserId() == null) {
			return null;
		}
		User user = users.get(loginRequestDto.getUserId());
		if (user == null) {
			user = createUser(loginRequestDto);
		}
		return user;
	}

	private User createUser(LoginRequestDto loginRequestDto) {
		final User user = new User(loginRequestDto.getUserId());
		users.put(loginRequestDto.getUserId(), user);
		ActivityTracker.INSTANCE.changed();
		return user;
	}
}

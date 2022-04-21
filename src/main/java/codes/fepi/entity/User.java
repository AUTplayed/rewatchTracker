package codes.fepi.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class User {

	private final String id;

	@JsonCreator
	public User(@JsonProperty("id") String id) {
		this.id = Objects.requireNonNull(id);
	}

	public String getId() {
		return id;
	}
}

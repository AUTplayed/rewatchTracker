package codes.fepi.model;

import java.util.Collections;
import java.util.List;

public class ShowsResponseDto {

	private final String message;
	private final List<ShowIndexDto> shows;

	public ShowsResponseDto(String message) {
		this.message = message;
		this.shows = Collections.emptyList();
	}

	public ShowsResponseDto(List<ShowIndexDto> shows) {
		this.message = null;
		this.shows = shows;
	}

	public String getMessage() {
		return message;
	}

	public List<ShowIndexDto> getShows() {
		return shows;
	}
}

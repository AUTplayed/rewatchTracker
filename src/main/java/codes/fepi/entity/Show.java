package codes.fepi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.IllegalFormatException;
import java.util.Objects;

public class Show {
	private String name;
	private int episode;
	private String notes;
	private String urlPattern;

	public Show() {
	}

	public Show(String name, int episode) {
		this.name = name;
		this.episode = episode;
	}

	public Show(String name, int episode, String notes) {
		this.name = name;
		this.episode = episode;
		this.notes = notes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	@JsonIgnore
	public String getWatchUrl() {
		if (urlPattern == null) {
			return null;
		}
		final int notesIndex = urlPattern.indexOf("%s");
		final int epIndex = urlPattern.indexOf("%d");
		try {
			if ((epIndex < notesIndex && epIndex != -1) || notesIndex == -1 && epIndex != -1) {
				return String.format(urlPattern, getEpisode(), getNotes());
			}
			return String.format(urlPattern, getNotes(), getEpisode());
		} catch (IllegalFormatException e) {
			return "javascript:alert('invalid watchUrl format: " + e.getMessage() + "')";
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Show show = (Show) o;
		return Objects.equals(name, show.name);
	}
}

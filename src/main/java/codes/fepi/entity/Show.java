package codes.fepi.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.IllegalFormatException;
import java.util.Objects;

public class Show implements Comparable<Show> {
	private final String name;
	private int episode;
	private String notes;
	private String urlPattern;
	private long lastModified;

	@JsonCreator
	public Show(@JsonProperty(value = "name") final String name) {
		this.name = Objects.requireNonNull(name);
	}

	public Show(final String name, final int episode) {
		this(name);
		this.episode = episode;
	}

	public Show(final String name, final int episode, final String notes) {
		this(name, episode);
		this.notes = notes;
	}

	public String getName() {
		return name;
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

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public void touch() {
		this.lastModified = System.currentTimeMillis();
	}

	@JsonIgnore
	public String getWatchUrl() {
		if (urlPattern == null) {
			return null;
		}
		final int notesIndex = urlPattern.indexOf("%s");
		final int epIndex = urlPattern.indexOf("%d");
		try {
			if ((epIndex < notesIndex && epIndex != -1) || notesIndex == -1) {
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

	@Override
	public int compareTo(Show o) {
		return Long.compare(o.getLastModified(), this.lastModified);
	}
}

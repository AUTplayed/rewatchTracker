package codes.fepi.logic;

import codes.fepi.entity.Show;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public enum Repository {
	INSTANCE;

	private Map<String, List<Show>> shows;

	Repository() {
		shows = new ConcurrentHashMap<>();
	}

	public static Repository getInstance() {
		return INSTANCE;
	}

	List<Show> getShows(final String userId) {
		final List<Show> userShows = this.shows.get(userId);
		return userShows != null ? userShows : Collections.emptyList();
	}

	Map<String, List<Show>> getAllShows() {
		return new HashMap<>(shows);
	}

	void setShows(Map<String, List<Show>> shows) {
		this.shows = new ConcurrentHashMap<>(shows);
	}

	Show getShowByName(final String userId, final String name) {
		final List<Show> userShows = this.shows.get(userId);
		if (userShows == null || userShows.isEmpty()) {
			return null;
		}
		final Optional<Show> optionalShow = userShows.stream()
				.filter(show -> show.getName().equals(name))
				.findFirst();
		return optionalShow.orElse(null);
	}

	private void addShow(final String userId, final Show newShow) {
		final List<Show> userShows = this.shows.computeIfAbsent(userId, id -> new ArrayList<>());
		userShows.add(newShow);
	}

	public void updateShow(final String userId, final Show updatedShow) {
		final Show show = getShowByName(userId, updatedShow.getName());
		if (show == null) {
			addShow(userId, updatedShow);
			touched();
			return;
		}
		show.setEpisode(updatedShow.getEpisode());
		show.setNotes(updatedShow.getNotes());
		show.setUrlPattern(updatedShow.getUrlPattern());
		show.touch();
		touched();
	}

	public void deleteShow(final String userId, final Show show) {
		final List<Show> userShows = this.shows.get(userId);
		if (userShows == null || userShows.isEmpty()) {
			return;
		}
		userShows.remove(show);
		touched();
	}

	private void touched() {
		ActivityTracker.INSTANCE.changed();
	}

}

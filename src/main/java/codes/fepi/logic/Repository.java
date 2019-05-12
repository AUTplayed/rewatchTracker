package codes.fepi.logic;

import codes.fepi.entity.Show;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class Repository {
	private static Repository instance;
	private long lastChanged = 0;

	private List<Show> shows;

	private Repository() {
		shows = new CopyOnWriteArrayList<>();
	}

	public static Repository getInstance() {
		if(instance == null) instance = new Repository();
		return instance;
	}

	List<Show> getShows() {
		return shows;
	}

	void setShows(List<Show> shows) {
		this.shows = new CopyOnWriteArrayList<>(shows);
	}

	Show getShowByName(String name) {
		Optional<Show> optionalShow = shows.stream().filter(show -> show.getName().equals(name)).findFirst();
		return optionalShow.orElse(null);
	}

	public void updateShow(Show updatedShow) {
		Show show = getShowByName(updatedShow.getName());
		if(show == null) {
			shows.add(updatedShow);
			touched();
			return;
		}
		show.setEpisode(updatedShow.getEpisode());
		show.setNotes(updatedShow.getNotes());
		show.setUrlPattern(updatedShow.getUrlPattern());
		touched();
	}

	public void deleteShow(Show show) {
		shows.remove(show);
		touched();
	}

	long getLastChanged() {
		return lastChanged;
	}

	private void touched() {
		lastChanged = System.currentTimeMillis();
	}

}

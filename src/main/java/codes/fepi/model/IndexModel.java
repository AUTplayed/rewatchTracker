package codes.fepi.model;

import codes.fepi.entity.Show;

import java.util.List;

public class IndexModel {
	private List<Show> shows;

	public IndexModel(List<Show> shows) {
		this.shows = shows;
	}

	public List<Show> getShows() {
		return shows;
	}
}

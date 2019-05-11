package codes.fepi.model;

import codes.fepi.entity.Show;

public class ShowIndexDto {
	private String name;
	private int episode;
	private String watchUrl;

	public ShowIndexDto(Show show) {
		setName(show.getName());
		setEpisode(show.getEpisode());
		setWatchUrl(show.getWatchUrl());
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

	public String getWatchUrl() {
		return watchUrl;
	}

	public void setWatchUrl(String watchUrl) {
		this.watchUrl = watchUrl;
	}
}

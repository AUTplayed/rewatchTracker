package codes.fepi.entity;

public class Show {
	private String name;
	private int episode;

	public Show() {
	}

	public Show(String name, int episode) {
		this.name = name;
		this.episode = episode;
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
}

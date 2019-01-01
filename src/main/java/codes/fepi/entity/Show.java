package codes.fepi.entity;

public class Show {
	private String name;
	private int episode;
	private String notes;

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
}

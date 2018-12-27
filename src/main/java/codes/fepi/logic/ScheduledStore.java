package codes.fepi.logic;

import codes.fepi.entity.Show;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ScheduledStore implements Runnable {
	private long lastSaved = 0;
	ObjectMapper mapper = new ObjectMapper();
	File file = new File("./store.json");

	@Override
	public void run() {
		long lastChanged = Repository.getInstance().getLastChanged();
		if(lastChanged > lastSaved) {
			try {
				mapper.writeValue(file, Repository.getInstance().getShows());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void load() {
		try {
			List<Show> shows = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Show.class));
			Repository.getInstance().setShows(shows);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

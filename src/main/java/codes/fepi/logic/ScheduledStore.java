package codes.fepi.logic;

import codes.fepi.Main;
import codes.fepi.entity.Show;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class ScheduledStore implements Runnable {
	private long lastSaved = 0;
	private final ObjectMapper mapper = new ObjectMapper();
	private final File file = Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent().resolve("store.json").toFile();

	public ScheduledStore() throws URISyntaxException {
	}

	@Override
	public void run() {
		long lastChanged = Repository.getInstance().getLastChanged();
		if (lastChanged > lastSaved) {
			try {
				mapper.writeValue(file, Repository.getInstance().getShows());
				lastSaved = lastChanged;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void load() {
		try {
			if (!file.exists()) {
				Files.write(file.toPath(), Collections.singletonList("[]"));
			}
			List<Show> shows = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Show.class));
			Repository.getInstance().setShows(shows);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

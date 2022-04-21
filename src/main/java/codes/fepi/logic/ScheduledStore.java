package codes.fepi.logic;

import codes.fepi.Main;
import codes.fepi.entity.DataStore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

public class ScheduledStore implements Runnable {
	private final ObjectMapper mapper = new ObjectMapper();
	private final File file = Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent().resolve("store.json").toFile();

	public ScheduledStore() throws URISyntaxException {
	}

	@Override
	public void run() {
		if (ActivityTracker.INSTANCE.hasChanges()) {
			try {
				final DataStore store = StorageFactory.createDataStore();
				mapper.writeValue(file, store);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void load() {
		try {
			if (!file.exists()) {
				Files.write(file.toPath(), Collections.singletonList("{}"));
			}
			final DataStore store = mapper.readValue(file, DataStore.class);
			if (store.getShows() != null) {
				Repository.getInstance().setShows(store.getShows());
			}
			if (store.getUsers() != null) {
				UserManager.INSTANCE.setUsers(store.getUsers());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

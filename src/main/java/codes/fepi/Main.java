package codes.fepi;

import codes.fepi.ldfspark.LdfSpark;
import codes.fepi.logic.ScheduledStore;
import codes.fepi.routing.MainRouter;

import java.net.URISyntaxException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) throws URISyntaxException {
		MainRouter mainRouter = new MainRouter();
		mainRouter.init();
		LdfSpark.start();
		mainRouter.route();
		ScheduledStore scheduledStore = new ScheduledStore();
		scheduledStore.load();
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(scheduledStore, 1, 1, TimeUnit.HOURS);
	}
}

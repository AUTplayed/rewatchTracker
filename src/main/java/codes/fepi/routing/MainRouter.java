package codes.fepi.routing;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static spark.Spark.*;

public class MainRouter {

	public void route() {
		init();
		ApiRouter apiRouter = new ApiRouter();
		PageRouter pageRouter = new PageRouter();
		path("/api", apiRouter::route);
		path("/pages", pageRouter::route);
		try {
			// doing this here should keep the index file in memory for faster response (i think)
			URI uri = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("public/index.html")).toURI();
			byte[] bytes = Files.readAllBytes(Paths.get(uri));
			get("/:page", (req, res) -> bytes);
		} catch (Exception ignored) {
		}
	}

	private void init() {
		port(8080);
		staticFiles.location("/public");
		staticFiles.expireTime(60 * 60);
	}
}

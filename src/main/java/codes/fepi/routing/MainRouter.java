package codes.fepi.routing;

import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
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
			// reading the index file to ram for fast access on requests falling through all other routes
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("public/index.html");
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			byte[] data = new byte[8192];
			int nRead;
			while((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			byte[] bytes = buffer.toByteArray();
			get("/:page", (req, res) -> bytes);
		} catch (Exception e) {
			LoggerFactory.getLogger("router").error("load index error", e);
		}
	}

	private void init() {
		port(8080);
		staticFiles.location("/public");
		staticFiles.expireTime(60 * 60);
	}
}

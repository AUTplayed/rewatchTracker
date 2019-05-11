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
		ApiRouter apiRouter = new ApiRouter();
		path("/api", apiRouter::route);
	}

	public void init() {
		port(8080);
	}
}

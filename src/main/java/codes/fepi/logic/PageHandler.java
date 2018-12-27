package codes.fepi.logic;

import codes.fepi.entity.Show;
import codes.fepi.model.IndexModel;
import spark.Request;

import java.util.List;

public class PageHandler {
	public Object non(Request req) {
		return null;
	}

	public Object index(Request req) {
		List<Show> shows = Repository.getInstance().getShows();
		return new IndexModel(shows);
	}

	public Object show(Request req) {
		return Repository.getInstance().getShowByName(req.queryParams("name"));
	}
}

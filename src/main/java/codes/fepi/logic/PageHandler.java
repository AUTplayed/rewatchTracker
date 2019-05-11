package codes.fepi.logic;

import codes.fepi.entity.Show;
import codes.fepi.model.ShowIndexDto;
import spark.Request;

import java.util.List;
import java.util.stream.Collectors;

@codes.fepi.ldfspark.PageHandler
public class PageHandler {
	public static Object create(Request req) {
		return null;
	}

	public static Object index(Request req) {
		List<Show> shows = Repository.getInstance().getShows();
		return shows.stream().map(ShowIndexDto::new).collect(Collectors.toList());
	}

	public static Object show(Request req) {
		return Repository.getInstance().getShowByName(req.queryParams("name"));
	}
}

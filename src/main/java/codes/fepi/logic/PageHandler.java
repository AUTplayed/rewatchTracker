package codes.fepi.logic;

import codes.fepi.entity.Show;
import codes.fepi.model.ShowIndexDto;
import spark.Request;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@codes.fepi.ldfspark.PageHandler
public class PageHandler {
	public static Object create(Request req) {
		return null;
	}

	public static Object index() {
		return null;
	}

	public static Object shows(Request req) {
		String searchString = req.queryParams("search");
		List<Show> shows = Repository.getInstance().getShows();
		Stream<Show> stream = shows.stream();
		if(searchString != null) {
			stream = stream.filter(show -> show.getName().toLowerCase().contains(searchString.toLowerCase()));
		}
		return stream.sorted()
				.map(ShowIndexDto::new)
				.collect(Collectors.toList());
	}

	public static Object show(Request req) {
		return Repository.getInstance().getShowByName(req.queryParams("name"));
	}
}

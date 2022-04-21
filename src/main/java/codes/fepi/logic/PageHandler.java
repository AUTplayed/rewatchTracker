package codes.fepi.logic;

import codes.fepi.entity.Show;
import codes.fepi.entity.User;
import codes.fepi.model.ShowIndexDto;
import codes.fepi.model.ShowsResponseDto;
import spark.Request;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@codes.fepi.ldfspark.PageHandler
public class PageHandler {
	public static Object create(Request req) {
		return null;
	}

	public static Object index(Request req) {
		final User user = Auth.getUserNullable(req);
		return user != null;
	}

	public static Object shows(Request req) {
		String searchString = req.queryParams("search");
		final User user = Auth.getUserNullable(req);
		if (user == null) {
			return new ShowsResponseDto("Not logged in");
		}
		List<Show> shows = Repository.getInstance().getShows(user.getId());
		Stream<Show> stream = shows.stream();
		if(searchString != null) {
			stream = stream.filter(show -> show.getName().toLowerCase().contains(searchString.toLowerCase()));
		}
		return new ShowsResponseDto(stream.sorted()
				.map(ShowIndexDto::new)
				.collect(Collectors.toList()));
	}

	public static Object show(Request req) throws IllegalAccessException {
		return Repository.getInstance().getShowByName(Auth.getUser(req).getId(), req.queryParams("name"));
	}

	public static Object username(Request req) {
		final User user = Auth.getUserNullable(req);
		if (user == null) {
			return "Login";
		}
		return user.getId();
	}
}

package codes.fepi.routing;

import codes.fepi.entity.Show;
import codes.fepi.logic.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static spark.Spark.post;

class ApiRouter {

	void route() {
		ObjectMapper mapper = new ObjectMapper();
		post("/ep", (req, res) -> {
			Show show = mapper.readValue(req.bodyAsBytes(), Show.class);
			Repository.getInstance().updateEpisode(show.getName(), show.getEpisode());
			return "success";
		});
	}
}

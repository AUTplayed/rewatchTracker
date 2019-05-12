package codes.fepi.routing;

import codes.fepi.entity.Show;
import codes.fepi.logic.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static spark.Spark.delete;
import static spark.Spark.post;

class ApiRouter {

	void route() {
		ObjectMapper mapper = new ObjectMapper();
		post("/show", (req, res) -> {
			Show show = mapper.readValue(req.bodyAsBytes(), Show.class);
			Repository.getInstance().updateShow(show);
			return "success";
		});
		delete("/show", (req, res) -> {
			Show show = mapper.readValue(req.bodyAsBytes(), Show.class);
			Repository.getInstance().deleteShow(show);
			return "success";
		});
	}
}

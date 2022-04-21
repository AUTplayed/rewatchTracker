package codes.fepi.routing;

import codes.fepi.model.LoginRequestDto;
import codes.fepi.entity.Show;
import codes.fepi.entity.User;
import codes.fepi.logic.Auth;
import codes.fepi.logic.Repository;
import codes.fepi.logic.UserManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import static spark.Spark.delete;
import static spark.Spark.post;

class ApiRouter {

	void route() {
		ObjectMapper mapper = new ObjectMapper();
		post("/show", (req, res) -> {
			Show show = mapper.readValue(req.bodyAsBytes(), Show.class);
			Repository.getInstance().updateShow(Auth.getUser(req).getId(), show);
			return "success";
		});
		delete("/show", (req, res) -> {
			Show show = mapper.readValue(req.bodyAsBytes(), Show.class);
			Repository.getInstance().deleteShow(Auth.getUser(req).getId(), show);
			return "success";
		});
		post("/login", (req, res) -> {
			LoginRequestDto loginRequestDto = mapper.readValue(req.bodyAsBytes(), LoginRequestDto.class);
			if (loginRequestDto.getUserId().isEmpty()) {
				Auth.removeToken(res);
				return "logged out";
			}
			final User user = UserManager.INSTANCE.tryLogin(loginRequestDto);
			if (user == null) {
				throw new IllegalAccessException("Invalid login");
			}
			final String token = UserManager.INSTANCE.getTokenFromUser(user);
			Auth.setToken(res, token);
			return "logged in";
		});
	}
}

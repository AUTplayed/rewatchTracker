package codes.fepi.routing;

import codes.fepi.logic.PageHandler;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.Request;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.function.Function;

import static spark.Spark.get;

class PageRouter {

	private MustacheTemplateEngine mte;

	PageRouter() {
		mte = new MustacheTemplateEngine();
	}

	void route() {
		PageHandler handler = new PageHandler();
		page("index", handler::index);
		page("show", handler::show);
	}

	private void page(String page, Function<Request, Object> handler) {
		String route = String.format("/%s/%s.html", page, page);
		LoggerFactory.getLogger("page").info("registered page: {}", route);
		get(route, (req, res) -> mte.render(new ModelAndView(handler.apply(req), page + ".html")));
	}
}

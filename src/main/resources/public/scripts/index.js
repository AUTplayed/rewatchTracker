var $search = $("#search");

$search.onkeyup = function(ev) {
	load();
}

load();

function load() {
	ldf.load("#shows", "/shows", "?search=" + $search.value);
}
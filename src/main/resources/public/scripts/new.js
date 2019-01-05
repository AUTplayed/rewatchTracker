function add() {
	fetch("/api/show", { method: "POST", body: JSON.stringify({ name: $("#name").value, episode: 0 }) })
		.then( ldf.nav("/") ).catch(console.log);
}
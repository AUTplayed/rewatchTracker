function add() {
	fetch("/api/show", { method: "POST", body: JSON.stringify({ name: $("#name").value, episode: 0 }) })
		.then((res)=>{
			if(res.ok) {
				ldf.nav("/")
			} else {
				console.log(res.error())
			}
		}).catch(console.log);
}
$ep = $("#ep");
$("#sub").onclick = () => { $ep.value = parseInt($ep.value) - 1; epchange(); }
$("#add").onclick = () => { $ep.value = parseInt($ep.value) + 1; epchange(); }

$ep.onchange = epchange;
function epchange() {
	console.log("changed");
	fetch("/api/ep", { method: "POST", body: JSON.stringify({ name: $("#name").innerHTML, episode: parseInt($ep.value) }) }).then(console.log).catch(console.log);
}
$ep = $("#ep");
$notes = $("#notes");
$("#sub").onclick = () => { $ep.value = parseInt($ep.value) - 1; changed(); }
$("#add").onclick = () => { $ep.value = parseInt($ep.value) + 1; changed(); }

$ep.onchange = changed;
$notes.onchange = changed;

function changed() {
	console.log("changed");
	fetch("/api/show", { method: "POST", body: JSON.stringify({ name: $("#name").innerHTML, episode: parseInt($ep.value), notes: $notes.value }) }).then(console.log).catch(console.log);
}
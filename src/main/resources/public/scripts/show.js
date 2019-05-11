$ep = $("#ep");
$notes = $("#notes");
$urlPattern = $("#urlPattern");

$("#sub").onclick = () => { $ep.value = parseInt($ep.value) - 1; changed(); }
$("#add").onclick = () => { $ep.value = parseInt($ep.value) + 1; changed(); }
$ep.onchange = changed;
$notes.onchange = changed;
$urlPattern.onchange = changed;

$urlPattern.scrollLeft = $urlPattern.scrollWidth;

function changed() {
	console.log("changed");
	fetch("/api/show", { method: "POST", body: JSON.stringify(
		{
			name: $("#name").innerHTML,
			episode: parseInt($ep.value),
			notes: $notes.value,
			urlPattern: $urlPattern.value
		}
	) }).then(console.log).catch(console.log);
}
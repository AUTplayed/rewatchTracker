$ep = $("#ep");
$notes = $("#notes");
$urlPattern = $("#urlPattern");
$delete = $("#delete");

$("#sub").onclick = () => { $ep.value = parseInt($ep.value) - 1; changed(); }
$("#add").onclick = () => { $ep.value = parseInt($ep.value) + 1; changed(); }
$delete.onclick = del;

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
	)}).then(console.log).catch(console.log);
}

function del() {
	var delStep = parseInt($delete.getAttribute("delStep"));
	var text;
	switch(delStep) {
		case 1:
			text = "no undo";
			break;
		case 2:
			text = "deleted";
			actuallyDel();
			break;
		default:
			delStep = 0;
			text = "u sure?";
	}
	$delete.innerHTML = text;
	delStep++;
	$delete.setAttribute("delStep", delStep);
}

function actuallyDel() {
	fetch("/api/show", { method: "DELETE", body: JSON.stringify(
    		{
    			name: $("#name").innerHTML
    		}
    	)}).then((res)=>{
            if(res.ok) {
                ldf.nav("/")
            } else {
                console.log(res.error())
            }
        }).catch(console.log);
}
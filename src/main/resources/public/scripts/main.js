var $ = document.querySelector.bind(document);

async function swRegister() {
	if ('serviceWorker' in navigator) {
		let serviceWorker = await navigator.serviceWorker.register('/sw.js');
	}
};

swRegister();
new Darkmode().showWidget();

function updateUser() {
    ldf.load("#userName", "/username", "");
}

updateUser();

function refresh() {
   ldf.nav("/")
}

$("#login").onkeydown = event => {
    if (event.keyCode === 13) {
        var value = $("#login").value
        fetch("/api/login", { method: "POST", body: JSON.stringify(
            {
                userId: value
            }
        )}).then(res => {
            console.log(res);
            updateUser();
            $("#login").value = "";
            $("#userbox").open = false;
            refresh();
        }).catch(console.log);
    }
}
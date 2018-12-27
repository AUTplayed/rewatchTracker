window.addEventListener('beforeinstallprompt', (e) => e.prompt());
var $ = document.querySelector.bind(document);

async function swRegister() {
	if ('serviceWorker' in navigator) {
		let serviceWorker = await navigator.serviceWorker.register('/sw.js');
	}
};

swRegister();
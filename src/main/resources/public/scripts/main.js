window.addEventListener('beforeinstallprompt', (e) => e.prompt());
var $ = document.querySelector.bind(document);

async function swRegister() {
	if ('serviceWorker' in navigator) {
		try {
			let serviceWorker = await navigator.serviceWorker.register('/scripts/sw.js')
			console.log(`Service worker registered ${serviceWorker}`)
		} catch (err) {
			console.error(`Failed to register service worker: ${err}`)
		}
	}
};

swRegister();
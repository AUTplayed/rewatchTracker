var $ = document.querySelector.bind(document);

async function swRegister() {
	if ('serviceWorker' in navigator) {
		let serviceWorker = await navigator.serviceWorker.register('/sw.js');
	}
};

swRegister();
new Darkmode().showWidget();
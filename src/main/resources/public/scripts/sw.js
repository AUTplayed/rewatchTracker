const CACHE_NAME = "V1";

this.addEventListener('install', async function () {
	const cache = await caches.open(CACHE_NAME);
	cache.addAll([
		'/index.html',
		'/styles/global.css',
		'/styles/index.css',
		'/scripts/main.js',
		'/scripts/show.js'
	]);
});

this.addEventListener('fetch', event => {
	event.respondWith(async () => {
		//Try to get the cached response
		const cachedResponse = await caches.match(event.request);
		if (cachedResponse) {
			//Return the cached response if present
			return cachedResponse
		}
		//Get the network response if no cached response is present
		const netResponse = await fetch(event.request)
		return netResponse
	})
})
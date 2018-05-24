(function() {
	let tags = document.getElementsByTagName('img');
	if (tags.length <= 0) { return; }
	tags = Array.from(tags);
	let urls = '';
	tags.forEach((tag,index) => {
		urls += tag.src + ',';
		tag.addEventListener('click',event => {
			const url = event.target.src;
			imgEventHandler.onImageClick(urls,url,index);
		})
	});
	if (urls !== '') {
		urls = urls.substring(0,urls.length-1);
	}
})();
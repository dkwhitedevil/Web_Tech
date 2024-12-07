function time() {
	setInterval(updateTime, 1000);
}
function updateTime() {
	const now = new Date();
	document.getElementById('timeDisplay').textContent = now.toLocaleTimeString();
	document.getElementById('timeDisplay').style.fontSize = "3rem";
	document.getElementById('timeDisplay').style.backgroundColor = "#0066cc"; // Match phone theme color
	document.getElementById('timeDisplay').style.color = "white";
	document.getElementById('timeDisplay').style.padding = "10px";
	document.getElementById('timeDisplay').style.borderRadius = "10px";
}
const hoverParagraph = document.getElementById('hoverParagraph');
const cursorStatus = document.getElementById('cursorStatus');
hoverParagraph.onmouseenter = () => cursorStatus.textContent = 'A safe and fun phone for kids, designed with parental controls!';
hoverParagraph.onmouseleave = () => cursorStatus.textContent = 'Explore our phone collection!';
const hoverImage = document.getElementById('hoverImage');
const hoverText = document.getElementById('hoverText');
hoverImage.onmouseenter = () => {
	hoverImage.style.transform = 'translateY(10px)';
	hoverText.style.display = 'block';
};
hoverImage.onmouseleave = () => {
	hoverImage.style.transform = 'translateY(0)';
	hoverText.style.display = 'none';
};
document.body.onclick = () => {
	document.body.style.backgroundColor = "black";
}

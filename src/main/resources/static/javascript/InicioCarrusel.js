let carrusel = ()=>{

	var num = 1;
	const imgElement = document.querySelector(".carrusel-img");
	imgElement.addEventListener("animationend", (e) => {
		if (e.animationName === "fadeOut") {
			imgElement.classList.remove("fade-out"); // remove the fade-out class
			num > 2 ? (num = 1) : num++;
	
			imgElement.src = `imgs/promotion${num}.webp`;
			imgElement.classList.add("fade-in"); // add the fade-in class
		} else {
			imgElement.classList.remove("fade-in"); // remove the fade-in class
		}
	});
	setInterval(() => {
		imgElement.classList.add("fade-out"); // add the fade-out class
	}, 5000);
};
// Récupère le DOM correspondant à la barre de navigation
const burgerMenu = document.querySelector('nav');

// Récupère le DOM correspondant à la croix de fermeture
const closeBtn = burgerMenu.querySelector('a.close-btn');

// Récupère le DOM correspondant au bouton "burger"
const openBtn = document.querySelector('a.open-btn');

console.log(burgerMenu, closeBtn, openBtn);

openBtn.addEventListener('click', function (e) {
        // Supprime le comportement par défaut (changement de page)
        // lors d'un clic sur le lien
        e.preventDefault(); 

        // Récupère la liste des classes de l'élément nav
        // Et ajoute la classe "visible"
        burgerMenu.classList.add('visible');
});

closeBtn.addEventListener('click', function (e) {
        // Supprime le comportement par défaut (changement de page)
        // lors d'un clic sur le lien
        e.preventDefault(); 

        // Récupère la liste des classes de l'élément nav
        // Et retire la classe "visible"
        burgerMenu.classList.remove('visible');
});

const temp = document.getElementsByClassName("phiou");

let currentIndex = 0;
const images = [
    '2.webp',
    '1.webp',
    '3.webp',
    '4.webp',
];

function changeImage() {
    temp[0].src = images[currentIndex];
    currentIndex = (currentIndex + 1) % images.length;
}

setInterval(changeImage, 2213);

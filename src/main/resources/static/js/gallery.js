// Array of image file names
const imageNames = [
    "pic1.JPG",
    "pic2.jpg",
    "pic3.JPG",
    "pic4.jpg",
    "pic5.jpg",
    "pic6.jpg",
    "pic7.jpg",

    // Add more images here as needed
];

const imagePath = "/images/gallery/";
const imageContainer = document.getElementById("image-container");

// Dynamically add images to the container
imageNames.forEach(imageName => {
    const img = document.createElement("img");
    img.src = imagePath + imageName; // Set the image source
    img.alt = "Gallery Image"; // Alt text for accessibility
    img.classList.add("gallery-image"); // Add class for styling

    imageContainer.appendChild(img); // Append the image to the container

}); 
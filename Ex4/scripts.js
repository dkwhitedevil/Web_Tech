function changebg()
{
    document.body.style.backgroundColor = "pink";
};

function displayTime() 
{
    const currentTime = new Date();
    const hours = String(currentTime.getHours()).padStart(2, '0'); // Get hours and pad with 0 if needed
    const minutes = String(currentTime.getMinutes()).padStart(2, '0'); // Get minutes and pad with 0 if needed
    const seconds = String(currentTime.getSeconds()).padStart(2, '0'); // Get seconds and pad with 0 if needed
    const formattedTime = `${hours}:${minutes}:${seconds}`;
    document.getElementById('timeBox').value = formattedTime;
    setInterval(displayTime,1000);
};


function checkEligibility(event) 
{
    event.preventDefault(); // Prevent form submission

    // Get values from the form
    const name = document.getElementById('name').value;
    const age = parseInt(document.getElementById('age').value, 10); // Convert age to an integer

    // Check if the user is eligible to vote
    let resultMessage;
    if (age >= 18) {
        resultMessage = `${name}, you are eligible to vote!`;
    } else {
        resultMessage = `${name}, you are not eligible to vote.`;
    }

    // Display the result
    document.getElementById('result').innerText = resultMessage;

    return false; // Prevent further propagation
}

document.addEventListener("DOMContentLoaded", () => {
    const gifElement = document.getElementById('gifElement');
    const message = document.getElementById('message');

    // Mouse enter event
    gifElement.addEventListener('mouseenter', () => {
        message.textContent = "You are hovering over the GIF!";
    });

    // Mouse leave event
    gifElement.addEventListener('mouseleave', () => {
        message.textContent = "You left the GIF!";
    });
});


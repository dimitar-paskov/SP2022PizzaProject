let buttons = document.getElementsByClassName('btn-danger');
for (let i = 0; i < buttons.length; i++) {
    let button = buttons[i];
    console.log(button);
    button.addEventListener("submit",handleFormSubmission);
}

async function handleFormSubmission(event){
    event.preventDefault();

    console.log(event);
       

}
let price = document.getElementById('price');
let totalAmount = document.getElementById('totalAmount');
let priceAdjusted = document.getElementById('priceAdjusted');
let size = document.getElementById('size');
let quantity = document.getElementById('quantity');
let orderForm = document.getElementById('orderForm');


const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content;
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content;

const productId = document.getElementById('productId').textContent;
const productName = document.getElementById('productName').textContent;
const imageUrl = document.getElementById('imageUrl').src;
const category = document.getElementById('category').textContent;


if (size != null) {
	size.addEventListener('change', (event) => {

		if (event.target.value === 'Medium') {
			priceAdjusted.textContent = price.textContent;
		} else if (event.target.value === 'Large') {
			priceAdjusted.textContent = Math.round((+price.textContent + 2) * 100 / 100).toFixed(2);
		} else if (event.target.value === 'ExtraLarge') {
			priceAdjusted.textContent = Math.round((+price.textContent + 3) * 100 / 100).toFixed(2);
		}

		totalAmount.textContent = Math.round((+priceAdjusted.textContent * quantity.value) * 100 / 100).toFixed(2);
		totalAmount.textContent = totalAmount.textContent + ' BGN';

	});

}

quantity.addEventListener('change', (event) => {

	totalAmount.textContent = Math.round((+priceAdjusted.textContent * quantity.value) * 100 / 100).toFixed(2);
	totalAmount.textContent = totalAmount.textContent + ' BGN';

});

orderForm.addEventListener("submit",handleFormSubmission);


async function handleFormSubmission(event){
    event.preventDefault();

    fetch(`http://localhost:8080/order/${productId}`,{
        method: "POST",        
        headers:{
			'redirect': 'follow',
            'Content-Type': 'application/json',
            'Accepts': 'application/json',
             [csrfHeaderName]:csrfHeaderValue

        },
        body: JSON.stringify({
            	 id:`${productId}`,
				 name:`${productName}`,
				 imageUrl:`${imageUrl}`,
				 price:`${price.textContent}`,
	 			 category:`${category}`,
				 quantity: `${quantity.value}`,
				 totalPrice:`${totalAmount.textContent.split(/(\s+)/)[0]}`,
				 'size':`${size === null ? null : size.value}`
        })
    }).then(res => {
	 if (res.redirected) {
            window.location.href = res.url;
        }	
    });
       

}



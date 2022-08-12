const csrfHeaderName = document.head.querySelector('[name=_csrf_header]').content;
const csrfHeaderValue = document.head.querySelector('[name=_csrf]').content;

const OrderList = document.querySelectorAll(`[id^="orderId"]`);

const totalPriceList = document.querySelectorAll(`[id^="totalPriceId"]`);

let orderForm = document.getElementById('orderForm');
let totalPriceForAllOrders = document.getElementById('totalPriceForAllOrders');


let totalPriceSum = 0;
for (let i = 0; i < totalPriceList.length ; i++) {
	
    totalPriceSum += +totalPriceList[i].textContent; 
}
totalPriceForAllOrders.textContent =  Math.round((totalPriceSum) * 100/100).toFixed(2);



let arr=[];
for (let i = 0; i < OrderList.length ; i++) {
    arr.push(OrderList[i].textContent);
}

orderForm.addEventListener("submit",handleFormSubmission);


async function handleFormSubmission(event){
    event.preventDefault();

    fetch(`http://localhost:8080/order/activateOrder/`,{
        method: "POST",        
        headers:{
			 'redirect': 'follow',
            'Content-Type': 'application/json',
            'Accepts': 'application/json',
             [csrfHeaderName]:csrfHeaderValue

        },
        body: JSON.stringify(arr)
    }).then(res => {
	 if (res.redirected) {
            window.location.href = res.url;
        }	
    });
       

}




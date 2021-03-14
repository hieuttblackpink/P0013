
function calPrice (x)
{
    var price = document.getElementById("price" + x).value;
    var amountBefore = document.getElementById("amountBefore" + x).value;
    var amount = document.getElementById("amount" + x).value;
    var total = price * amount;
    var addMore = document.getElementById("addMore" + x);
    var reduce = document.getElementById("reduce" + x);
    
    if (amount === 0)
    {
        addMore.style.display = "none";
        reduce.style.display = "none";
    }
    else if (amount > amountBefore)
    {
        addMore.style.display = "block";
        reduce.style.display = "none";
    }
    else if (amount < amountBefore)
    {
        addMore.style.display = "none";
        reduce.style.display = "block";
    }
    
    document.getElementById("priceShow" + x).innerHTML = total + " $";
    document.getElementById("total" + x).value = total;
    amountBefore = amount;
    
}

function confirmRemove ()
{
    return confirm('DO YOU WANT TO REMOVE THIS PRODUCT FROM CART?');
};



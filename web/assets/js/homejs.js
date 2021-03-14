
function calPrice (x)
{
    var price = document.getElementById("price" + x).value;
    var amount = document.getElementById("amount" + x).value;
    var total = price * amount;
    
    document.getElementById("priceShow" + x).innerHTML = total + " $";
    document.getElementById("total" + x).value = total;
}

function checkMinPrice ()
{
    var minPrice = document.getElementById("minPrice").value;
    var maxPrice = document.getElementById("maxPrice").value;
    
    if (minPrice > maxPrice)
    {
        document.getElementById("minPrice").value = maxPrice;
    }
}

function checkMaxPrice ()
{
    var minPrice = document.getElementById("minPrice").value;
    var maxPrice = document.getElementById("maxPrice").value;
    
    if (maxPrice < minPrice)
    {
        document.getElementById("maxPrice").value = minPrice;
    }
}


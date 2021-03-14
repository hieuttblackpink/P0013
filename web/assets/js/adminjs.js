
var loadFile = function(event)
{
    var image = document.getElementById('output');
    image.src = URL.createObjectURL(event.target.files[0]);
};

var loadFile2 = function(event, x)
{
    var image = document.getElementById('image' + x);
    image.src = URL.createObjectURL(event.target.files[0]);
    document.getElementById("changeImg" + x).value = "CHANGED";
};

function categorySelected ()
{
    const category = document.getElementById("categorySelect").value;
    
    document.getElementById("categoryID").value = category;
};

function categorySelected2 (x)
{
    const category = document.getElementById('categorySelect' + x).value;
    
    document.getElementById('categoryID' + x).value = category;
};

function confirmRemove (x)
{
    var status = document.getElementById("statusChoose" + x).value;
    
    if (status === "Inactive")
    {
        return confirm('DO YOU WANT TO REMOVE THIS PRODUCT?');
    }
    else
    {
        return true;
    }
};


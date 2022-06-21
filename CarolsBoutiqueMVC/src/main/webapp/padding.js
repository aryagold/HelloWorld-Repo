// let size = document.getElementById("size").value;
let productId = document.getElementById("productID");
let result = document.getElementById("result");

//let sub = id.substring(3, id.length + 1);



// console.log(size);


function update() {
    var id = productId.value;
    var prefix = id.substring(3, id.length + 1);
    
    var select = document.getElementById('size');
    var option = select.options[select.selectedIndex];
    const paddedNo = String(prefix + option.value).padStart(5, '0'); 
    result.value = paddedNo;
}

// let size = document.getElementById("size").value;
let id = document.getElementById("productID").value;
let result = document.getElementById("result");

let sub = id.substring(3, id.length + 1);



// console.log(size);






function update() {
    
    var select = document.getElementById('size');
    var option = select.options[select.selectedIndex];
    const paddedNo = String(sub + option.value).padStart(5, '0'); 
    result.value = paddedNo;
}

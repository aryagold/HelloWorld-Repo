function onScanSuccess(decodedText, decodedResult) {
    
    k = decodedText;
    console.log(k);
    document.getElementById('barcode').value = k;
    

    console.log(`Code scanned = ${decodedText}`, decodedResult);
}
var html5QrcodeScanner = new Html5QrcodeScanner(
	"qr-reader", { fps: 10, qrbox: 250 });
html5QrcodeScanner.render(onScanSuccess);
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Replenish Stock Home</title>
    </head>
    <body style="background-color:#f5f5dc;">
        <h1>Capture Stock</h1>
        <form action="InventoryServlet" method ="post">
            <label>Bar code: <input type="text" name="barcode" ></label><br>
            <input list="quantities" name="quantity">
            <datalist id="quantity">
                
                <option value="1"></option>  
                <option value="2"></option>  
                <option value="3"></option>  
                <option value="4"></option>  
                <option value="5"></option>  
                <option value="6"></option>  
                <option value="7"></option>  
                <option value="8"></option>  
                <option value="9"></option>  
                <option value="10"></option>  
                <option value="11"></option>  
                
            </datalist>
            <input type="submit" name="submit" value="captureinventory" >
        </form> 
    </body>
</html>

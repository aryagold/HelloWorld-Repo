package za.co.vzap.Communication.Model;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class CommunicationDto {
    public int type;
    public String emailAddressTo;
    public Map<String, String> parameters = new HashMap<>();
    
    public CommunicationDto() {
        parameters.put("date" , "2022-06-08");
        parameters.put("saleId", "SL001");
        parameters.put("productName", "Jean");
        parameters.put("price", "R1000");
        parameters.put("branchId", "BR001");
    }
    
}

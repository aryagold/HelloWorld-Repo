package za.co.vzap.Common.Resource;

import javax.ws.rs.HeaderParam;

public class ControllerBase {
    @HeaderParam("userId")
    protected String userId;  
    
}

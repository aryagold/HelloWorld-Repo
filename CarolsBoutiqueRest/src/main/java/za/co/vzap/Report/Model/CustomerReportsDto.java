package za.co.vzap.Report.Model;

import java.util.ArrayList;
import java.util.List;

public class CustomerReportsDto extends ReportBase {
    
    public String date;
    public int resultAmount;
    
    public String branchName;
    public List<ItemAmount> storeRatings = new ArrayList<>();
}

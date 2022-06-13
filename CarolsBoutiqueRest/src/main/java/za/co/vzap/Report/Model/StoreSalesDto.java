package za.co.vzap.Report.Model;

import java.util.ArrayList;
import java.util.List;

public class StoreSalesDto extends ReportBase {
    public String branchName;
    public String date;
    public double dailyTarget;
    public List<SaleSummaryDto> sales = new ArrayList<>();
}



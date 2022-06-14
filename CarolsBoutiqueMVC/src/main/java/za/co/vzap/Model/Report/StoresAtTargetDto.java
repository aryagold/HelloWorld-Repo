package za.co.vzap.Model.Report;

import java.util.ArrayList;
import java.util.List;

public class StoresAtTargetDto extends ReportBase {
    public String branchName;
    public double monthlyTarget;
    public List<ItemAmount> storeSales = new ArrayList<>();
}

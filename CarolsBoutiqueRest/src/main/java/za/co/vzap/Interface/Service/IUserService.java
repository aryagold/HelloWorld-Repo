package za.co.vzap.Interface.Service;

import java.util.List;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.User.Model.User;

public interface IUserService {
    boolean updateToTeller(String userId);
    
    String addBranch(Branch branch);
    
    User login(User user);
    
    List<Branch> getAllBranches();
}

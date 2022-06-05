package za.co.vzap.Interface.Service;

import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.User.Model.User;

public interface IUserService {
    boolean updateToTeller(String userId);
    
    String addBranch(Branch branch);
    
    User login(User user);
}

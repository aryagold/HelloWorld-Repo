package za.co.vzap.Interface.Service;

import za.co.vzap.Model.Branch.Branch;
import za.co.vzap.Model.User.User;

public interface IUserService {
    boolean updateToTeller(String userId);
    
    String addBranch(Branch branch);
    
    User login(User user);
}

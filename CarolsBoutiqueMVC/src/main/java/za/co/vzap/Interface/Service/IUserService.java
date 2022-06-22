package za.co.vzap.Interface.Service;

import java.util.List;
import za.co.vzap.Model.Branch.Branch;
import za.co.vzap.Model.User.User;
import za.co.vzap.Model.User.UserDto;

public interface IUserService {
    boolean updateToTeller(String userId);

    String addBranch(Branch branch);

    UserDto login(User user);

    List<Branch> getAllBranches();
}

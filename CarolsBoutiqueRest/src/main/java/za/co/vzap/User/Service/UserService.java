package za.co.vzap.User.Service;

import java.util.List;
import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.Interface.Service.IUserService;
import za.co.vzap.User.Model.RoleEnum;
import za.co.vzap.User.Model.User;
import za.co.vzap.User.Repository.UserRepository;

public class UserService implements IUserService {

    private IRepository userRepository;
    private IRepository branchRepository;

    public UserService(IRepository userRepository, IRepository branchRepository) {
        userRepository = new UserRepository();
        branchRepository = new BranchRepository();
    }
   
    
    @Override
    public boolean updateToTeller(String userId) { 
        List<User> users = (List<User>) userRepository.getById(userId);    
        
        for(User u: users) {
            u.setRole(RoleEnum.ofStatusCode(1));
            
            userRepository.update(u);
            
            if (u.getRole().getValue() == 1) {
                return true;
            }
        
        }
        
        return false;
    }

    @Override
    public String addBranch(Branch branch) {
        return branchRepository.add2(branch);
    }

    @Override
    public User login(User user) {
        List<User> users = (List<User>) userRepository.getById(user.userId);
            for(User u : users) {
                
                if (u.getPassword().equals(user.getPassword())) {
                    return u;
                }
                
            }
            
            return null;
    }   
}

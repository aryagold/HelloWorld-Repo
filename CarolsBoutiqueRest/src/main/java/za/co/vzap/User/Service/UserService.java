package za.co.vzap.User.Service;

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
        this.userRepository = new UserRepository();
        this.branchRepository = new BranchRepository();
    }
   
    
    @Override
    public boolean updateToTeller(String userId) { 
        User user = (User) userRepository.getById(userId);    
        
        user.setRole(RoleEnum.valueOf(1));
        
        userRepository.update (user);
            
        if (user.getRole().getValue() == 1) {
            return true;
        }
        
        return false;
    }

    @Override
    public String addBranch(Branch branch) {
        return branchRepository.add2(branch);
    }

    @Override
    public User login(User user) {
        User userRet = (User) userRepository.getById(user.userId);
        
        if (userRet.getPassword().equals(user.getPassword())) {
            return userRet;
        }
                
        return null;
    }   
}

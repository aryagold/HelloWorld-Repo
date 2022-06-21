package za.co.vzap.User.Service;

import za.co.vzap.Branch.Model.Branch;
import za.co.vzap.Branch.Repository.BranchRepository;
import za.co.vzap.Interface.Repository.IRepository;
import za.co.vzap.User.Model.User;
import za.co.vzap.User.Model.UserDto;

public class UserMapper {
    private static IRepository branchRepository = new BranchRepository();
    
    public static UserDto toUserDto(User user) {
        UserDto dto = new UserDto();
        
        dto.userId = user.userId;
        dto.branchId = user.getBranchId();
        dto.email = user.getEmail();
        dto.name = user.getName();
        dto.role = user.getRole();
        
        Branch branch = (Branch) branchRepository.getById(user.getBranchId());
        dto.branchName = branch.getName();
        
        return dto;
    }
    
    public static User toUser(UserDto dto) {
        User user = new User();
        
        user.setUserId(dto.userId);
        user.setEmail(dto.email);
        user.setBranchId(dto.branchId);
        user.setName(dto.name);
        user.setRole(dto.role);
        
        return user;
    }
}

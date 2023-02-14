package ndtq.service.users;


import ndtq.model.Users;
import ndtq.service.IGeneralService;

import java.util.List;
import java.util.Optional;


public interface IUserService extends IGeneralService<Users> {
    void updatePasswordByID(String newPass, Long id);
    Iterable<Users> findAllByNameContaining(String name);
    void updateUser(Long id, String name, String address, String email, String phone);
    void updatePass(Long id, String password);
    Optional<Users> findUserByName(String name);
    Optional<Users> findUserByUsername(String username);
    //TODO tạo ràng buộc trực tiếp ở Database xử lý cho trường hợp này để không cần phải viết thêm funtion check nữa
    // user.name = unique
    Boolean checkUsername(String name);

    List<String> findAllUsername();
}

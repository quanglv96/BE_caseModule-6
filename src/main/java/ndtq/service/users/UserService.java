package ndtq.service.users;


import ndtq.model.Users;
import ndtq.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public Iterable<Users> findAll() {
        return iUserRepository.findAll();
    }

    @Override
    public Optional<Users> findById(Long id) {
        return iUserRepository.findById(id);
    }

    @Override
    public Users save(Users users) {
        return iUserRepository.save(users);
    }

    @Override
    public void remove(Long id) {
        iUserRepository.deleteById(id);
    }

    @Override
    public void updatePasswordByID(String newPass, Long id) {
        iUserRepository.updatePasswordByID(newPass,id);
    }

    @Override
    public void updateUser(Long id, String name, String address, String email, String phone, String avatar) {
        iUserRepository.updateUser(id, name, address, email, phone, avatar);
    }

    @Override
    public void updatePass(Long id, String password) {
        iUserRepository.updatePass(id, password);
    }

    @Override
    public Iterable<Users> findAllByNameContaining(String name) {
        return iUserRepository.findAllByNameContaining(name);
    }

    @Override
    public Optional<Users> findUserByName(String name) {
        return iUserRepository.findUserByName(name);
    }

    @Override
    public Optional<Users> findUserByUsername(String username) {
        return iUserRepository.findUserByUsername(username);
    }

    @Override
    public Boolean checkUsername(String name) {
        if (iUserRepository.countUsersByUsername(name) > 0) {
            return true;
        }
       return false;
    }

    @Override
    public List<String> findAllUsername() {
        return iUserRepository.findAllUsername();
    }
}

package ndtq.repository;

import ndtq.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IUserRepository extends JpaRepository<Users, Long> {
    @Modifying
    @Query(value = "UPDATE `dbmodule6`.`users` SET `password` = ?1 WHERE (`id` = ?2);", nativeQuery = true)
    void updatePasswordByID(String newPass, Long id);
    @Modifying
    @Query(value = "update users set  name = :name, address = :address, email = :email, phone = :phone where (id = :id)", nativeQuery = true)
    void updateUser(Long id,String name, String address, String email, String phone);
    @Modifying
    @Query(value = "update users set password = :password where (id = :id)", nativeQuery = true)
    void updatePass(Long id, String password);

    Iterable<Users> findAllByNameContaining(String name);

    Optional<Users> findUserByName(String name);

    Optional<Users> findUserByUsername(String username);

    int countUsersByUsername(String name);

    @Query(value = "select username from dbmodule6.users", nativeQuery = true)
    List<String> findAllUsername();
    @Query(value = "select count(*) from dbmodule6.songs where id_users=?1", nativeQuery = true)
    int countSongByUser(Long id);
    @Query(value = "select count(*) from dbmodule6.playlist where id_users=?1", nativeQuery = true)
    int countPlaylistByUser(Long id);
}

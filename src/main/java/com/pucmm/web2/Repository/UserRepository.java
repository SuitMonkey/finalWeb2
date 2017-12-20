
package com.pucmm.web2.Repository;

import com.pucmm.web2.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, String> {

    User findByEmail(String email);



    @Query("select u from User u where u.email = :email and u.password = :password")
    User findUserAccountWithUsernameAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("select u from User u where u.department = :department")
    List<User> findByDepartment(@Param("department") String department);

    @Query("select u from User u where u.country = :country")
    List<User> findByCountry(@Param("country") String country);

    @Query("select u from User u where u.city = :city and u.country = :country")
    List<User> findByCity(@Param("city") String city, @Param("country") String coutry);
}

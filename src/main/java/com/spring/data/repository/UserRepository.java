package com.spring.data.repository;

import com.spring.data.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<Users,Long>{
    List<Users> findByEmail(String email);

}

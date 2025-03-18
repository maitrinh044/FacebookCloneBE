package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

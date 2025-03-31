package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Reaction, Long> {

}

package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, String> {

}

package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}

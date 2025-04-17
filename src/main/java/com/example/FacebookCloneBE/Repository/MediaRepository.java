package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {


}

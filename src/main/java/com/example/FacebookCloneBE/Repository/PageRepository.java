package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

}

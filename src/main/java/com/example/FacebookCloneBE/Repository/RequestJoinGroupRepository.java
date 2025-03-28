package com.example.FacebookCloneBE.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FacebookCloneBE.Model.RequestJoinGroup;

@Repository
public interface RequestJoinGroupRepository extends JpaRepository<RequestJoinGroup, Long> {

}

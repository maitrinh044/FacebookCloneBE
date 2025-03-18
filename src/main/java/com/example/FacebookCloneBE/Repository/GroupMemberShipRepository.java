package com.example.FacebookCloneBE.Repository;

import com.example.FacebookCloneBE.Model.GroupMemberShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberShipRepository extends JpaRepository<GroupMemberShip, Long> {

}

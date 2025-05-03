package com.example.FacebookCloneBE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FacebookCloneBE.Model.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

}

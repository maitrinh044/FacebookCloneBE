package com.example.FacebookCloneBE.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.DTO.BankDTO.BankDTO;
import com.example.FacebookCloneBE.Model.Bank;

@Service
public interface BankService {
    public List<BankDTO> getAllBank();

    public Optional<BankDTO> getById(Long id);

    public Optional<BankDTO> addBank(BankDTO bankDTO);

    public Optional<BankDTO> controlActiveStatus(Long id);

    // public Optional<BankDTO>
}

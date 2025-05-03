package com.example.FacebookCloneBE.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.DTO.BankAccountDTO.BankAccountDTO;
import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Mapper.BankAccountMapper;
import com.example.FacebookCloneBE.Model.Bank;
import com.example.FacebookCloneBE.Model.BankAccount;
import com.example.FacebookCloneBE.Model.User;
import com.example.FacebookCloneBE.Repository.BankAccountRepository;
import com.example.FacebookCloneBE.Repository.BankRepository;
import com.example.FacebookCloneBE.Repository.UserRepository;
import com.example.FacebookCloneBE.Service.BankAccountService;

import io.jsonwebtoken.lang.Collections;

@Service
public class BankAccountServiceImlp implements BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<BankAccountDTO> getByBank(Long bankId) {
        try {
            Optional<Bank> bank = bankRepository.findById(bankId);
            List<BankAccount> list = bankAccountRepository.findByBankID(bank.get());
            return list.stream().map(BankAccountMapper::toDTO).toList();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<BankAccountDTO> getById(Long id) {
        try {
            Optional<BankAccount> bankaccountDTO = bankAccountRepository.findById(id);
            return Optional.of(BankAccountMapper.toDTO(bankaccountDTO.get()));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<BankAccountDTO> getAllBankAccount() {
        try {
            List<BankAccount> list = bankAccountRepository.findAll();
            return list.stream().map(BankAccountMapper::toDTO).toList();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<BankAccountDTO> controlActiveStatus(Long id) {
        try {
            Optional<BankAccount> bankAccount = bankAccountRepository.findById(id);
            if (bankAccount.isPresent()) {
                BankAccountDTO bankAccountDTO = BankAccountMapper.toDTO(bankAccount.get());
                // Đảo ngược trạng thái hoạt động
                bankAccountDTO.setActiveStatus(
                        bankAccountDTO.getActiveStatus().equals(ActiveEnum.ACTIVE) ? ActiveEnum.INACTIVE
                                : ActiveEnum.ACTIVE);

                // Lưu lại ngân hàng với trạng thái mới
                BankAccount updated = bankAccountRepository.save(BankAccountMapper.toEntityPUT(bankAccountDTO));
                return Optional.of(BankAccountMapper.toDTO(updated));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log lỗi
        }
        return Optional.empty(); // Đảm bảo trả về Optional.empty() nếu không tìm thấy hoặc có lỗi
    }

    @Override
    public Optional<BankAccountDTO> addBankAccount(BankAccountDTO bankAccountDTO) {
        try {
            BankAccount bankAccount = BankAccountMapper.toEntityPOST(bankAccountDTO);
            BankAccount saved = bankAccountRepository.save(bankAccount);
            return Optional.of(BankAccountMapper.toDTO(saved));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<BankAccountDTO> updateBankAccount(BankAccountDTO bankAccountDTO) {
        try {
            Optional<BankAccount> existingBA = bankAccountRepository.findById(bankAccountDTO.getId());
            if (existingBA.isPresent()) {
                BankAccount bankAccount = BankAccountMapper.toEntityPUT(bankAccountDTO);
                BankAccount updated = bankAccountRepository.save(bankAccount);
                return Optional.of(BankAccountMapper.toDTO(updated));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<BankAccountDTO> getByUser(Long userId) {
        try {
            Optional<User> user = userRepository.findById(userId);
            List<BankAccount> list = bankAccountRepository.findByUserID(user.get());
            return list.stream().map(BankAccountMapper::toDTO).toList();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

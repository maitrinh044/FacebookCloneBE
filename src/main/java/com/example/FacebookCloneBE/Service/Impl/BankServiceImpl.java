package com.example.FacebookCloneBE.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.DTO.BankDTO.BankDTO;
import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Mapper.BankMapper;
import com.example.FacebookCloneBE.Model.Bank;
import com.example.FacebookCloneBE.Repository.BankRepository;
import com.example.FacebookCloneBE.Service.BankService;

import io.jsonwebtoken.lang.Collections;

@Service
public class BankServiceImpl implements BankService {
    @Autowired
    private BankRepository bankRepository;

    @Override
    public List<BankDTO> getAllBank() {
        try {
            List<Bank> list = bankRepository.findAll();
            return list.stream().map(BankMapper::toDTO).toList();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<BankDTO> getById(Long id) {
        try {
            Optional<Bank> bank = bankRepository.findById(id);
            return Optional.of(BankMapper.toDTO(bank.get()));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<BankDTO> addBank(BankDTO bankDTO) {
        try {
            Bank addBank = bankRepository.save(BankMapper.toEntityPOST(bankDTO));
            return Optional.of(BankMapper.toDTO(addBank));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // mai lam tiep
    @Override
    public Optional<BankDTO> controlActiveStatus(Long id) {
        try {
            Optional<Bank> bank = bankRepository.findById(id);
            if (bank.isPresent()) {
                BankDTO bankDTO = BankMapper.toDTO(bank.get());
                // Đảo ngược trạng thái hoạt động
                bankDTO.setActiveStatus(
                        bankDTO.getActiveStatus().equals(ActiveEnum.ACTIVE) ? ActiveEnum.INACTIVE : ActiveEnum.ACTIVE);

                // Lưu lại ngân hàng với trạng thái mới
                Bank savedBank = bankRepository.save(BankMapper.toEntityPUT(bankDTO));
                return Optional.of(BankMapper.toDTO(savedBank));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log lỗi
        }
        return Optional.empty(); // Trả về Optional.empty() nếu không tìm thấy ngân hàng hoặc có lỗi
    }
}

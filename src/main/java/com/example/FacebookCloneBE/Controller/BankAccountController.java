package com.example.FacebookCloneBE.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FacebookCloneBE.DTO.BankAccountDTO.BankAccountDTO;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.BankAccountService;
import com.example.FacebookCloneBE.Service.BankService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/bankaccounts")
public class BankAccountController {
    @Autowired
    private BankAccountService bankAccountService;
    private ResponseData responseData = new ResponseData();

    @GetMapping("/getByBank/{id}")
    public ResponseEntity<ResponseData> getByBank(@PathVariable Long id) {
        try {
            List<BankAccountDTO> list = bankAccountService.getByBank(id);
            if (list.iterator().hasNext()) {
                responseData.setData(list);
                responseData.setMessage("Get bankaccount by bankId success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Not found bank!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            // TODO: handle exception
            responseData.setMessage("Error when get bankaccount by bankId!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getById(@PathVariable Long id) {
        try {
            Optional<BankAccountDTO> bankAccountDTO = bankAccountService.getById(id);
            if (bankAccountDTO.isPresent()) {
                responseData.setData(bankAccountDTO.get());
                responseData.setMessage("Get bankaccount by ID success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Bank account not found!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setMessage("Error when getting bank account by ID!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseData> getAllBankAccounts() {
        try {
            List<BankAccountDTO> list = bankAccountService.getAllBankAccount();
            responseData.setData(list);
            responseData.setMessage("Get all bank accounts success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setMessage("Error when getting all bank accounts!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getByUser/{userId}")
    public ResponseEntity<ResponseData> getByUser(@PathVariable Long userId) {
        try {
            List<BankAccountDTO> list = bankAccountService.getByUser(userId);
            if (list.iterator().hasNext()) {
                responseData.setData(list);
                responseData.setMessage("Get bankaccounts by user success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Not found user");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            // TODO: handle exception
            responseData.setMessage("Error when get bankaccounts by user!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PutMapping("/controlActiveStatus/{id}")
    public ResponseEntity<ResponseData> controlActiveStatus(@PathVariable Long id) {
        try {
            Optional<BankAccountDTO> updatedAccount = bankAccountService.controlActiveStatus(id);
            if (updatedAccount.isPresent()) {
                responseData.setData(updatedAccount.get());
                responseData.setMessage("Control active status success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Bank account not found or update failed!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            responseData.setMessage("Error when controlling active status!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseData> addBankAccount(@RequestBody BankAccountDTO bankAccountDTO) {
        try {
            Optional<BankAccountDTO> newAccount = bankAccountService.addBankAccount(bankAccountDTO);
            if (newAccount.isPresent()) {
                responseData.setData(newAccount.get());
                responseData.setMessage("Add bank account success!");
                responseData.setStatusCode(201);
                return ResponseEntity.status(201).body(responseData);
            } else {
                responseData.setMessage("Failed to add bank account!");
                responseData.setStatusCode(400);
                return ResponseEntity.status(400).body(responseData);
            }
        } catch (Exception e) {
            responseData.setMessage("Error when adding bank account!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseData> updateBankAccount(@RequestBody BankAccountDTO bankAccountDTO) {
        try {
            Optional<BankAccountDTO> updatedAccount = bankAccountService.updateBankAccount(bankAccountDTO);
            if (updatedAccount.isPresent()) {
                responseData.setData(updatedAccount.get());
                responseData.setMessage("Update bank account success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Failed to update bank account!");
                responseData.setStatusCode(400);
                return ResponseEntity.status(400).body(responseData);
            }
        } catch (Exception e) {
            responseData.setMessage("Error when updating bank account!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }
}

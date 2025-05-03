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

import com.example.FacebookCloneBE.DTO.BankDTO.BankDTO;
import com.example.FacebookCloneBE.Reponse.ResponseData;
import com.example.FacebookCloneBE.Service.BankService;

import io.jsonwebtoken.security.Jwks.OP;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/banks")
public class BankController {
    @Autowired
    private BankService bankService;
    private ResponseData responseData = new ResponseData();

    @GetMapping("/getAllBanks")
    public ResponseEntity<ResponseData> getAllBanks() {
        try {
            List<BankDTO> list = bankService.getAllBank();
            responseData.setData(list);
            responseData.setMessage("Get all banks success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            // TODO: handle exception
            responseData.setMessage("Error when get all banks!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseData> getById(@PathVariable Long id) {
        try {
            Optional<BankDTO> bankDTO = bankService.getById(id);
            responseData.setData(bankDTO.get());
            responseData.setMessage("Get bank by id success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            // TODO: handle exception
            responseData.setMessage("Error when get by id!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PostMapping("/addBank")
    public ResponseEntity<ResponseData> addBank(@RequestBody BankDTO bankDTO) {
        try {
            Optional<BankDTO> addBank = bankService.addBank(bankDTO);
            responseData.setData(addBank.get());
            responseData.setMessage("Add new bank success!");
            responseData.setStatusCode(200);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            // TODO: handle exception
            responseData.setMessage("Error when add new bank!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }

    @PutMapping("/controlActiveStatus/{id}")
    public ResponseEntity<ResponseData> controlActiveStatus(@PathVariable Long id) {
        try {
            Optional<BankDTO> bankDTO = bankService.controlActiveStatus(id);
            if (bankDTO.isPresent()) {
                responseData.setData(bankDTO.get());
                responseData.setMessage("Control activeStatus by id success!");
                responseData.setStatusCode(200);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.setMessage("Not found bank by id!");
                responseData.setStatusCode(404);
                return ResponseEntity.status(404).body(responseData);
            }
        } catch (Exception e) {
            // TODO: handle exception
            responseData.setMessage("Error when control activeStatus bank!");
            responseData.setStatusCode(500);
            return ResponseEntity.status(500).body(responseData);
        }
    }
}

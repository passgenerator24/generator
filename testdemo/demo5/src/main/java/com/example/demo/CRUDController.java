package com.example.demo;

import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CRUDController {
    public CRUDService crudService;

    public CRUDController(CRUDService crudService) {
        this.crudService = crudService;
    }

    @PostMapping("/create")
    public String createCRUD(@RequestBody CRUD crud) throws InterruptedException, ExecutionException {
        return crudService.createCRUD(crud);
    }

    @GetMapping("/get")
    public CRUD getCRUD(@RequestParam String credential_id) throws InterruptedException, ExecutionException {
        return crudService.getCRUD(credential_id);
    }

    // @PutMapping("/update")
    // public String updateCRUD(@RequestBody CRUD crud) throws InterruptedException, ExecutionException {
    //     return crudService.updateCRUD(crud);
    // }

    @PutMapping("/delete")  // Should be @DeleteMapping
    public String deleteCRUD(@RequestParam String credential_id) throws InterruptedException, ExecutionException {
        return crudService.deleteCRUD(credential_id);
    }
}


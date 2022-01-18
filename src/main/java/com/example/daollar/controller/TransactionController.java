package com.example.daollar.controller;

import com.example.daollar.model.Children;
import com.example.daollar.model.Parent;
import com.example.daollar.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    /**
     * Create all transactions to parent table
     * @param List<Parent> parents: JSON Array
     * @return String
     */
    @PostMapping("/parents")
    public ResponseEntity<String> saveTransaction(@RequestBody final List<Parent> parents) {
        transactionService.createParent(parents);
        return ResponseEntity.ok("Parents successfully created");
    }

    /**
     * Task 1: get all parents by pagination, sort by id
     * @return Page<Parent> list
     */
    @GetMapping("/parents")
    public ResponseEntity<Page<Parent>> getTransactions(@PageableDefault(size = 2, direction = Sort.Direction.ASC, sort = {"id"}) Pageable page) {
        Page<Parent> parents = transactionService.getParents(page);
        return ResponseEntity.ok(parents);
    }

    /**
     * Create all installament to children table
     * @param List<Children> children: JSON Array
     * @return String
     */
    @PostMapping("/children")
    public ResponseEntity<String> saveChildren(@RequestBody final List<Children> children) {
        transactionService.createChildren(children);
        return ResponseEntity.ok("Children successfully created");
    }

    /**
     * Task 2: get all children by parentId
     * @return List<Children> (no pagination need)
     */
    @GetMapping("/children/{parentId}")
    public ResponseEntity<List<Children>> getChildrenById(@PathVariable("parentId") Integer parentId) {
        List<Children> children = transactionService.getChildren(parentId);
        return ResponseEntity.ok(children);
    }
}

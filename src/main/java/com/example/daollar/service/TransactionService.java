package com.example.daollar.service;

import com.example.daollar.model.Children;
import com.example.daollar.model.Parent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {
    // Handle Parent model
    void createParent(List<Parent> parents);

    Parent getParent(Integer parentId);

    Page<Parent> getParents(Pageable page);

    // Handle Children model
    void createChildren(List<Children> children);

    List<Children> getChildren(Integer parentId);
}

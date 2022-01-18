package com.example.daollar.service.impl;

import com.example.daollar.dao.ChildrenRepository;
import com.example.daollar.dao.ParentRepository;
import com.example.daollar.model.Children;
import com.example.daollar.model.Parent;
import com.example.daollar.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private ParentRepository parentRepo;

    @Autowired
    private ChildrenRepository childRepo;

    @Override
    public void createParent(List<Parent> parents) {
        this.parentRepo.saveAll(parents);
    }

    @Override
    public Page<Parent> getParents(Pageable page) {
        Page<Parent> parents = this.parentRepo.findAll(page);
        for (Parent parent: parents) {
            Double totalPaidAmount = parent.getChildrenList().stream().map(o->o.getPaidAmount()).reduce(0.0, (a, b) -> a + b);
            parent.setTotalPaidAmount(totalPaidAmount);
        }
        return parents;
    }

    @Override
    public Parent getParent(Integer parentId) {
        return this.parentRepo.findById(parentId).orElse(null);
    }

    @Override
    public void createChildren(List<Children> children) {
        for (Children child: children) {
            Integer parentId = child.getParentId();
            Parent parent = this.getParent(parentId);
            child.setParent(parent);
        }
        this.childRepo.saveAll(children);
    }

    @Override
    public List<Children> getChildren(Integer parentId) {
        Parent parent = this.getParent(parentId);
        List<Children> children = this.childRepo.findByParent(parent);
        for (Children child: children) {
            child.setTotalAmount(parent.getTotalAmount());
            child.setSender(parent.getSender());
            child.setReceiver(parent.getReceiver());
        }
        return children;
    }
}

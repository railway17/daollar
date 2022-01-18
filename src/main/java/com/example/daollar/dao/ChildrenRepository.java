package com.example.daollar.dao;

import com.example.daollar.model.Children;
import com.example.daollar.model.Parent;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ChildrenRepository  extends PagingAndSortingRepository<Children, Integer> {
    List<Children> findByParent(Parent parent);
}

package com.rendezvous.service;

import com.rendezvous.entity.CompCategory;
import com.rendezvous.repository.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CompCategory> getAllCategories() {
        return categoryRepository.findAll();
    }
}

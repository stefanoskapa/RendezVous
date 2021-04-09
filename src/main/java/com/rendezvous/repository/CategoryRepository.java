package com.rendezvous.repository;

import com.rendezvous.entity.CompCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CompCategory, Integer> {

    List<CompCategory> findAll();
}

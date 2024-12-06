package com.codemaster.io.repository;

import com.codemaster.io.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p join p.tags t where t.id = :tagId")
    List<Product> findProductsByTag(String tagId);

    @Query("select p.id from Product p join p.tags t where t.id = :tagId")
    List<String> findProducIdsByTag(String tagId);

    @Query("select p from Product p join p.tags t where t.id in :tagIds")
    List<Product> findProductsByTag(List<String> tagIds);
}

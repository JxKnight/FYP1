package com.example.fyp.Repository;

import com.example.fyp.Model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface StorageRepository  extends JpaRepository<Storage, Integer> {
    Optional<Storage> findByProductsId(String productsId);
}

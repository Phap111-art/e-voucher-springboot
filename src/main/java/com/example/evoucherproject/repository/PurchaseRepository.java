package com.example.evoucherproject.repository;

import com.example.evoucherproject.model.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {
    // đếm quantity khách khi mua sp
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Purchase p WHERE p.customer.customerId = :customerId AND p.product.productId = :productId AND p.quantity >= 5")
    boolean checkQuantityByCustomerIdAndProductId(@Param("customerId") int customerId, @Param("productId") int productId);

    // tính tổng tiền kh khi mua sp
    @Query("SELECT SUM(p.quantity * p.product.price) FROM Purchase p WHERE p.customer.customerId = :customerId AND p.product.productId = :productId")
    Double TotalQuantityByCustomerIdAndProductId(@Param("customerId") int customerId, @Param("productId") int productId);

    // cập nhật quantity khi kh mua lại sp
    @Modifying
    @Query("UPDATE Purchase p SET p.quantity = p.quantity + 1 WHERE p.customer.customerId = :customerId AND p.product.productId = :productId")
    void incrementPurchaseQuantity(@Param("customerId") int customerId, @Param("productId") int productId);

    // kiểm tra kh và sp có tồn tại trong db hay không  // Purchase as p  where p.customer_id.id
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Purchase p WHERE p.customer.customerId = :customerId AND p.product.productId = :productId")
    boolean existsByCustomerIdAndProductId(int customerId, int productId);

    // kiểm tra cập nhật quantity dựa trên thông tin id customer và product
    @Query("SELECT p FROM Purchase p WHERE p.customer.customerId = :customerId AND p.product.productId = :productId")
    Optional<Purchase> getByCustomerAndProduct(@Param("customerId") int customerId, @Param("productId") int productId);
}

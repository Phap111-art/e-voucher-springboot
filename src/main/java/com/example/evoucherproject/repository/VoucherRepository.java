package com.example.evoucherproject.repository;

import com.example.evoucherproject.model.entity.Customer;
import com.example.evoucherproject.model.entity.Purchase;
import com.example.evoucherproject.model.entity.Voucher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Integer> {

    //check status voucher
    @Query("SELECT v.status FROM Voucher v WHERE v.customer.customerId = :customerId")
    boolean checkCustomerVoucherStatus(@Param("customerId") int customerId);

    // insert voucher
    @Modifying
    @Query(value = "INSERT INTO Voucher (status, startTime, endTime, discount, customerId) VALUES (true, CURRENT_TIMESTAMP, FUNCTION('DATE_ADD', CURRENT_TIMESTAMP, 'INTERVAL 3 DAY'), :discount, :customerId) RETURNING *", nativeQuery = true)
    Voucher insertVoucherStatusAndDates(@Param("discount") double discount, @Param("customerId") int customerId);

    // check customer of voucher is exits
    boolean existsByCustomerCustomerId(int customerId);

    // trả về dữ liệu  voucher theo id customer
    Optional<Voucher> findByCustomerCustomerId(int customerId);

    // delete voucher neu qua han
    @Modifying
    @Query("DELETE FROM Voucher v WHERE v.customer.customerId = :customerId AND FUNCTION('DATE', v.endTime) = FUNCTION('CURRENT_DATE')")
    void deleteVouchersByCustomerAndEndDate(@Param("customerId") int customerId);
}

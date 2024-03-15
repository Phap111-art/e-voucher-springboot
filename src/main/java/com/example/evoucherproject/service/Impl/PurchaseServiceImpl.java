package com.example.evoucherproject.service.Impl;

import com.example.evoucherproject.exception.CustomException;
import com.example.evoucherproject.model.entity.Purchase;
import com.example.evoucherproject.model.entity.Voucher;
import com.example.evoucherproject.repository.CustomerRepository;
import com.example.evoucherproject.repository.ProductRepository;
import com.example.evoucherproject.repository.PurchaseRepository;
import com.example.evoucherproject.repository.VoucherRepository;
import com.example.evoucherproject.service.PurchaseService;
import com.example.evoucherproject.ultil.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public List<String> paymentCustomer(int customerId, int productId) {
        List<String> ls = new ArrayList<>();
        Voucher voucher = new Voucher();
        addOrUpdateCustomerBuyProduct(customerId, productId);

        // check customer sau khi mua 5 lần sản phầm
        if (purchaseRepository.isCustomerExceededPurchaseLimit(customerId, productId)) {
            voucher = insertOrUpdateVoucher(customerId);
        }
        Double money = purchaseRepository.TotalQuantityByCustomerIdAndProductId(customerId, productId);
        ls.add("tổng giá tiền hiện tại : " + money.toString());
        Double amount = money - (money * (voucher.getDiscount()) / 100);
        ls.add("sau khi áp dụng mã voucher " + voucher.getDiscount() + " : " + amount);
        voucherRepository.deleteVouchersByCustomerAndEndDate(customerId);
        return ls;
    }


    private Voucher applyDiscountDuringLateNight(int customerId) {
        Voucher voucher = null;
        LocalDateTime currentTime = LocalDateTime.now();
        LocalTime startTime = LocalTime.of(0, 0); // 12h tối
        LocalTime endTime = LocalTime.of(1, 0); // 1h sáng
        // Thực hiện giảm giá cho khách hàng từ 12h tối tới 1h sáng
        if (currentTime.toLocalTime().isAfter(startTime) && currentTime.toLocalTime().isBefore(endTime)) {
            // voucher is exits
            if (voucherRepository.checkCustomerAndStatus(customerId)) {
                voucher = new Voucher();
            } else {
                voucher = voucherRepository.insertVoucherStatusAndDates(10, customerId);
            }

        }
        return voucher;
    }

    private Voucher applyDiscountOnSunday(int customerId) {
        Voucher voucher = null;
        LocalDate currentDate = LocalDate.now();
        // Thực hiện giảm giá sunday hang` tuan`
        if (currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            // voucher is exits
            if (voucherRepository.checkCustomerAndStatus(customerId)) {
                voucher = new Voucher();
            } else {
                voucher = voucherRepository.insertVoucherStatusAndDates(5, customerId);
            }
        }
        return voucher;
    }

    private Voucher applyDiscountOnSpecialOccasions(int customerId) {
        Voucher voucher = null;
        LocalDate currentDate = LocalDate.now();
        // Kiểm tra nếu ngày hiện tại là một trong các ngày lễ yêu cầu (giáng sinh, tết, ngày khai trương)
        if (DateUtils.isChristmas(currentDate) || DateUtils.isNewYear(currentDate) || DateUtils.isGrandOpening(currentDate)) {

            // voucher is exits
            if (voucherRepository.checkCustomerAndStatus(customerId)) {
                voucher = new Voucher();
            } else {
                voucher = voucherRepository.insertVoucherStatusAndDates(5, customerId);
            }

        }
        return voucher;
    }

    private void addOrUpdateCustomerBuyProduct(int customerId, int productId) {
        // nếu lần đầu mua hàng
        if (!purchaseRepository.existsByCustomerIdAndProductId(customerId, productId)) {
            purchaseRepository.save(Purchase.builder()
                    .purchaseTime(DateUtils.currentDateTime())
                    .customer(customerRepository.findById(customerId).get())
                    .product(productRepository.findById(productId).get())
                    .quantity(1).build());
        } else {
            // cập nhật số lượng khi mua hàng
            Optional<Purchase> purchase = purchaseRepository.getByCustomerAndProduct(customerId, productId);
            if (!purchase.isPresent())
                throw new CustomException("id customer and product does not exits!", HttpStatus.NOT_FOUND);
            purchase.get().setQuantity(purchase.get().getQuantity() + 1);
            purchaseRepository.save(purchase.get());
        }

    }

    private Voucher insertOrUpdateVoucher(int customerId) {
        Optional<Voucher> isVoucher = voucherRepository.findByCustomerCustomerId(customerId);
        if (voucherRepository.checkCustomerAndStatus(customerId) != null && voucherRepository.checkCustomerAndStatus(customerId)) {
            if (isVoucher.isPresent()){
                isVoucher.get().setDiscount(7);
                return voucherRepository.save(isVoucher.get());
            }
        }
        Voucher newVoucher = Voucher.builder()
                .customer(customerRepository.findById(customerId).get())
                .discount(7)
                .status(true)
                .startTime(new Date())
                .endTime(DateUtils.getEndDate())
                .build();
        return voucherRepository.save(newVoucher);
    }

}

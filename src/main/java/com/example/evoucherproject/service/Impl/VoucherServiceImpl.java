package com.example.evoucherproject.service.Impl;

import com.example.evoucherproject.exception.CustomException;
import com.example.evoucherproject.model.dto.CustomResponse;
import com.example.evoucherproject.model.entity.Customer;
import com.example.evoucherproject.model.entity.Voucher;
import com.example.evoucherproject.repository.CustomerRepository;
import com.example.evoucherproject.repository.VoucherRepository;
import com.example.evoucherproject.service.VoucherService;
import com.example.evoucherproject.ultil.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;


@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private CustomerRepository customerRepository;
    // IO , Date and Time , JPQL , SQL
    @Override
    public ResponseEntity<CustomResponse> saveVoucher(int customerId, int discount) {
        try {
            Optional<Customer> customer = customerRepository.findById(customerId);
            if (!customer.isPresent()) {
                throw new CustomException("Customer does not exist!", HttpStatus.NOT_FOUND);
            }
            Voucher voucher = Voucher.builder()
                    .customer(customer.get())
                    .discount(discount)
                    .status(true)
                    .startTime(new Date())
                    .endTime(DateUtils.getEndDate())
                    .build();
            if (!voucherRepository.existsByCustomerCustomerId(customerId)) voucherRepository.save(voucher);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CustomResponse("You have received a " + discount + "% discount coupon !", HttpStatus.OK.value(), voucher));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getHttpStatus())
                    .body(new CustomResponse(e.getMessage(), HttpStatus.OK.value(), new Voucher()));
        }
    }

}

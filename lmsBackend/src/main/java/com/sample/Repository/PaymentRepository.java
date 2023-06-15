package com.sample.Repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sample.Model.Payment;


@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment, Integer>{

	Payment save(Payment p);

	List<Payment> findAll();

}

package com.sample.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sample.Model.Customer;
import com.sample.Model.Loan;

@Repository
public interface LoanRepository extends PagingAndSortingRepository<Loan, Integer> {

	@Query("select l from Loan l where l.id=?1")
	Customer findByCustomerId(int custId);
	
	void delete(Loan loan);
	//@Query("select * from Loan l")
	List<Loan> findAll();
    //@Query("insert into loan (loanId, duration, loanAmt, loanType, mothlyEMI,id) values (default, ?, ?, ?, ?, ?)")
	Loan save(Loan loan);

	Loan findById(int loanId);

}

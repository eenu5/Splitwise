package com.example.groyoo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.groyoo.entity.ExpenseDtl;

@Repository
public interface ExpenseDtlRepository extends PagingAndSortingRepository<ExpenseDtl,String> {


	@Query("select r from ExpenseDtl r where r.isCompleted=:b and (r.borrowerId=:iD or r.lenderId=:iD)")
	List<ExpenseDtl> findOpenTransactions(String iD, boolean b);

	ExpenseDtl findBytransactionId(String transactionId);
}

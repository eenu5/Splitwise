package com.example.groyoo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.groyoo.entity.ExpenseEntity;
import com.example.groyoo.repository.ExpenseDtlRepository;
import com.example.groyoo.entity.ExpenseDtl;

@Service
public class SplitwiseService {

	@Autowired
	private ExpenseDtlRepository expenseDtlRepository;

	public String createExpense(ExpenseEntity expenseEntity) {

		try {
			List<ExpenseDtl> expenseDtlList = new ArrayList<ExpenseDtl>();
			int index = 0;
			
			for(String id : expenseEntity.getPayeeId())
			{
				ExpenseDtl expDtl = new ExpenseDtl();
				expDtl.setBorrowerId(id);
				expDtl.setLenderId(expenseEntity.getMainUserId());
				expDtl.setTransactionDate(new Date());
				expDtl.setIsCompleted(false);
				expDtl.setAmount(findAmount(expenseEntity.getExpense(), expenseEntity.isPorA(),expenseEntity.getContribution().get(index)));
				expDtl.setTransactionId(UUID.randomUUID().toString());
				expenseDtlList.add(expDtl);
				index++;
			}
			expenseDtlRepository.saveAll(expenseDtlList);
		} catch (Exception e) {
			e.printStackTrace();
			return "FAILED";
		}
		return "SUCCESS";
	}

	public BigDecimal findAmount(BigDecimal  amount, boolean isPerc, BigDecimal contribution)
	{
		if(isPerc)
			return amount.divide(BigDecimal.valueOf(100)).multiply(contribution);
		else 
			return contribution;		
	}

	public List<ExpenseDtl> getOpenTransactions(String userId) {
		if(null!=userId)
		{
			
			return expenseDtlRepository.findOpenTransactions(userId, false);
		}
		else
			return null;
	}

	public ExpenseDtl updateExpense(String transactionId, BigDecimal amount) {

		ExpenseDtl expDtl =expenseDtlRepository.findBytransactionId(transactionId);
		if(expDtl.getAmount()==amount)
		{
			expDtl.setAmount(BigDecimal.valueOf(0));
			expenseDtlRepository.save(expDtl);
		}
		else {
			expDtl.setAmount(expDtl.getAmount().subtract(amount));
			expenseDtlRepository.save(expDtl);
		}
		return expDtl;
	}


}

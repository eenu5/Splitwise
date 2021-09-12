package com.example.groyoo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.groyoo.entity.ExpenseDtl;
import com.example.groyoo.entity.ExpenseEntity;
import com.example.groyoo.service.SplitwiseService;

@RestController
public class SplitwiseController {

	@Autowired 
	private SplitwiseService splitwiseService;

	@RequestMapping(value="/createExpense", method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public String createExpense(@RequestBody ExpenseEntity exenseEntity)
	{
		return splitwiseService.createExpense(exenseEntity);
	}

	@RequestMapping(value="/getOpenExpenses/{userId}", method=RequestMethod.GET, produces="application/json")
	public List<ExpenseDtl> getOpenTransactions(@PathVariable String userId)
	{
		return splitwiseService.getOpenTransactions(userId);
	}

	@RequestMapping(value="/updateExpense", method=RequestMethod.PATCH, produces="application/json")
	public ExpenseDtl updateExpense(@RequestBody String transactionId, BigDecimal amount)
	{
		return splitwiseService.updateExpense(transactionId,amount);
	}
}

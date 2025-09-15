package com.JobFindingPlatform.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobFindingPlatform.Entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
	List<Payment>findByUserId(Long userId);

}


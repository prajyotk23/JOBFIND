package com.JobFindingPlatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.JobFindingPlatform.Entity.UnblockRequest;

public interface UnblockRequestRepository extends JpaRepository<UnblockRequest, Long> {
}

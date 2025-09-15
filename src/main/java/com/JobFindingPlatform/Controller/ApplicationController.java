package com.JobFindingPlatform.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.JobFindingPlatform.DTO.ApplicationDTO;
import com.JobFindingPlatform.Enum.JobType;
import com.JobFindingPlatform.Enum.Status;
import com.JobFindingPlatform.Service.ApplicationService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {
	@Autowired
	private ApplicationService appService;

	@PostMapping("/apply")
	public ResponseEntity<String> apply(@RequestBody ApplicationDTO dto) {
		appService.apply(dto);
		return ResponseEntity.ok("Application got Submitted");
	}

	@GetMapping("/jobseeker")
	public ResponseEntity<List<ApplicationDTO>> getJobSeekerApplication(@RequestParam String jobseekerEmail) {
		return ResponseEntity.ok(appService.getByJobSeekerEmail(jobseekerEmail));
	}

	@GetMapping("/recruiter")
	public ResponseEntity<List<ApplicationDTO>> getRecruiterApplication(@RequestParam String recruiterEmail) {
		return ResponseEntity.ok(appService.getByRecruiterEmail(recruiterEmail));
	}

	@GetMapping("/jobType")
	public ResponseEntity<List<ApplicationDTO>> getByJobType(@RequestParam JobType jobType) {
		return ResponseEntity.ok(appService.getByJobType(jobType));
	}

	@GetMapping("/search/{jobTitle}")
	public ResponseEntity<List<ApplicationDTO>>getByJobTitle (@PathVariable String jobTitle){	
			return ResponseEntity.ok(appService.getByJobTitle(jobTitle));
	}

	@PostMapping("/update-status")
	public ResponseEntity<String> updateStatus (@RequestParam Long id, @RequestParam Status status){
	appService.updateStatus(id, status);
	return ResponseEntity.ok("Status got updated");
	}
	
}


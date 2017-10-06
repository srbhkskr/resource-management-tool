package com.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;
import com.app.entity.WorkAllocation;
import com.app.service.IWorkAllocationService;

@Controller
@RequestMapping("user")
public class WorkAllocationController {
	@Autowired
	private IWorkAllocationService workallocationService;
	
	@GetMapping("workallocation/{id}")
	public ResponseEntity<WorkAllocation> getWorkAllocationById(@PathVariable("id") Integer id)
	{
		WorkAllocation workallocation = workallocationService.getWorkAllocationById(id);
		
		return new ResponseEntity<WorkAllocation>(workallocation, HttpStatus.OK);
		
	}
	
	@GetMapping("workallocations")
	public ResponseEntity<List<WorkAllocation>> getAllWorkAllocations()
	{
		
		List<WorkAllocation> workallocations = workallocationService.getAllWorkAllocations();
		return new ResponseEntity<List<WorkAllocation>>(workallocations, HttpStatus.OK);
		
	}
	
	@PostMapping("project/{projectid}/employee/{empid}/workallocation")
	public ResponseEntity<Void> addWorkAllocation(@RequestBody WorkAllocation workallocation ,UriComponentsBuilder builder,@PathVariable("projectid") Integer projectid,@PathVariable("empid") Integer empid)
	{
		boolean flag = workallocationService.addWorkAllocation(empid, projectid, workallocation);
		if(flag = false)
		{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("workallocation/{id}").buildAndExpand(workallocation.getId()).toUri());
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		
	}
	
	@PutMapping("workallocation")
	public ResponseEntity<WorkAllocation> updateWorkAllocation(@RequestBody WorkAllocation workallocation) {
		workallocationService.updateWorkAllocation(workallocation);
		return new ResponseEntity<WorkAllocation>(workallocation, HttpStatus.OK);
	}
	
	@DeleteMapping("workallocation/{id}")
	public ResponseEntity<Void> deleteWorkAllocation(@PathVariable("id") Integer workallocationid)
	{
		workallocationService.removeWorkAllocation(workallocationid);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
}

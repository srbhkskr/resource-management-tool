package com.app.service;

import java.util.List;

import com.app.entity.Employee;
import com.app.entity.WorkAllocation;

public interface IWorkAllocationService {
	
	List<WorkAllocation> getAllWorkAllocations();
    WorkAllocation getWorkAllocationById(int allocationId);
    void updateWorkAllocation(WorkAllocation allocation);
	boolean addWorkAllocation(Integer empid, Integer projectid, WorkAllocation workallocation);
	void removeWorkAllocation(Integer workallocationid);

}

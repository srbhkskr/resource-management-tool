package com.app.dao;

import java.util.List;

import com.app.entity.WorkAllocation;
import com.app.entity.Project;
import com.app.entity.Skill;;

public interface IWorkAllocationDAO {
	
	List<WorkAllocation> getAllWorkAllocations();
	WorkAllocation getWorkAllocationById(int workallocationId);
	void addWorkAllocation(WorkAllocation workallocation);
	void updateWorkAllocation(WorkAllocation workallocation);
	void deleteWorkAllocation(int workallocationId);
	boolean workallocationExists(String Email);

}

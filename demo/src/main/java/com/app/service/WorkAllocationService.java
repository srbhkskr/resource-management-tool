package com.app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.dao.IWorkAllocationDAO;
import com.app.dao.IEmployeeDAO;
import com.app.dao.IProjectDAO;
import com.app.entity.WorkAllocation;

@Service
public class WorkAllocationService implements IWorkAllocationService {
	
	@Autowired
	private IWorkAllocationDAO workAllocationDAO;
	
	@Autowired
	private IEmployeeDAO employeeDAO;
	
	@Autowired
	private IProjectDAO projectDAO;
	
	

	@Override
	public WorkAllocation getWorkAllocationById(int workAllocationId) {
		// TODO Auto-generated method stub
		return workAllocationDAO.getWorkAllocationById(workAllocationId);
	}

	@Override
	public List<WorkAllocation> getAllWorkAllocations() {
		// TODO Auto-generated method stub
		return workAllocationDAO.getAllWorkAllocations();
	}

	@Override
	public void updateWorkAllocation(WorkAllocation allocation) {
		// TODO Auto-generated method stub
		workAllocationDAO.updateWorkAllocation(allocation);
		
	}

	@Override
	public boolean addWorkAllocation(Integer empid, Integer projectid, WorkAllocation workallocation) {
		// TODO Auto-generated method stub
		employeeDAO.getEmployeeById(empid).addWorkAllocation(workallocation);
		projectDAO.getProjectById(projectid).addWorkAllocation(workallocation);
		workAllocationDAO.addWorkAllocation(workallocation);
		return false;
	}

	@Override
	public void removeWorkAllocation(Integer workallocationid) {
		// TODO Auto-generated method stub
		if(null != workAllocationDAO.getWorkAllocationById(workallocationid)){
			
			WorkAllocation allocation_to_delete = workAllocationDAO.getWorkAllocationById(workallocationid);
			allocation_to_delete.getEmployee().removeWorkAllocation(allocation_to_delete);
			allocation_to_delete.getProject().removeWorkAllocation(allocation_to_delete);
			workAllocationDAO.deleteWorkAllocation(allocation_to_delete.getId());
		}
		
	}

}

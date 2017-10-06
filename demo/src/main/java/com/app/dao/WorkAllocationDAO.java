package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.WorkAllocation;
import com.app.entity.Project;
import com.app.entity.Skill;

@Transactional
@Repository
public class WorkAllocationDAO implements IWorkAllocationDAO {

	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public List<WorkAllocation> getAllWorkAllocations() {
		String hql = "From WorkAllocation as workallocation ORDER BY workallocation.id";
		//System.out.println( entityManager.createQuery(hql).getResultList()) ;
		return (List<WorkAllocation>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public WorkAllocation getWorkAllocationById(int workallocationId) {
		// TODO Auto-generated method stub
		return entityManager.find(WorkAllocation.class,workallocationId);
	}

	@Override
	public void addWorkAllocation(WorkAllocation workallocation) {
		// TODO Auto-generated method stub
		entityManager.persist(workallocation);

	}

	@Override
	public void updateWorkAllocation(WorkAllocation workallocation) {
		// TODO Auto-generated method stub
		WorkAllocation workallocation1 = getWorkAllocationById(workallocation.getId());
		if(workallocation.getStart_date() != null && workallocation.getStart_date() != workallocation1.getStart_date()){
			workallocation1.setStart_date(workallocation.getStart_date());
		}
		
		if(workallocation.getBillingCode() != null && workallocation.getBillingCode() != workallocation1.getBillingCode()){
			workallocation1.setBillingCode(workallocation.getBillingCode());
		}
		
		if(workallocation.getEmployee() != null && workallocation.getEmployee() != workallocation1.getEmployee()){
			workallocation1.setEmployee(workallocation.getEmployee());
		}
		
		if(workallocation.getEnd_date() != null && workallocation.getEnd_date() != workallocation1.getEnd_date()){
			workallocation1.setEnd_date(workallocation.getEnd_date());
		}
		
		if(workallocation.getPercentage() != null && workallocation.getPercentage() != workallocation1.getPercentage()){
			workallocation1.setPercentage(workallocation.getPercentage());
		}
		
		if(workallocation.getProject() != null && workallocation.getProject() != workallocation1.getProject()){
			workallocation1.setProject(workallocation.getProject());
		}
		//workallocation.setProjectAllocations(workallocation.getProjectAllocations());
		if(workallocation.getTypeOfWork() != null && workallocation.getTypeOfWork() != workallocation1.getTypeOfWork()){
			workallocation1.setTypeOfWork(workallocation1.getTypeOfWork());
		}
		entityManager.flush();

	}

	@Override
	public void deleteWorkAllocation(int workallocationId) {
		// TODO Auto-generated method stub
		entityManager.remove(getWorkAllocationById(workallocationId));
	}

	@Override
	public boolean workallocationExists(String email) {
		// TODO Auto-generated method stub
		String hql = "From WorkAllocation as workallocation where email = ?";
		int count = entityManager.createQuery(hql).setParameter(1, email).getResultList().size();
		return count > 0 ? true : false;
	}
	
	
	

}

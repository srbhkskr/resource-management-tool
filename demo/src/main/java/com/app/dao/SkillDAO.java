package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.Employee;
import com.app.entity.Skill;

@Transactional
@Repository
public class SkillDAO implements ISkillDAO {

	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public List<Skill> getAllSkills() {
		String hql = "From Skill as skill ORDER BY skill.id";
		return (List<Skill>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Skill getSkillById(int skillId) {
		// TODO Auto-generated method stub
		return entityManager.find(Skill.class,skillId);
	}

	@Override
	public void addSkill(Skill skill) {
		// TODO Auto-generated method stub
		entityManager.persist(skill);
	}

	@Override
	public void updateSkill(Skill skill) {
		// TODO Auto-generated method stub
		Skill tempskill = getSkillById(skill.getId());
		tempskill.setCategory_Id(skill.getCategory_Id());
		tempskill.setEmployees(skill.getEmployees());
		tempskill.setLevel(skill.getName());
		tempskill.setName(skill.getName());
		tempskill.setProjects(skill.getProjects());
		entityManager.flush();
	}

	@Override
	public void deleteSkill(int skillId) {
		// TODO Auto-generated method stub
		entityManager.remove(getSkillById(skillId));
	}

	@Override
	public boolean SkillExists(Integer id) {
		// TODO Auto-generated method stub
		String hql = "From Skill as skill where id = ?";
		int count = entityManager.createQuery(hql).setParameter(1, id).getResultList().size();
		return count > 0 ? true : false;
	}

}

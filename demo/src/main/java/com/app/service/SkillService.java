package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ISkillDAO;
import com.app.entity.Skill;

@Service
public class SkillService implements ISkillService {
	
	@Autowired
	private ISkillDAO skillDAO;
	
	@Override
	public List<Skill> getAllSkills() {
		// TODO Auto-generated method stub
		
		return skillDAO.getAllSkills();
	}

	@Override
	public Skill getSkillById(int skillId) {
		// TODO Auto-generated method stub
		return skillDAO.getSkillById(skillId);
	}

	@Override
	public synchronized boolean addSkill(Skill skill) {
		// TODO Auto-generated method stub

		skillDAO.addSkill(skill);
		return true;
	}

	@Override
	public void updateSkill(Skill skill) {
		// TODO Auto-generated method stub
		skillDAO.updateSkill(skill);
		
	}

	@Override
	public void deleteSkill(int skillId) {
		// TODO Auto-generated method stub
		skillDAO.deleteSkill(skillId);
	}

	

}

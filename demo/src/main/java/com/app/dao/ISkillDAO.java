package com.app.dao;

import java.util.List;

import com.app.entity.Skill;;

public interface ISkillDAO {
	
	List<Skill> getAllSkills();
	Skill getSkillById(int skillId);
	void addSkill(Skill skill);
	void updateSkill(Skill skill);
	void deleteSkill(int skillId);
	boolean SkillExists(Integer skillId);
}

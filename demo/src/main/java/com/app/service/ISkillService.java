package com.app.service;

import java.util.List;

import com.app.entity.Skill;

public interface ISkillService {
	
	List<Skill> getAllSkills();
    Skill getSkillById(int skillId);
    boolean addSkill(Skill skill);
    void updateSkill(Skill skill);
    void deleteSkill(int skillId);

}

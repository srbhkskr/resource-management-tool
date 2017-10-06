package com.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
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

import com.app.entity.Skill;
import com.app.service.ISkillService;

@Controller
@RequestMapping("user")
public class SkillController {
	@Autowired
	private ISkillService skillService;
	
	@GetMapping("skill/{id}")
	public ResponseEntity<Skill> getSkillById(@PathVariable("id") Integer id)
	{
		Skill skill = skillService.getSkillById(id);
		return new ResponseEntity<Skill>(skill, HttpStatus.OK);
		
	}
	
	@GetMapping("skills")
	public ResponseEntity<List<Skill>> getAllSkills()
	{
		
		List<Skill> skills = skillService.getAllSkills();
		return new ResponseEntity<List<Skill>>(skills, HttpStatus.OK);
		
	}
	
	@PostMapping("skill")
	public ResponseEntity<Void> addSkill(@RequestBody Skill skill ,UriComponentsBuilder builder)
	{
		boolean flag = skillService.addSkill(skill);
		if(flag = false)
		{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("skill/{id}").buildAndExpand(skill.getId()).toUri());
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		
	}
	
	@PutMapping("skill")
	public ResponseEntity<Skill> updateSkill(@RequestBody Skill skill) {
		skillService.updateSkill(skill);
		return new ResponseEntity<Skill>(skill, HttpStatus.OK);
	}
	
	@DeleteMapping("skill/{id}")
	public ResponseEntity<Void> deleteSkill(@PathVariable("id") Integer Id)
	{
		skillService.deleteSkill(Id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}

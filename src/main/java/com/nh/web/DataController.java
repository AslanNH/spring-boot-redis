package com.nh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nh.dao.PersonDao;
import com.nh.domain.Person;

@RestController
public class DataController {

	@Autowired
	PersonDao personDao;
	
	/**
	 * 设置字符串和对象
	 */
	@RequestMapping("/set")
	public void set() {
		Person person  = new Person("1","wyf",32);
		personDao.save(person);
		personDao.stringRedisTemplateDemo();
	}
	
	/**
	 * 获取字符串
	 * @return
	 */
	@RequestMapping("/getStr")
	public String getStr() {
		return personDao.getString();
	}
	
	/**
	 * 获取对象
	 * @return
	 */
	@RequestMapping("/getPerson")
	public Person getPerson() {
		return personDao.getPerson();
	}
}

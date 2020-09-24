package com.xq17.cloudtools.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xq17.cloudtools.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService userService;

	@PostMapping("/api/findUsers")
	public Map<String, Object> findUsers(@RequestParam Map<String, Object> map) {
		System.out.println(map);
		return userService.findUsers(map);
	}
}

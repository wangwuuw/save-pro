package com.save.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
/**
 * 输入框
 * @return
 */
	 @RequestMapping(value = "/index")
	    public String hello() {
	        return "index";
	    }
	 /**
	  * xss展示界面，xss说白了就是往服务器注入脚本语言，
	  * 例如<script>alert('xss攻击')</script>
	  * @param req
	  * @param res
	  * @return
	  */
	 @RequestMapping(value = "/show")
	    public String show(HttpServletRequest req,HttpServletResponse res) {
	        return "show";
	    }
}

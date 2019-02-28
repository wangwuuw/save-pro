package com.save.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.util.concurrent.RateLimiter;

@RestController
public class LimitController {
	  private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	  
	   private static final RateLimiter rateLimiter = RateLimiter.create(2);//每秒处理的任务量
	/**
	 * 限流
	 * @return
	 */
		 @RequestMapping(value = "/limit")
		    public String limit() {
			 if (rateLimiter.tryAcquire()) { //  一次拿1个
				             System.out.println(sdf.format(new Date()));
				              try {
				                  Thread.sleep(500);
				                  return "处理结束";
				              } catch (InterruptedException e) {
				                  e.printStackTrace();
				              }
				          }else {
				        	  return "对不起，系统繁忙";
				          }
			  return "对不起，系统繁忙";
		    }
		
}

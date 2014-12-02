package com.zhanghui.saler.mvc;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;

@Controller
public class TimerTestController {
	public void execute() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM mm:ss");
		System.out.println(sdf.format(new Date()));
	}
}

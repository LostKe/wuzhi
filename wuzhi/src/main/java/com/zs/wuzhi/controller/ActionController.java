package com.zs.wuzhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zs.wuzhi.bean.Diary;
import com.zs.wuzhi.service.Service;

@Controller
public class ActionController {
	
	
	@RequestMapping("showRandom")
	public ModelAndView showRandom(ModelMap model){
		Diary diary=Service.getInstance().getRandomDiary();
		model.put("diary", diary);
		return new ModelAndView("show_diary", model);
	}

}

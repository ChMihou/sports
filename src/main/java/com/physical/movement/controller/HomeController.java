package com.physical.movement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("home")
public class HomeController {

    @RequestMapping("/index")
    public ModelAndView home(ModelAndView mv) {
        mv.addObject("/home/index");
        return mv;
    }

    @RequestMapping("/about")
    public ModelAndView about(ModelAndView mv) {
        mv.addObject("/home/about");
        return mv;
    }

    @RequestMapping("/cause-single")
    public ModelAndView causesingle(ModelAndView mv) {
        mv.addObject("/home/cause-single");
        return mv;
    }

    @RequestMapping("/causes")
    public ModelAndView causes(ModelAndView mv) {
        mv.addObject("/home/causes");
        return mv;
    }

    @RequestMapping("/concat")
    public ModelAndView concat(ModelAndView mv) {
        mv.addObject("/home/concat");
        return mv;
    }

    @RequestMapping("/gallery")
    public ModelAndView gallery(ModelAndView mv) {
        mv.addObject("/home/gallery");
        return mv;
    }

    @RequestMapping("/news")
    public ModelAndView news(ModelAndView mv) {
        mv.addObject("/home/news");
        return mv;
    }

    @RequestMapping("/testimonial")
    public ModelAndView testimonial(ModelAndView mv) {
        mv.addObject("/home/testimonial");
        return mv;
    }
}

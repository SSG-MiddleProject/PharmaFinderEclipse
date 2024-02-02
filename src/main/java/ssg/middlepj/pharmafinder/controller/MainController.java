package ssg.middlepj.pharmafinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
public class MainController {
    @RequestMapping(value = "/main.do", method = RequestMethod.GET)
    public String landing(Model model) {
        model.addAttribute("url", "list.do" );
        return "main.tiles";
    }

@RequestMapping(value = "/list.do", method = RequestMethod.GET)
public String list(Locale locale, Model model) {
        model.addAttribute("url2", "list.do");
    return "main.tiles";

}






}
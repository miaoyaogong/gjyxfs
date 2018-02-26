package com.kaishengit.controller;

import com.kaishengit.dto.Message;
import com.kaishengit.pojo.User;
import com.kaishengit.service.VisitService;
import com.kaishengit.util.Constants;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Value("${user.salt}")
    private String passwordSalt;

    @Inject
    private VisitService visitService;
    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest req) {
        model.addAttribute("menu","index");
        model.addAttribute("siteName", Constants.siteName);
//        visitService.saveNewVisit(req);

        return "index";
    }


    /**
     * 服务页面
     */
    @RequestMapping(value = "/service",method = RequestMethod.GET)
    public String service(Model model, String num) {
        model.addAttribute("menu","service");
        model.addAttribute("siteName", Constants.siteName);
        if("1".equals(num)){
            return "service1";
        }else if("2".equals(num)){
            return "service2";
        }else if("3".equals(num)){
            return "service3";
        }else if("4".equals(num)){
            return "service4";
        }
        return "service";
    }

    /**
     * 案例页面
     */
    @RequestMapping(value = "/case",method = RequestMethod.GET)
    public String successCase(Model model, String num) {

        model.addAttribute("menu","case");
        model.addAttribute("siteName", Constants.siteName);
        if("1".equals(num)){
            return "case1";
        }else if("2".equals(num)){
            return "case2";
        }
        return "case";
    }

    /**
     * 吉祥物页面
     */
    @RequestMapping(value = "/treasure",method = RequestMethod.GET)
    public String treasure(Model model) {
        model.addAttribute("menu","treasure");
        model.addAttribute("siteName", Constants.siteName);
        return "treasure";
    }
    /**
     * 关于页面
     */
    @RequestMapping(value = "/about",method = RequestMethod.GET)
    public String about(Model model) {
        model.addAttribute("menu","about");
        model.addAttribute("siteName", Constants.siteName);
        return "about";
    }

}

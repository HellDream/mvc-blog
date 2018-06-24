package com.blog.controller;

import com.blog.domain.User;
import com.blog.service.UserService;
import com.blog.utils.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    private UserService userService;

    @RequestMapping(value = "/register")
    public ModelAndView register(HttpServletRequest request){
        request.getSession().setAttribute("type","register");
        if(request.getMethod().equals("POST")) {
            String username = request.getParameter("username");
            if(username.length()<4){
                request.getSession().setAttribute("Error", "The username is shorter than 4 digits");
                return new ModelAndView("error");
            }
            String password = request.getParameter("password");
            String password_confirm = request.getParameter("confirm");
            if(!password.equals(password_confirm)){
                request.getSession().setAttribute("Error", "The password is not the same");
                return new ModelAndView("error");
            }
            if(userService.findMatchUser(username)>0){
                request.getSession().setAttribute("Error", "The password has been register");
                return new ModelAndView("error");
            }
            userService.signUpUser(username,password);
            User user = userService.loginUser(username,password);
            request.getSession().setAttribute("user_id", user.getUserId());
            request.getSession().setAttribute("username", user.getUsername());
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("form");

    }

    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request){
        request.getSession().setAttribute("type","login");
        if(request.getMethod().equals("POST")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if(!userService.checkUserPassword(username, password)){
                request.getSession().setAttribute("Error", "Incorrect password");
                return new ModelAndView("error");
            }
            User user = userService.loginUser(username,password);
            request.getSession().setAttribute("user_id", user.getUserId());
            request.getSession().setAttribute("username", user.getUsername());
            Object previousPage = request.getSession().getAttribute("previousPage");
            System.out.println(request.getSession().getAttribute("previousPage"));
            if(previousPage==null)
                return new ModelAndView("redirect:/");
            return new ModelAndView("redirect:"+previousPage.toString());
        }
        return new ModelAndView("form");
    }



    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

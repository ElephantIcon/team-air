package com.team.air.controller.user;

import com.team.air.bean.User;
import com.team.air.mapper.UserMapper;
import com.team.air.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RequestMapping("user")
@Controller
public class UserController {

    private String prefile = "custom/";
    @Autowired
    UserService userService;


    //用户登录
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("psw") String psw,
                        Map<String,Object> map, HttpSession session){

        User user = userService.getUserByUsername(username);
        if (user == null){

            map.put("msg","不存在该用户！请重新输入。");
            return prefile + "sign_in";
        }else if( !user.getPsw().equals(psw)) {
            map.put("username",username);
            map.put("msg","密码不正确！请重新输入");
            return prefile + "sign_in";
        }else {
            //登录成功，并将用户信息存入session
            session.setAttribute("loginUser",user);
            System.out.println(user);
            System.out.println(user.getUsername());
            return "redirect:/index";
        }
    }

    //用户登出
    @RequestMapping("/logout")
    public String sighOut(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("loginUser");
        request.getSession().invalidate();
        return "redirect:/index";
    }


    //用户注册
    //springMVC自动将请求参数和入参对象的属性进行一一绑定，要求请求参数的名字和javabean入参的对象里面的属性名是一样的
    @PostMapping("/resgister")
    public String resgister(User user,Model model){
        //rediect: 重定向到一个页面 /代表当前项目路径
        //forward: 转发到一个地址
        //来到登录页面
        System.out.println("用户注册的信息："+user);
        //判断用户是否已经存在
        if (userService.getUserByUsername(user.getUsername()) == null){
            //用户名可用，保存用户，返回登录页面
            //用户id自己获取用户总数+1
            int id = userService.countUser();
            user.setUser_id(id+1);

            //保存到数据库
            int no = userService.addUser(user);
            System.out.println("addUser():"+no);
            model.addAttribute("success","注册成功，请登录！");
            model.addAttribute("User",user);
            return prefile+"sign_in";
        }else {
            //用户名已存在，注册失败，返回注册页面
//            map.put("error","该用户名已存在！请重新输入");
//            model.addAttribute("user",user);
            model.addAttribute("error","该用户名已存在！请重新输入");
            return prefile+"sign_up";
        }
    }

    @RequestMapping("/info")
    public String UserInfo(){

        System.out.println("进入个人信息页面----------------------》》》》");
        return prefile + "userInfo";
    }

    //用户修改个人信息
    @GetMapping("/upUserInfo")
    public String updateUserInfo(Model model,User user,HttpSession session){

        System.out.println(user.toString());
        userService.updateUser(user);
        //更新用户信息后，重新加载更新后的用户信息
        User newUser = userService.getUserById(user.getUser_id());
        session.setAttribute("loginUser",newUser);
        model.addAttribute("success","个人信息修改成功！");
        return prefile + "userInfo";
    }

    //用户修改密码
    @PostMapping("/upPsw")
    public String updatePsw(Model model,User user,HttpServletRequest request){

        System.out.println(user.toString());
        userService.updatePsw(user);
        model.addAttribute("success","密码修改成功！请重新登录！");
//        修改密码后，注销用户重新登录
        request.getSession().removeAttribute("loginUser");
        request.getSession().invalidate();
        return prefile + "sign_in";
    }

    @RequestMapping("/book")
    public String userBook(){
        return "orderBook";
    }



}

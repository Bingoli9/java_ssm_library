package com.mecol.bookshop_ssm.web;

import com.mecol.bookshop_ssm.entity.Admin;
import com.mecol.bookshop_ssm.entity.ReaderCard;
import com.mecol.bookshop_ssm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
public class LoginController
{
    @Autowired
    private LoginService loginService;

    //负责处理登录请求
    //请求参数会根据参数名称默认契约自动绑定到相应方法的入参中
    @RequestMapping("/api/loginCheck")
    @ResponseBody
    public Object loginCheck(HttpServletRequest request )
    {
        long id=Long.parseLong(request.getParameter("id")); //账号
        String passwd=request.getParameter("passwd");
        ReaderCard isReader=loginService.hasMatchReader(id,passwd);
        //管理员和图书馆读者 用同一个登录 不用选择 这里查了两个表看有没有匹配的
        //这样 读书馆读者 和管理员 密码和ID都不能出错不然会混乱

        Admin isAdmin=loginService.hasMatchAdmin(id,passwd);
        HashMap<String,String> res=new HashMap<>();
        if(isAdmin!=null)
        {

          //  System.out.println(isAdmin);
            request.getSession().setAttribute("admin",isAdmin);
            request.getSession().setAttribute("login",1);//随便给个值 login不为空 jsp中弹出提示框
            res.put("stateCode","1");//表明是管理员登录
            res.put("msg","管理员登录成功");
        }
        else if(isReader!=null)
        {

         //   System.out.println(isReader);
            request.getSession().setAttribute("readercard",isReader);
            request.getSession().setAttribute("login",1);
            res.put("stateCode","2");
            res.put("msg","读者登录成功");
        }
        else
        {
            res.put("stateCode","0");
            res.put("msg","账号或者密码错误");
        }
        return  res;
    }

    @RequestMapping("/admin_main.html")
    public ModelAndView toAdminMain(HttpServletResponse response) {
        return new ModelAndView("admin_main");
    }

    @RequestMapping("/reader_main.html")
    public ModelAndView toReaderMain(HttpServletResponse response) {
        return new ModelAndView("reader_main");
    }


    @RequestMapping("/reader_repasswd.html")
    public ModelAndView reReaderPasswd() {
        return new ModelAndView("reader_repasswd");
    }

    @RequestMapping("/reader_repasswd_do")
    public String reReaderPasswdDo(HttpServletRequest request, String oldPasswd, String newPasswd, String reNewPasswd, RedirectAttributes redirectAttributes)
    {
        ReaderCard reader = (ReaderCard) request.getSession().getAttribute("readercard");
        long id = reader.getReaderId();
        reader=loginService.getReaderById(id);
        String password = reader.getPassword();
        if (password.equals(oldPasswd))
        {
            try
            {
                reader.setPassword(newPasswd);
                loginService.readerRePassword(reader);
                redirectAttributes.addFlashAttribute("succ", "密码修改成功！");
            }
            catch (Exception e)
            {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "密码修改失败！");
            }
            return "redirect:/reader_repasswd.html";
        } else {
            redirectAttributes.addFlashAttribute("error", "旧密码错误！");
            return "redirect:/reader_repasswd.html";
        }
    }



    @RequestMapping("/admin_repasswd.html")
    public ModelAndView reAdminPasswd() {
        return new ModelAndView("admin_repasswd");
    }

    @RequestMapping("/admin_repasswd_do")
    public String reAdminPasswdDo(HttpServletRequest request, String oldPasswd, String newPasswd, String reNewPasswd, RedirectAttributes redirectAttributes) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        long id = admin.getAdminId();
        admin=loginService.getAdminById(id);
        String password = admin.getPassword();
        if (password.equals(oldPasswd))
        {
            try
            {
                admin.setPassword(newPasswd);
                loginService.adminRePassword(admin);
                redirectAttributes.addFlashAttribute("succ", "密码修改成功！");
            }
            catch (Exception e)
            {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("error", "密码修改失败！");
            }
            return "redirect:/admin_repasswd.html";
        } else {
            redirectAttributes.addFlashAttribute("error", "旧密码错误！");
            return "redirect:/admin_repasswd.html";
        }
    }

    @RequestMapping("/logout.html")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/index.jsp";
    }

    //配置404页面
    //当我们发出的请求 无法匹配到时 最后才会执行这个404
    @RequestMapping("*")
    public String notFind() {
        return "404";
    }
}













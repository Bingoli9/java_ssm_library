package com.mecol.bookshop_ssm.web;

import com.mecol.bookshop_ssm.entity.ReaderCard;
import com.mecol.bookshop_ssm.entity.ReaderInfo;
import com.mecol.bookshop_ssm.service.ReaderCardService;
import com.mecol.bookshop_ssm.service.ReaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class ReaderController
{

    @Autowired
    private ReaderInfoService readerInfoService;
    @Autowired
    private ReaderCardService readerCardService;

    private ReaderInfo getReaderInfo(long readerId, String name, String sex, String birth, String address, String
            phone) {
        ReaderInfo readerInfo = new ReaderInfo();
        Date date = new Date();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            date = df.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        readerInfo.setAddress(address);
        readerInfo.setName(name);
        readerInfo.setReaderId(readerId);
        readerInfo.setPhone(phone);
        readerInfo.setSex(sex);
        readerInfo.setBirth(date);
        return readerInfo;
    }

    @RequestMapping("reader_edit.html")
    public ModelAndView readerInfoEdit(HttpServletRequest request) {
        long readerId = Long.parseLong(request.getParameter("readerId"));
        ReaderInfo readerInfo = readerInfoService.getReaderInfoById(readerId);
        ModelAndView modelAndView = new ModelAndView("admin_reader_edit");
        modelAndView.addObject("readerInfo", readerInfo);
        return modelAndView;
    }

    @RequestMapping("reader_edit_do.html")
    public String readerInfoEditDo(HttpServletRequest request, String name, String sex, String birth, String
            address, String phone, RedirectAttributes redirectAttributes) {
        long readerId = Long.parseLong(request.getParameter("readerId"));
        ReaderInfo readerInfo = getReaderInfo(readerId, name, sex, birth, address, phone);
        ReaderCard readerCard=readerCardService.getReadCardById(readerId);
        readerCard.setName(name);


        try
        {
            readerInfoService.editReaderInfo(readerInfo);
            readerCardService.editReaderCard(readerCard);
            redirectAttributes.addFlashAttribute("succ", "读者信息修改成功！");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "读者信息修改失败！");
        }
        return "redirect:/allreaders.html";
    }



    @RequestMapping("reader_add.html")
    public ModelAndView readerInfoAdd() {
        return new ModelAndView("admin_reader_add");
    }

    @RequestMapping("reader_add_do.html")
    public String readerInfoAddDo(String name, String sex, String birth, String address, String phone, String
            password, RedirectAttributes redirectAttributes)
    {
        ReaderInfo readerInfo = getReaderInfo(0, name, sex, birth, address, phone);
        readerInfoService.addReaderInfo(readerInfo);

        readerInfo=readerInfoService.getReaderInfoByName(name);
        ReaderCard readerCard=new ReaderCard();
        readerCard.setReaderId(readerInfo.getReaderId());
        readerCard.setName(name);
        readerCard.setPassword(password);
        try
        {
            readerCardService.addReaderCard(readerCard);
            redirectAttributes.addFlashAttribute("succ", "添加读者信息成功！");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("succ", "添加读者信息失败！");
        }
        return "redirect:/allreaders.html";
    }


    @RequestMapping("allreaders.html")
    public ModelAndView allBooks() {
        ArrayList<ReaderInfo> readers = readerInfoService.readerInfos();
        ModelAndView modelAndView = new ModelAndView("admin_readers");
        modelAndView.addObject("readers", readers);
        return modelAndView;
    }

    @RequestMapping("reader_delete.html")
    public String readerDelete(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long readerId = Long.parseLong(request.getParameter("readerId"));

        try
        {
            readerInfoService.deleteReaderInfoById(readerId);
            readerCardService.deleteReaderCardById(readerId);
            redirectAttributes.addFlashAttribute("succ", "删除成功！");
        }
        catch (Exception e)
        {
            redirectAttributes.addFlashAttribute("error", "删除失败！");
        }
        return "redirect:/allreaders.html";
    }

    @RequestMapping("/reader_info.html")
    public ModelAndView toReaderInfo(HttpServletRequest request) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        ReaderInfo readerInfo = readerInfoService.getReaderInfoById(readerCard.getReaderId());
        ModelAndView modelAndView = new ModelAndView("reader_info");
        modelAndView.addObject("readerinfo", readerInfo);
        return modelAndView;
    }

    @RequestMapping("reader_info_edit.html")
    public ModelAndView readerInfoEditReader(HttpServletRequest request) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        ReaderInfo readerInfo = readerInfoService.getReaderInfoById(readerCard.getReaderId());
        ModelAndView modelAndView = new ModelAndView("reader_info_edit");
        modelAndView.addObject("readerinfo", readerInfo);
        return modelAndView;
    }


    @RequestMapping("reader_edit_do_r.html")
    public String readerInfoEditDoReader(HttpServletRequest request, String name, String sex, String birth, String address, String phone, RedirectAttributes redirectAttributes)
    {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        ReaderInfo readerInfo = getReaderInfo(readerCard.getReaderId(), name, sex, birth, address, phone);
        readerCard.setReaderId(readerInfo.getReaderId());
        readerCard.setName(readerInfo.getName());

        try
        {
            readerInfoService.editReaderInfo(readerInfo);
            readerCardService.editReaderCard(readerCard);
            request.getSession().setAttribute("readercard", readerCard);
            redirectAttributes.addFlashAttribute("succ", "信息修改成功！");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "信息修改失败！");
        }

        return "redirect:/reader_info.html";
    }

}



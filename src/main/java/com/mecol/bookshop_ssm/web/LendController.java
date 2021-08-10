package com.mecol.bookshop_ssm.web;

import com.mecol.bookshop_ssm.entity.Book;
import com.mecol.bookshop_ssm.entity.Lend;
import com.mecol.bookshop_ssm.entity.ReaderCard;
import com.mecol.bookshop_ssm.service.BookService;
import com.mecol.bookshop_ssm.service.LendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class LendController
{

    @Autowired
    private LendService lendService;
    @Autowired
    private BookService bookService;

    @RequestMapping("/lendlist.html")
    public ModelAndView lendList(HttpServletRequest request)
    {
        List<Lend> lendList=lendService.lendList();
        ModelAndView modelAndView = new ModelAndView("admin_lend_list");
        modelAndView.addObject("list", lendList);
        return modelAndView;
    }

    @RequestMapping("/mylend.html")
    public ModelAndView myLend(HttpServletRequest request)
    {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        ModelAndView modelAndView = new ModelAndView("reader_lend_list");
        modelAndView.addObject("list", lendService.myLendList(readerCard.getReaderId()));
        return modelAndView;
    }





    @RequestMapping("/deletelend.html")
    public String deleteLend(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long serNum = Long.parseLong(request.getParameter("serNum"));

        try
        {
            lendService.deleteLendBySerNum(serNum);
            redirectAttributes.addFlashAttribute("succ", "记录删除成功！");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "记录删除失败！");
        }
        return "redirect:/lendlist.html";
    }

    @RequestMapping("/lendbook.html")
    public String bookLend(HttpServletRequest request, RedirectAttributes redirectAttributes) throws ParseException {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        long readerId = ((ReaderCard) request.getSession().getAttribute("readercard")).getReaderId();

        Book book=bookService.getBookById(bookId);
        book.setNumber(book.getNumber()-1);
        Lend lend=new Lend();
        lend.setBookId(bookId);
        lend.setReaderId(readerId);
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String nowTime=simpleDateFormat.format(date);
        Date lendTime=simpleDateFormat.parse(nowTime);
        lend.setLendDate(lendTime);
        try
        {
            bookService.updateBook(book);
            lendService.addLend(lend);
           //插入后自动返回 插入数据的主键ID 返回在lend的ser_num属性中
            //lendService.addLendAndGetId(lend);
          //  System.out.println(lend.getSer_num());
            redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("succ", "图书借阅失败！");
        }
        return "redirect:/reader_books.html";
    }


    @RequestMapping("/returnbook.html")
    public String bookReturn(HttpServletRequest request, RedirectAttributes redirectAttributes) throws ParseException {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        long readerId = ((ReaderCard) request.getSession().getAttribute("readercard")).getReaderId();

        Book book=bookService.getBookById(bookId);
        book.setNumber(book.getNumber()+1);
        Lend lend;
        lend=lendService.getLendByReadIdAndBookId(readerId,bookId);

        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String nowTime=simpleDateFormat.format(date);
        Date backTime=simpleDateFormat.parse(nowTime);
        lend.setBackDate(backTime);
        try
        {
            bookService.updateBook(book);
            lendService.updateLend(lend);
            redirectAttributes.addFlashAttribute("succ", "图书归还成功！");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("succ", "图书归还失败！");
        }
        return "redirect:/reader_books.html";
    }


}



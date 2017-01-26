package com.el.spring.controller;

import com.el.spring.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class UserController {

    private final UserService service;

    final
    ApplicationContext context;

    @Autowired
    public UserController(UserService service, ApplicationContext context) {
        this.service = service;
        this.context = context;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("/view/index.jsp");
        return mav;
    }

/*
    @RequestMapping( value = "isPhonePresent", method = RequestMethod.GET )
    public String displayPhone() {
        return "/view/isPhonePresent.jsp";
    }

    @RequestMapping( value = "isPhonePresent" , method = RequestMethod.POST)
    public String validatePhone(@RequestParam String phone ) {
        Customer byPhone = service.getByPhone(phone);
        if (byPhone == null)
            return "/view/error.jsp";
        else if( service.getByPhone(phone).getPhone().equals("0677449650"))
            return "/view/success.jsp";
        else
            return "/view/error.jsp";
    }

    @RequestMapping(value = "/listcustomers", method = RequestMethod.GET)
    public String showAll(ModelMap map) {
        CustomerService bean = context.getBean(CustomerService.class);
        StringBuilder sb = new StringBuilder("<br>");
        bean.getAll().forEach(it->sb.append(it.toString()).append("<br>"));
        map.put("msg", sb.toString());
        return "/view/listcustomers.jsp";
    }

    @RequestMapping(value = "/showcustomers", method = RequestMethod.GET)
    public String showAllList(ModelMap map) {
        CustomerService bean = context.getBean(CustomerService.class);
        List<Customer> list = bean.getAll();
        map.put("msg", list);
        return "/view/showcustomers.jsp";
    }

    @RequestMapping("/remove/{phone}")
    public String removeCustomer(@PathVariable("phone") String phone) {
        service.deleteByPhone(phone);
        return "redirect:/showcustomers";
    }

    @RequestMapping("edit/{phone}")
    public String editCustomer(@PathVariable("email") String email, Model model){
        model.addAttribute("customer", service.find(email));
        model.addAttribute("msg", service.getAll());
        return "/showcustomers";
    }

    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    public String showCustomer(ModelMap map) {
        CustomerService bean = context.getBean(CustomerService.class);
        Customer customer = new Customer(map.get("email").toString(), map.get("name").toString(), true, map.get("phone").toString());
        bean.editItem(customer);
        return "/view/customer.jsp";
    }

    @RequestMapping(value = "/showcustomers/add", method = RequestMethod.GET)
    public String addCustomer(@PathVariable("email") String email,
                              @PathVariable("name") String name,
                              @PathVariable("phone") String phone){
        Customer customer = new Customer(email, name, true, phone);
        if (customer.getEmail() == null) {
            service.addItem(customer);
        } else {
            service.editItem(customer);
        }
        return "redirect:/showcustomers";
    }


 @RequestMapping("customer/{email}")
    public String custData(@PathVariable("email") String email, Model model){
        model.addAttribute("cust", service.find(email));
        return "customer";
    }

    @RequestMapping("bookdata/{id}")
    public String bookData(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookService.getBookById(id));
        return "bookdata";
    }

@RequestMapping(value = "books", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.listBooks());
        return "books";
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book ){
        if (book.getId() == 0) {
            bookService.addBook(book);
        } else {
            bookService.updateBook(book);
        }
        return "redirect:/books";
    }

    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") int id) {
        bookService.removeBook(id);
        return "redirect:/books";
    }

    @RequestMapping("edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("bookList", bookService.listBooks());
        return "books";
    }
 */
}



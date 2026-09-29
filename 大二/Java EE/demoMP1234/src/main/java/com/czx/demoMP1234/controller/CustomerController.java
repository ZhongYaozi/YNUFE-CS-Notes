package com.czx.demoMP1234.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czx.demoMP1234.entity.Customer;
import com.czx.demoMP1234.entity.User;
import com.czx.demoMP1234.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // 1. 查询所有客户信息并展示
    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int pageNum,
                       @RequestParam(defaultValue = "5") int pageSize,
                       Model model, HttpSession session) {
        Page<Customer> page = customerService.page(new Page<>(pageNum, pageSize));
        model.addAttribute("page", page);
        model.addAttribute("user", session.getAttribute("user"));
        return "customers";
    }

    // 2. 按条件查询
    @RequestMapping("/search")
    public String search(Customer customer, Model model, HttpSession session,
                         @RequestParam(defaultValue = "1") int pageNum,
                         @RequestParam(defaultValue = "5") int pageSize) {
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        if (customer.getName() != null && !customer.getName().isEmpty()) {
            wrapper.like("name", customer.getName());
        }
        if (customer.getCompName() != null && !customer.getCompName().isEmpty()) {
            wrapper.like("comp_name", customer.getCompName());
        }
        Page<Customer> page = customerService.page(new Page<>(pageNum, pageSize), wrapper);
        model.addAttribute("page", page);
        model.addAttribute("user", session.getAttribute("user"));
        return "customers";
    }

    // 显示添加客户表单页面
    @GetMapping("/add")
    public String showAddForm(HttpSession session, Model model) {
        // 检查用户是否为管理员
        User user = (User) session.getAttribute("user");
        if (user == null || !"ADMIN".equals(user.getRole())) {
            return "redirect:/customer/list";
        }
        model.addAttribute("user", user);
        return "add_customer";
    }
    
    // 处理添加客户请求
    @PostMapping("/add")
    public String addCustomer(Customer customer, HttpSession session) {
        // 检查用户是否为管理员
        User user = (User) session.getAttribute("user");
        if (user == null || !"ADMIN".equals(user.getRole())) {
            return "redirect:/customer/list";
        }
        
        // 设置客户状态为可用（true或1）
        customer.setStatus(true);
        customerService.save(customer);
        return "redirect:/customer/list";
    }

    // 3. 切换状态
    @PostMapping("/toggle/{id}")
    @ResponseBody
    public String toggleStatus(@PathVariable Integer id) {
        Customer customer = customerService.getById(id);
        if (customer != null) {
            customer.setStatus(!customer.getStatus());
            customerService.updateById(customer);
            return "success";
        }
        return "fail";
    }
}
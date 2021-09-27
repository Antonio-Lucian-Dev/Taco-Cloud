package com.asusoftware.tacocloud.controller;

import com.asusoftware.tacocloud.model.Order;
import com.asusoftware.tacocloud.model.User;
import com.asusoftware.tacocloud.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;

    @GetMapping("/current")
    public ModelAndView orderForm(Model model) {
        ModelAndView mv = new ModelAndView("orderForm");
        mv.addObject("order", new Order());
        return mv;
    }

    @PostMapping
    public ModelAndView processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        System.out.println("Order: " + order);
        if(errors.hasErrors()) {
            ModelAndView mv = new ModelAndView("orderForm");
            mv.addObject("order", new Order());
            return mv;
        }
        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();
        log.info("Order submitted: " + order);
        return new ModelAndView("redirect:/");
    }
}

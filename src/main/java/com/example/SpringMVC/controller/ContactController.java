package com.example.SpringMVC.controller;

import com.example.SpringMVC.entity.Contact;
import com.example.SpringMVC.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/read-contact")
    public String showReadContactPage(Model model) {
        model.addAttribute("contacts", contactService.findAll());
        return "readcontact";
    }

    @GetMapping("/create-contact")
    public String showCreateContactPage(Model model) {
        model.addAttribute("command", new Contact());
        return "createcontact";
    }

    @PostMapping(value = "/create-contact")
    public String createContact(@ModelAttribute("contact") Contact contact) {
        contactService.saveContact(contact);
        return "redirect:/contact/read-contact";
    }

    @GetMapping(value = "/update-contact/{id}")
    public String showUpdateContactPage(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("command", contactService.findById(id).orElse(null));
        return "updatecontact";
    }

    @PostMapping(value = "/update-contact/{id}")
    public String updateContact(@PathVariable int id, @ModelAttribute("contact") Contact contact) {
        contactService.updateContact(id, contact);
        return "redirect:/contact/read-contact";
    }

    @GetMapping(value = "/delete-contact/{id}")
    public String deleteContact(@PathVariable int id) {
        contactService.deleteById(id);
        return "redirect:/contact/read-contact";
    }

}
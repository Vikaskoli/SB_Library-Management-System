package com.library_mgt.Controller;

import com.library_mgt.Entity.Publisher;
import com.library_mgt.Service.PublisherService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/publishers")
    public String findAllPublishers(Model model){
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "publishers";
    }

    @GetMapping("/remove-publisher/{id}")
    public String deletePublisher(@PathVariable Long id, Model model){
        publisherService.deletePublisherById(id);
        model.addAttribute("publishers",publisherService.findAllPublishers());
        return "publishers";

    }

    @GetMapping("/update-publisher/{id}")
    public String updatePublisher(@PathVariable Long id, Model model){
       model.addAttribute("publisher", publisherService.findPublisherById(id));
        return "update-publisher";
    }

    @PostMapping("/update-publisher/{id}")
    public String saveUpdatedPublisher(@PathVariable Long id, Publisher publisher, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "update-publisher";
        }
        publisherService.updatePublisher(publisher);
        model.addAttribute("publishers",publisherService.findAllPublishers());
        return "redirect:/publishers";
    }

    @GetMapping("/add-publisher")
    public  String showCreatePage(Publisher publisher){
        return "add-publisher";
    }

    @PostMapping("/save-publisher")
    public String createPublisher(Publisher publisher, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "add-publisher";
        }
        publisherService.createPublisher(publisher);
        model.addAttribute("publishers",publisherService.findAllPublishers());
        return "redirect:/publishers";
    }
}

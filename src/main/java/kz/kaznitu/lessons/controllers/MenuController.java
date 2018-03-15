package kz.kaznitu.lessons.controllers;

import kz.kaznitu.lessons.models.Menu;
import kz.kaznitu.lessons.reposotories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/menu")
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;
    private long a;
    @RequestMapping("/add")
    public String showForm(Model model){
        model.addAttribute("menu",new Menu());
        return "menu_add_form";
    }


    @PostMapping("/add")
    public String addMenu(@ModelAttribute Menu menu){
        menuRepository.save(menu) ;
        return "redirect:/menu/all" ;
    }


    @GetMapping("/all2")
    public @ResponseBody
    Iterable<Menu> allMenu(){
        return menuRepository.findAll() ;
    }

    @GetMapping("/all")
    public String allMenu2(Model model){
        List<Menu> menus = (List<Menu>) menuRepository.findAll() ;
        model.addAttribute("menus", menus) ;
        return "menus" ;
    }

    @RequestMapping(value = "/deleteContact",method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam("id") long idd){
        menuRepository.deleteById(idd);
        return new ModelAndView("redirect:/menu/all");
    }
    @PostMapping("/edit<Menuu")
    public String editMenu(@ModelAttribute Menu menu) {
        Menu menu1 = new Menu();
        menu1.setId(a);
        menu1.setName(menu.getName());
        menu1.setSummary(menu.getSummary());
        menu1.setPrice(menu1.getPrice());
        menuRepository.save(menu);
        return "redirect:/menu/all2";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public ModelAndView editMenu(Model model,@RequestParam("id") long id){
        a=id;
        Optional<Menu> menu = (Optional<Menu>) menuRepository.findById(id);
        model.addAttribute("menu",menu);
        return new ModelAndView("editMenu");
    }
    @RequestMapping("/editMenu")
    public String showForm2(Model model){
        model.addAttribute("menu",new Menu());
        return "editMenu";
    }




}


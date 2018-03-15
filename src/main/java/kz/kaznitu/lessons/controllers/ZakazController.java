package kz.kaznitu.lessons.controllers;

import kz.kaznitu.lessons.models.Zakaz;
import kz.kaznitu.lessons.reposotories.ZakazRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/zakaz")
public class ZakazController {
    @Autowired
    private ZakazRepository zakazRepository;
    private long a;

    @RequestMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("zakaz", new Zakaz());
        return "zakaz_add_form";
    }


    @PostMapping("/add")
    public String addZakaz(@ModelAttribute Zakaz zakaz) {
        Zakaz save = ZakazRepository.<Zakaz>save(zakaz);
        return "redirect:/zakaz/all";
    }


    @GetMapping("/all2")
    public @ResponseBody
    Iterable<Zakaz> allZakaz() { return zakazRepository.findAll();
    }

    @GetMapping("/all")
    public String allZakazs2(Model model) {
        List<Zakaz> zakazs = (List<Zakaz>) zakazRepository.findAll();
        model.addAttribute("zakazs", zakazs);
        return "zakazs";
    }

    @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam("id") long idd) {
        zakazRepository.deleteById(idd);
        return new ModelAndView("redirect:/zakaz/all");

    }

    @PostMapping("/editZakazz")
    public String editZakaz(@ModelAttribute Zakaz zakaz) {
        Zakaz zakaz1 = new Zakaz();
        zakaz1.setId(a);
        zakaz1.setName(zakaz.setName());
        zakaz1.setTime(zakaz.getTime());
        return "redirect:/zakaz/all2";
    }

    @RequestMapping(value = "/editZakaz", method = RequestMethod.GET)
    public ModelAndView editZakaz(Model model, @RequestParam("id") long id) {
        a = id;
        Optional<Zakaz> zakaz = (Optional<Zakaz>) zakazRepository.findById(id);
        model.addAttribute("zakaz", zakaz);
        return new ModelAndView("editZakaz");
    }

    @RequestMapping("/editZakaz")
    public String showForm2(Model model) {
        model.addAttribute("zakaz", new Zakaz());
        return "editZakaz";
    }
}


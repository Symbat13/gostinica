package kz.kaznitu.lessons.controllers;

import kz.kaznitu.lessons.models.Komnata;
import kz.kaznitu.lessons.reposotories.KomnataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/komnata")
public class KomnataController {
    @Autowired
    private KomnataRepository komnataRepository;
    private long a;

    @RequestMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("komnata", new Komnata());
        return "komnata_add_form";
    }


    @PostMapping("/add")
    public String addKomnata(@ModelAttribute Komnata komnata) {
        Komnata save = komnataRepository.<Komnata> save(komnata);
        return "redirect:/komnata/all";
    }


    @GetMapping("/all2")
    public @ResponseBody
    Iterable<Komnata> allZakaz() { return komnataRepository.findAll();
    }

    @GetMapping("/all")
    public String allKomnata2(Model model) {
        List<Komnata> komnata = (List<Komnata>) komnataRepository.findAll();
        model.addAttribute("komnata", komnata);
        return "komnatas";
    }

    @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam("id") long idd) {
        komnataRepository.deleteById(idd);
        return new ModelAndView("redirect:/komnata/all");

    }

    @PostMapping("/editKomnata")
    public String editKomnata(@ModelAttribute Komnata komnata) {
        Komnata komnata1 = new Komnata();
        komnata1.setId(a);
        komnata1.setName(komnata.getName());
        komnata1.setTime(komnata.getTime());
        return "redirect:/zakaz/all2";
    }

    @RequestMapping(value = "/editKomnata", method = RequestMethod.GET)
    public ModelAndView editKomnata(Model model, @RequestParam("id") long id) {
        a = id;
        Optional<Komnata> komnata = (Optional<Komnata>) komnataRepository.findById(id);
        model.addAttribute("komnata", komnata);
        return new ModelAndView("editKomnata");
    }

    @RequestMapping("/editKomnata")
    public String showForm2(Model model) {
        model.addAttribute("komnata", new Komnata());
        return "editKomnata";
    }
}


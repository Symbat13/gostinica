package kz.kaznitu.lessons.controllers;

import kz.kaznitu.lessons.models.Sotrudniki;
import kz.kaznitu.lessons.reposotories.SotrudnikiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/sotrudiki")
public class SotrudnikiController {
    @Autowired
    private SotrudnikiRepository sotrudnikiRepository;
    private long a;
    @RequestMapping("/add")
    public String showForm(Model model){
        model.addAttribute("sotrudniki",new Sotrudniki());
        return "sotrudniki_add_form";
    }


    @PostMapping("/add")
    public String addSotrudniki(@ModelAttribute Sotrudniki sotrudniki){

        sotrudnikiRepository.save(sotrudniki) ;
        return "redirect:/sotrudniki/all" ;
    }


    @GetMapping("/all2")
    public @ResponseBody
    Iterable<Sotrudniki> allSotrudniki(){
        return sotrudnikiRepository.findAll() ;
    }

    @GetMapping("/all")
    public String allSotrudniki2(Model model){
        List<Sotrudniki> sotrudnikis = (List<Sotrudniki>) sotrudnikiRepository.findAll() ;
        model.addAttribute("sotrudnikis", sotrudnikis) ;
        return "sotrudnikis" ;
    }

    @RequestMapping(value = "/deleteContact",method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam("id") long idd){
        sotrudnikiRepository.deleteById(idd);
        return new ModelAndView("redirect:/sotrudniki/all");
    }
    @PostMapping("/edit<Sotrudniki")
    public String editSotrudniki(@ModelAttribute Sotrudniki sotrudniki) {
        Sotrudniki sotrudniki1 = new Sotrudniki();
        sotrudniki1.setId(a);
        sotrudniki1.setName(sotrudniki1.getName());
        sotrudniki1.setSummary(sotrudniki1.getSummary());
        sotrudniki1.setPrice(sotrudniki1.getPrice());
        sotrudnikiRepository.save(sotrudniki);
        return "redirect:/sotrudniki/all2";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public ModelAndView editSotrudniki(Model model,@RequestParam("id") long id){
        a=id;
        Optional<Sotrudniki> sotrudniki = (Optional<Sotrudniki>) sotrudnikiRepository.findById(id);
        model.addAttribute("sotrudniki",sotrudniki);
        return new ModelAndView("editSotrudniki");
    }
    @RequestMapping("/editSotrudniki")
    public String showForm2(Model model){
        model.addAttribute("sotrudniki",new Sotrudniki());
        return "editSotrudniki";
    }




}


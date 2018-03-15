package kz.kaznitu.lessons.controllers;

import kz.kaznitu.lessons.models.Guest;
import kz.kaznitu.lessons.reposotories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/guest")
public class GuestController {
    @Autowired
    private GuestRepository guestRepository;
    private long a;

    @RequestMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("guest", new Guest());
        return "guest_add_form";
    }


    @PostMapping("/add")
    public String addGuest(@ModelAttribute Guest guest) {
        guestRepository.save(guest);
        return "redirect:/guest/all";
    }


    @GetMapping("/all2")
    public @ResponseBody
    Iterable<Guest> allGuests() {
        return guestRepository.findAll();
    }

    @GetMapping("/all")
    public String allGuest2(Model model) {
        List<Guest> guests = (List<Guest>) guestRepository.findAll();
        model.addAttribute("guests", guests);
        return "guests";
    }

    @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam("id") long idd) {
        guestRepository.deleteById(idd);
        return new ModelAndView("redirect:/guest/all");

    }

    @PostMapping("/editGuestt")
    public String editGuest(@ModelAttribute Guest guest) {
        Guest guest1 = new Guest();
        guest1.setId(a);
        guest1.setFirstName(guest.getFirstName());
        guest1.setLastName(guest.getLastName());
        guest1.setNumber(guest.getNumber());
        guestRepository.save(guest1);
        return "redirect:/guest/all2";
    }

    @RequestMapping(value = "/editGuest", method = RequestMethod.GET)
    public ModelAndView editGuest(Model model, @RequestParam("id") long id) {
        a = id;
        Optional<Guest> guest = (Optional<Guest>) guestRepository.findById(id);
        model.addAttribute("guest", guest);
        return new ModelAndView("editGuest");
    }

    @RequestMapping("/editGuest")
    public String showForm2(Model model) {
        model.addAttribute("guest", new Guest());
        return "editGuest";
    }
}



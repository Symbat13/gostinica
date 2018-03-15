package kz.kaznitu.lessons.controllers;

import kz.kaznitu.lessons.models.Client;
import kz.kaznitu.lessons.reposotories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/client")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    private long a;

    @RequestMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("client", new Client());
        return "client_add_form";
    }


    @PostMapping("/add")
    public String addClient(@ModelAttribute Client client) {
        clientRepository.save(client);
        return "redirect:/client/all";
    }


    @GetMapping("/all2")
    public @ResponseBody
    Iterable<Client> allClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/all")
    public String allClient(Model model) {
        List<Client> client = (List<Client>) clientRepository.findAll();
        model.addAttribute("client", client);
        return "clients";
    }

    @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam("id") long idd) {
        clientRepository.deleteById(idd);
        return new ModelAndView("redirect:/client/all");

    }

    @PostMapping("/editClient")
    public String editClient(@ModelAttribute Client client) {
        Client client1 = new Client();
        client1.setId(a);
        client1.setFirstName(client.getFirstName());
        client1.setLastName(client.getLastName());
        client1.setNumber(client.getNumber());
        clientRepository.save(client);
        return "redirect:/client/all2";
    }

    @RequestMapping(value = "/editClient", method = RequestMethod.GET)
    public ModelAndView editClient(Model model, @RequestParam("id") long id) {
        a = id;
        Optional<Client> client = (Optional<Client>) clientRepository.findById(id);
        model.addAttribute("client", client);
        return new ModelAndView("editClient");
    }

    @RequestMapping("/editClient")
    public String showForm2(Model model) {
        model.addAttribute("client", new Client());
        return "editClient";
    }
}
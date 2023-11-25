package com.springboot.springmvc.web;

import com.springboot.springmvc.dao.PatientRepository;
import com.springboot.springmvc.entities.Recette;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecetteController {

    @Autowired
    private PatientRepository patientRepository;
    @GetMapping(path = "/index")
    public String index(){
        return "index";
    }

    @GetMapping(path = "/user/recettes")
    public String list(Model model,
                       @RequestParam(name="page",defaultValue = "0") int page,
                       @RequestParam(name="size",defaultValue = "10") int size,
                       @RequestParam(name="keyword",defaultValue = "") String mc){
        Page<Recette> pageRecettes = patientRepository.findByNameContains(mc,PageRequest.of(page,size));
        model.addAttribute("recettes",pageRecettes.getContent()); // retourner la list des patients
        model.addAttribute("pages",new int[pageRecettes.getTotalPages()]); // total des pages
        model.addAttribute("currentPage",page);
        model.addAttribute("size",size);
        model.addAttribute("keyword" , mc);


        return "recettes";
    }

    @GetMapping(path = "/admin/deleteRecette")
    public String delete(Long id ,String keyword, int page ,int size ){

        patientRepository.deleteById(id);
        return "redirect:/user/recettes?page="+page+"&size="+size+"&keyword="+keyword;
    }



    @GetMapping(path="/admin/formRecette")
    public String fromRece(Model model){
        model.addAttribute("recette" , new Recette());
        model.addAttribute("mode","new");
        return "formRecette";
    }

    @PostMapping(path = "/admin/saveRecette" )
    //public String savePatient(@Valid Patient patient , BindingResult bindingResult){
    public String savePatient(Model model, @Valid Recette recette , BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "formRecette";
        patientRepository.save(recette);
        model.addAttribute("patient",recette);;
        return "confirmation";
    }

    @GetMapping(path="/admin/editRecette")
    public String editPatient(Model model, Long id ){
        Recette p = patientRepository.findById(id).get();
        model.addAttribute("recette" , p);
        model.addAttribute("mode","edit");
        return "formRecette";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/user/recettes";
    }


}

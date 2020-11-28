package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private PersonRepository repository;

    @RequestMapping("/")
    public String index(){
        return "list";
    }

    @RequestMapping("/list")
    public String list(Model model){
        List<Person> data = new LinkedList<>();
        for (Person p: repository.findAll()){
            data.add(p);
        }
        model.addAttribute("people", data);
        return "list";
    }

    @RequestMapping("/input")
    public String input(){
        return "input";
    }

    @RequestMapping("/create")
    public String create(
            @RequestParam(name="firstname", required=true) String firstname,
            @RequestParam(name="lastname", required=true) String lastname) {
        repository.save(new Person(firstname,lastname));
        return "redirect:/list";
    }



    @RequestMapping("/show")
    public String read(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> result = repository.findById(id);
        //TODO: check if the result is found,
        if(result.isPresent()){
            //TODO: put data in the model field to be displayed in the page
            model.addAttribute("person",result.get());
            return "/show";
        }
        //TODO: in case no data is found, display the "notfound" page
        return "/notfound";
    }



    @RequestMapping("/edit")
    public String edit(
            @RequestParam(name="id", required=true) Long id,
            Model model) {
        Optional<Person> result = repository.findById(id);
        //TODO: check if the result is found
        if(result.isPresent()){
            //TODO: put data in the model field to be displayed in the next page to edit them
            model.addAttribute("person",result.get());
            return "edit";
        }
        else{ return  "notfound"; }
        //TODO: in case no data is found, display the "notfound" page
    }

    @RequestMapping("/update")
    public String update(
            @RequestParam(name="id", required=true) Long id,
            @RequestParam(name="firstname", required=true) String firstname,
            @RequestParam(name="lastname", required=true) String lastname,
            Model model) {
        Optional<Person> result = repository.findById(id);
        //TODO: check if the result is found
        if(result.isPresent()){
            repository.delete(result.get());
            repository.save(new Person(firstname,lastname));
            return "redirect:/list";
        }
        else{
            return "notfound";
        }
    }


    @RequestMapping("/delete")
    public String delete(
            @RequestParam(name="id", required=true) Long id) {
        Optional<Person> result = repository.findById(id);
        if(result.isPresent()){
            repository.delete(result.get());
            return "redirect:/list";
            //I didn't implement the 'add new person' for me it is not necessery in this case
        }
        else{
            return "notfound";
        }
    }

}
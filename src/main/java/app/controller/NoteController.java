package app.controller;


import app.entity.Note;
import app.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;


    @GetMapping("/list")
    public ModelAndView listNotes() {
        List<Note> notes = noteService.findAll();
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("notes", notes);
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteNote(@PathVariable("id") long id) {
        noteService.deleteById(id);
        return new ModelAndView("redirect:/note/list");
    }

    @GetMapping("/edit")
    public ModelAndView editNote(@RequestParam("id") long id) {
        Note note = noteService.getById(id);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("note", note);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView saveEditedNote(@Valid Note note, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("edit");
        }

        noteService.update(note);
        return new ModelAndView("redirect:/note/list");
    }

    @GetMapping("/create")
    public ModelAndView createNoteForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("note", new Note());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createNote(@Valid @ModelAttribute Note note, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("create");
        }
        noteService.add(note);
        return new ModelAndView("redirect:/note/list");
    }
}


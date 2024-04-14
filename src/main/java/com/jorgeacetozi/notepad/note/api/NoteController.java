package com.jorgeacetozi.notepad.note.api;

import java.util.List;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.jorgeacetozi.notepad.note.domain.model.Note;
import com.jorgeacetozi.notepad.note.domain.service.NoteService;


@Slf4j
@Controller
public class NoteController {

	@Autowired
	private NoteService noteService;


	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		List<Note> notes = noteService.findAll();
		modelAndView.addObject("notes", notes);
		return modelAndView;
	}

	@PostMapping("/notes")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public Note create(@RequestBody @Valid Note note) {
		log.debug("Create note request: {}", note);
		return noteService.create(note);
	}
}

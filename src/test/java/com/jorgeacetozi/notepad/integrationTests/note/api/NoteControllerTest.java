package com.jorgeacetozi.notepad.integrationTests.note.api;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jorgeacetozi.notepad.note.domain.model.Note;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@WebAppConfiguration
public class NoteControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void shouldCreateNoteWithTitleAndContentAndReturnHttp201Created() throws Exception {
		Note note = new Note("Integration Tests", "Test the external integrations such as databases or web services.");

		this.mockMvc
				.perform(post("/notes").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(note)))
				.andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.id", is(notNullValue())))
				.andExpect(jsonPath("$.title", is(note.getTitle())))
				.andExpect(jsonPath("$.content", is(note.getContent())))
				.andExpect(jsonPath("$.wordCount", is(note.getWordCount())));
	}

	@Test
	public void shouldNotCreateNoteWhenTitleIsEmptyAndReturnHttp400BadRequest() throws Exception {
		Note note = new Note("", "Test the external integrations such as databases or web services.");

		this.mockMvc
				.perform(post("/notes").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(note)))
				.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	public void shouldNotCreateNoteWhenContentIsEmptyAndReturnHttp400BadRequest() throws Exception {
		Note note = new Note("Integration Tests", "");

		this.mockMvc
				.perform(post("/notes").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(note)))
				.andDo(print()).andExpect(status().isBadRequest());
	}
}

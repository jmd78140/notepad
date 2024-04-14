package com.jorgeacetozi.notepad.acceptanceTests.note;

import org.springframework.beans.factory.annotation.Autowired;

// Testing Hamcrest
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

// Testing Junit
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;

// Selenium
import org.openqa.selenium.WebDriver;

// Project
import com.jorgeacetozi.notepad.acceptanceTests.configuration.AcceptanceTestsConfiguration;
import com.jorgeacetozi.notepad.acceptanceTests.note.pageObject.NewNotePage;
import com.jorgeacetozi.notepad.note.domain.model.Note;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { AcceptanceTestsConfiguration.class })
public class CreateNoteTest {

	@Autowired
	private WebDriver driver;

	@Autowired
	private URI notepadBaseUri;

	private NewNotePage newNotePage;
	private final String newNoteSuccessMessage = "Your note was successfully saved!";
	private final String newNoteFailMessage = "Title and Content cannot be empty";

	@BeforeEach
	public void setUp() {
		driver.get(notepadBaseUri.toString());
		newNotePage = new NewNotePage(driver);
	}

	@Test
	public void shouldCreateNewNoteWithTitleAndContent() throws InterruptedException {
		Note newNote = new Note("Acceptance Test", "Creating note from the acceptance test");
		newNotePage.create(newNote);
		assertThat(newNotePage.getMessage(), equalTo(newNoteSuccessMessage));
	}
	
	@Test
	public void shouldNotCreateNewNoteWhenTitleIsEmpty() throws InterruptedException {
		Note newNote = new Note("", "Creating note from the acceptance test");
		newNotePage.create(newNote);
		assertThat(newNotePage.getMessage(), equalTo(newNoteFailMessage));
	}
	
	@Test
	public void shouldNotCreateNewNoteWhenContentIsEmpty() throws InterruptedException {
		Note newNote = new Note("Acceptance Test", "");
		newNotePage.create(newNote);
		assertThat(newNotePage.getMessage(), equalTo(newNoteFailMessage));
	}
}

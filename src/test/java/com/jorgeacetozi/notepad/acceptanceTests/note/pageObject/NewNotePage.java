package com.jorgeacetozi.notepad.acceptanceTests.note.pageObject;

import static java.lang.Thread.sleep;

import jakarta.annotation.PostConstruct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.springframework.beans.factory.annotation.Autowired;

import com.jorgeacetozi.notepad.note.domain.model.Note;


public class NewNotePage {


	@Autowired
	protected WebDriverWait wait;
	
	@FindBy(id="newNote")
	private WebElement newNoteModal;
	
	@FindBy(id="newNoteTitle")
	private WebElement title;
	
	@FindBy(id="newNoteContent")
	private WebElement content;
	
	@FindBy(id="btnCreateNewNote")
	private WebElement createNoteButton;

	
	protected WebDriver driver;
	private Long sleep = 2000l;
	
	
    public NewNotePage(WebDriver driver) {
    	this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @PostConstruct
    private void init() {
        PageFactory.initElements(this.driver, this);
    }
	
    
	public void create(Note newNote) throws InterruptedException {
		newNoteModal.click();
		sleep(sleep);
		
		title.sendKeys(newNote.getTitle());
		content.sendKeys(newNote.getContent());
		createNoteButton.click();
		sleep(sleep);
	}

	public String getMessage() {
		return driver.findElement(By.className("noty_text")).getText();
	}
}

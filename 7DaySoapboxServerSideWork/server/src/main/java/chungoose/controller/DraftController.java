package chungoose.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import chungoose.model.Attribute;
import chungoose.model.Draft;
import chungoose.service.DraftService;

@RestController
@RequestMapping("/draft")
@CrossOrigin(origins="*")
public class DraftController {
	
	private DraftService draftService;
	
	public DraftController() {
		// No-args constructor
	}

	@Autowired
	public DraftController(DraftService draftService) {
		super();
		this.draftService = draftService;
	}
	
	@PostMapping("/submit.goose")
	public String submitDraft(@RequestBody int id) {
		draftService.submitDraft(id);
		return "success";
	}
	
	@PostMapping("/get.goose")
	public Draft getDraft(@RequestBody int id) {
		return draftService.selectById(id);
	}
	
	@GetMapping("/all.goose")
	public List<Draft> getAllDrafts() {
		System.out.println("in all.goose");
		return draftService.selectAll();
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/saveorupdate.goose")
	public <T> String saveOrUpdate(@RequestBody String draftString) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("in save or update");
		Object draft = mapper.readValue(draftString, Object.class);
		
		System.out.println(draft);
		draftService.saveOrUpdate((LinkedHashMap<T, T>)draft);
		return "success";
	}
	
	@PostMapping("/create.goose")
	public Draft createEmptyDraft(@RequestBody List<Attribute> attributes) {
		return draftService.createEmptyDraft(attributes);
	}
	
	//Aggregate 7 most recent posts of their respective day of the week
	
	@GetMapping("/featured/display.goose")
	public List<Draft> aggregateFeatured(){
		return draftService.aggregateFeatured();
	}
	
	
	//Displays all selected drafts in order from most recent to oldest
	
	@GetMapping("/featured/all.goose") 
	public List<Draft> allFeatured(){
		return draftService.allFeatured();
	}
	
	@PostMapping("/featured/select.goose")
	public List<Draft> selectFeatured() {
		return draftService.selectFeatured();
	}
	
	@PostMapping("/author/all.goose")
	public List<Draft> getDraftsByAuthor(@RequestBody String author) {
		return draftService.getDraftsByAuthor(author);
	}
}


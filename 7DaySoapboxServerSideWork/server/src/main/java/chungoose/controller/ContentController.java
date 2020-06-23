package chungoose.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chungoose.dao.ContentDao;
import chungoose.model.Content;

@RestController
@RequestMapping("/content")
@CrossOrigin(origins="*")
public class ContentController {
	
	private ContentDao conDao;
	
	public ContentController() {
		// No-args
	}
	
	@Autowired
	public ContentController(ContentDao conDao) {
		super();
		this.conDao = conDao;
	}
	
	@GetMapping("/all.goose")
	public List<Content> getAllContent() {
		return conDao.selectAll();
	}
	
	@PostMapping("/insert.goose")
	public void insertContent(Content con) {
		conDao.insert(con);
	}

}

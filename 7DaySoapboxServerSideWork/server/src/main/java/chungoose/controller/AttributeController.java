package chungoose.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chungoose.dao.AttributeDao;
import chungoose.model.Attribute;

@RestController
@RequestMapping("/attribute")
@CrossOrigin(origins="*")
public class AttributeController {
	
	private AttributeDao attDao;
	
	public AttributeController() {
		// No-args
	}

	@Autowired
	public AttributeController(AttributeDao attDao) {
		super();
		this.attDao = attDao;
	}
	
	@GetMapping("/all.goose")
	public List<Attribute> getAllAttributes() {
		return attDao.selectAll();
	}
	
	@PostMapping("/insert.goose")
	public void insertAttribute(Attribute att) {
		attDao.insert(att);
	}

}

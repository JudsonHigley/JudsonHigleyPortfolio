package chungoose;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import chungoose.dao.AttributeDao;
import chungoose.dao.ContentDao;
import chungoose.dao.DraftDao;
import chungoose.dao.UserDao;
import chungoose.model.Attribute;
import chungoose.model.Draft;
import chungoose.model.User;

/**
 * This is the driver class used for project demonstration purposes. This class provides dummy data for the 
 * demonstration, and otherwise has no effect on the application itself. Snippets of code used for different
 * parts of the demonstration are commented out.
 * 
 * @author Judson Higley
 *
 */

public class Driver {

	public static ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//	public static ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
	public static UserDao userDao = appContext.getBean("userDao", UserDao.class);
	public static DraftDao draftDao = appContext.getBean("draftDao", DraftDao.class);
	public static AttributeDao attributeDao = appContext.getBean("attributeDao", AttributeDao.class);
	public static ContentDao contentDao = appContext.getBean("contentDao", ContentDao.class);

	public static void main(String[] args) {
//		insertValues();

//		System.out.println(userDao.selectById(3));
//
//		System.out.println(draftDao.selectById(36));

//		System.out.println(userDao.selectById(1));
//		userDao.selectAll().forEach( user -> {
//			System.out.println(user);
//		});

	}

	public static void insertValues() {

		// Users
		User u1 = new User("EBoi", "eric@eric.com", "password");
		User u2 = new User("MonsterTrux", "judson.higley@yahoo.com", "password");
		User u3 = new User("TimmyTooTurnt", "tim@tim.com", "password");

		// Attributes
		
		Attribute backgroundColor = new Attribute("background-color", "", "", "");
		Attribute backgroundURL = new Attribute("background-url", "", "", "");

		Attribute textLT = new Attribute("text", "half", "left", "top");
		Attribute textLB = new Attribute("text", "half", "left", "bottom");
		Attribute textLF = new Attribute("text", "full", "left", "");

		Attribute textCT = new Attribute("text", "half", "center", "top");
		Attribute textCB = new Attribute("text", "half", "center", "bottom");
		Attribute textCF = new Attribute("text", "full", "center", "");

		Attribute textRT = new Attribute("text", "half", "right", "top");
		Attribute textRB = new Attribute("text", "half", "right", "bottom");
		Attribute textRF = new Attribute("text", "full", "right", "");

		Attribute picLT = new Attribute("pic", "half", "left", "top");
		Attribute picLB = new Attribute("pic", "half", "left", "bottom");
		Attribute picLF = new Attribute("pic", "full", "left", "");

		Attribute picCT = new Attribute("pic", "half", "center", "top");
		Attribute picCB = new Attribute("pic", "half", "center", "bottom");
		Attribute picCF = new Attribute("pic", "full", "center", "");

		Attribute picRT = new Attribute("pic", "half", "right", "top");
		Attribute picRB = new Attribute("pic", "half", "right", "bottom");
		Attribute picRF = new Attribute("pic", "full", "right", "");

		// Drafts
		Draft d1 = new Draft("My Recipe", u1);

		d1.addAttribute(backgroundColor);
		d1.addAttribute(picLT);
		d1.addAttribute(picLB);
		d1.addAttribute(textCF);
		d1.addAttribute(textRB);
		d1.addAttribute(picRB);

		Draft d2 = new Draft("Brevity", u2);

		d2.addAttribute(backgroundURL);
		d2.addAttribute(textCF);
		
		Draft d3 = new Draft("Sunday", u3);
		Draft d4 = new Draft("Monday", u3);
		Draft d5 = new Draft("Monday1", u3);
		Draft d6 = new Draft("Tuesday", u3);
		Draft d7 = new Draft("Tuesday1", u3);
		Draft d8 = new Draft("Wednesday", u3);
		Draft d9 = new Draft("Thursday", u3);
		Draft d10 = new Draft("Friday", u3);
		Draft d11 = new Draft("Friday1", u3);
		Draft d12 = new Draft("Friday2", u3);
		Draft d13 = new Draft("Friday3", u3);
		Draft d14 = new Draft("Friday4", u3);
		Draft d15 = new Draft("Saturday", u3);
		Draft d16 = new Draft("Saturday1", u3);
		Draft d17 = new Draft("Saturday2", u3);
		Draft emailTest = new Draft("emailTest", u2);


		// Content
//		Content c1 = new Content(d1, backgroundColor1, "blue");
//		contentDao.insert(c1);
//		
		userDao.insert(u1);
		userDao.insert(u2);
		userDao.insert(u3);

		attributeDao.insert(backgroundColor);
		attributeDao.insert(backgroundURL);
		attributeDao.insert(textLT);
		attributeDao.insert(textLB);
		attributeDao.insert(textLF);
		attributeDao.insert(textCT);
		attributeDao.insert(textCB);
		attributeDao.insert(textCF);
		attributeDao.insert(textRT);
		attributeDao.insert(textRB);
		attributeDao.insert(textRF);
		
		attributeDao.insert(picLT);
		attributeDao.insert(picLB);
		attributeDao.insert(picLF);
		attributeDao.insert(picCT);
		attributeDao.insert(picCB);
		attributeDao.insert(picCF);
		attributeDao.insert(picRT);
		attributeDao.insert(picRB);
		attributeDao.insert(picRF);
		
		draftDao.insert(d1);
		draftDao.insert(d2);
		draftDao.insert(d3);
		draftDao.insert(d4);
		draftDao.insert(d5);
		draftDao.insert(d6);
		draftDao.insert(d7);
		draftDao.insert(d8);
		draftDao.insert(d9);
		draftDao.insert(d10);
		draftDao.insert(d11);
		draftDao.insert(d12);
		draftDao.insert(d13);
		draftDao.insert(d14);
		draftDao.insert(d15);
		draftDao.insert(d16);
		draftDao.insert(d17);
		draftDao.insert(emailTest);
	}

}

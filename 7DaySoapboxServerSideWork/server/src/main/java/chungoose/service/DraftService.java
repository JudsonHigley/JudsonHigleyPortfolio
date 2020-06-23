package chungoose.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chungoose.dao.AttributeDao;
import chungoose.dao.ContentDao;
import chungoose.dao.DraftDao;
import chungoose.dao.UserDao;
import chungoose.model.Attribute;
import chungoose.model.Content;
import chungoose.model.Draft;
import chungoose.model.User;

@Service
public class DraftService {

	private UserDao userDao;
	private DraftDao draftDao;
	private ContentDao contentDao;
	private AttributeDao attributeDao;
	private EmailService emailServ;

	public DraftService() {
		// No-args constructor
	}

	@Autowired
	public DraftService(DraftDao draftDao, UserDao userDao, ContentDao contentDao, AttributeDao attributeDao, EmailService emailServ) {
		this.draftDao = draftDao;
		this.userDao = userDao;
		this.contentDao = contentDao;
		this.attributeDao = attributeDao;
		this.emailServ = emailServ;
	}

	public Draft selectById(int id) {
		return draftDao.selectById(id);
	}

	public List<Draft> selectAll() {
		return draftDao.selectAll();
	}

	@SuppressWarnings("unchecked")
	public <T> void saveOrUpdate(Map<T,T> draftMap) {
		Draft draft = new Draft();
		
		if((int)draftMap.get("draftId") == 0) {
			draft.setDraftId((int)draftMap.get("draftId"));
		}
		
		draft.setDraftTitle((String)draftMap.get("draftTitle"));
		draft.setDraftColumnPic((String)draftMap.get("draftColumnPic"));
		
		if(draftMap.get("draftCreated") != null) {
			draft.setDraftCreated(new Date((long)(draftMap.get("draftCreated"))));
		}
		
		draft.setDraftSubmitted((boolean)(draftMap.get("draftSubmitted")));
		draft.setDraftSelected((boolean)(draftMap.get("draftSelected")));
		
		int authorId = (int)((LinkedHashMap<T,T>) draftMap.get("author")).get("userId");
		User author = userDao.selectById(authorId);
		draft.setAuthor(author);
		System.out.println("in draft service");
		System.out.println(draft);
		draftDao.saveOrUpdate(draft);
		
		Draft recent = draftDao.getMostRecentByAuthor(authorId);
		System.out.println("Making content List");
		List<LinkedHashMap<T,T>> contentList = (List<LinkedHashMap<T,T>>)draftMap.get("content");
		System.out.println(contentList);
		try{
			contentList.forEach(contentMap -> {
				
			Content content = new Content();
			LinkedHashMap<T,T> attributeMap = (LinkedHashMap<T, T>) contentMap.get("attribute");
			int attributeId = (int)attributeMap.get("attributeId");
			Attribute attribute = attributeDao.selectById(attributeId);
			System.out.println(attribute);
			content.setAttribute(attribute);
			content.setDraft(recent);
			content.setId(recent.getDraftId(), attribute.getAttributeId());
			
			if(contentMap.get("value1") != null) {
				content.setValue1((String)contentMap.get("value1"));
			}
			if(contentMap.get("value2") != null) {
				content.setValue2((String)contentMap.get("value2"));
			}
			try{
				content.setValue3((String)contentMap.get("value3"));
			}catch(ClassCastException c) {
				content.setValue3(((Integer)contentMap.get("value3")).toString());
			}catch(NullPointerException e) {
				
			}
			
			contentDao.saveOrUpdate(content);
			});
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
			
		
	}

	public Draft createEmptyDraft(List<Attribute> attributes) {
		Draft draft = new Draft();
		draft.addAttributes(attributes);
		return draft;
	}
	
	// Helper Function:
	// Map 7 most recent posts of their respective day of the week
	public Map<String,Draft> mapFeatured(){
		int counter = 0;
		List<Draft> allFeatured = allFeatured();
		Map<String, Draft> theSeven = new HashMap<>();

		theSeven.put("Sunday", null);
		theSeven.put("Monday", null);
		theSeven.put("Tuesday", null);
		theSeven.put("Wednesday", null);
		theSeven.put("Thursday", null);
		theSeven.put("Friday", null);
		theSeven.put("Saturday", null);

		for (Draft draft : allFeatured) {
			String day = new SimpleDateFormat("EEEE").format(draft.getDraftUpdated());

			if (theSeven.get(day) == null) {
				theSeven.put(day, draft);
				++counter;
			}
			if (counter == 7) break;
		}
		return theSeven;
	}

	// List 7 most recent posts of their respective day of the week

	public List<Draft> aggregateFeatured() {

		List<Draft> sevenList = new ArrayList<>();
		Map<String, Draft> theSeven = mapFeatured();

		sevenList.add(theSeven.get("Sunday"));
		sevenList.add(theSeven.get("Monday"));
		sevenList.add(theSeven.get("Tuesday"));
		sevenList.add(theSeven.get("Wednesday"));
		sevenList.add(theSeven.get("Thursday"));
		sevenList.add(theSeven.get("Friday"));
		sevenList.add(theSeven.get("Saturday"));

		return sevenList;
	}

	// Lists all selected drafts in order from most recent to oldest
	public List<Draft> allFeatured() {
		return draftDao.selectAllSelected();
	}
	
	// Lists all submitted drafts that aren't selected
	public List<Draft> allSubmittedNotFeatured(){
		return draftDao.selectAllSubmitted();
	}
	
	public void submitDraft(int id) {
		Draft current = draftDao.selectById(id);
		current.setDraftSubmitted(true);
		draftDao.saveOrUpdate(current);
	}
	
	
	// Selects a draft to be featured IF:
	// a) there are submitted drafts
	// b) the selected draft's updatedAt for Todays Day of Week is != Today
	
	public List<Draft> selectFeatured() {
		//a)
		List<Draft> submitted = allSubmittedNotFeatured();
		if(submitted.isEmpty()) {
			return aggregateFeatured();
		}
		
		//b)
		
		Map<String, Draft> oldFeatured = mapFeatured();

		String dayOfWeek = new SimpleDateFormat("EEEE").format(new Date());
		DateTime today = DateTime.now();
		
		Draft checkedDraft = oldFeatured.get(dayOfWeek);
		DateTime checkedDate = new DateTime(checkedDraft.getDraftUpdated());
		
		DateTimeComparator dateTimeComparator = DateTimeComparator.getDateOnlyInstance();
		if(dateTimeComparator.compare(today, checkedDate) > 0) {
			Random rand = new Random();
			Draft winner = submitted.get(rand.nextInt(submitted.size()));
			
			winner.setDraftSelected(true);
			//pick a random draft from submitted and update it to selected
			
			draftDao.saveOrUpdate(winner);
			
			//send the winner congratulations
			emailServ.sendMail(winner.getAuthor().getEmail(), "CONGRATULATIONS",
					"Congratulations! Your draft " + winner.getDraftTitle() + " has been selected to be"
							+ " featured on the SevenDaySoapbox homepage!");
		}
		return aggregateFeatured();
	}
	
	public List<Draft> getDraftsByAuthor(String author) {
		List<Draft> allDrafts = draftDao.selectAll();
		List<Draft> authorDrafts = new ArrayList<>();
		for (Draft d: allDrafts) {
			if (d.getAuthor().getUsername().equals(author)) {
				authorDrafts.add(d);
			}
		}
		return authorDrafts;
	}
}

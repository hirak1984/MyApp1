package pvt.hrk.myapp1.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import pvt.hrk.myapp1.data.ReminderDao;
import pvt.hrk.myapp1.model.Reminder;
import pvt.hrk.myapp1.utils.ConversionUtils;
import pvt.hrk.myapp1.vo.ReminderVO;

@ManagedBean(name = "reminderService")
@ApplicationScoped
public class ReminderService {
	 @ManagedProperty("#{ReminderDao}")
	 private ReminderDao dao;
	 

	public List<ReminderVO> getActiveReminders(){
		Map<String, Object> map = new HashMap<>();
		map.put("complete",false);
		return ConversionUtils.ToReminderVOs(dao.findWhere(map));
	}
	public List<ReminderVO> getArchivedReminders(){
		Map<String, Object> map = new HashMap<>();
		map.put("complete",true);
		return ConversionUtils.ToReminderVOs(dao.findWhere(map));
	}
	
	public List<ReminderVO> getAllReminders(){
		Map<String, Object> map = null;
		return ConversionUtils.ToReminderVOs(dao.findWhere(map));
	}
	
	
	public void create(String text,LocalDateTime dueDate) throws Exception {
		Reminder reminder = new Reminder();
		reminder.setText(text);
		reminder.setDueDate(dueDate);
		dao.insertOrUpdate(reminder);
	}
	public void delete(ReminderVO vo) {
		Reminder reminder = ConversionUtils.ToReminder(vo);
		reminder.setComplete(true);
		dao.insertOrUpdate(reminder);
	}
	public ReminderDao getDao() {
		return dao;
	}
	public void setDao(ReminderDao dao) {
		this.dao = dao;
	}
	public ReminderService() {
		super();
	}
	
}

package pvt.hrk.myapp1.utils;

import java.util.ArrayList;
import java.util.List;

import pvt.hrk.myapp1.model.Reminder;
import pvt.hrk.myapp1.vo.ReminderVO;

public class ConversionUtils {

	
	public static List<ReminderVO> ToReminderVOs(List<Reminder> reminders) {
		List<ReminderVO> vos = new ArrayList<>();
		if(reminders!=null) {
		reminders.forEach(reminder->vos.add(ToReminderVO(reminder)));
		}
		return vos;
	}
	
	public static List<Reminder> ToReminders(List<ReminderVO> vos) {
		List<Reminder> reminders = new ArrayList<>();
		if(vos!=null) {
		vos.forEach(vo->reminders.add(ToReminder(vo)));
		}
		return reminders;
	}

	public static ReminderVO ToReminderVO(Reminder reminder) {
		if(reminder==null) {
			return null;
		}
		ReminderVO vo = new ReminderVO();
		vo.id=reminder.getId();
		vo.text=reminder.getText();
		vo.complete=reminder.isComplete();
		vo.dueDate=reminder.getDueDate();
		return vo;
	}
	
	public static Reminder ToReminder(ReminderVO vo) {
		if(vo==null) {
			return null;
		}
		Reminder reminder = new Reminder();
		reminder.setId(vo.id);
		reminder.setText(vo.text);
		reminder.setComplete(vo.complete);
		reminder.setDueDate(vo.dueDate);
		return reminder;
	}
}

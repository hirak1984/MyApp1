package pvt.hrk.myapp1.views;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import pvt.hrk.myapp1.service.ReminderService;
import pvt.hrk.myapp1.vo.ReminderVO;

@ManagedBean(name="archivedRemindersView")
@ViewScoped
public class ArchivedRemindersView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7092058973082711092L;
	 private List<ReminderVO> reminders;
	    
	 @ManagedProperty("#{reminderService}")
	    private ReminderService service;
	 
	    @PostConstruct
	    public void init() {
	    	reminders = service.getArchivedReminders();
	    }
	     
	    public List<ReminderVO> getReminders() {
	        return reminders;
	    }
	 
	    public void setService(ReminderService service) {
	        this.service = service;
	    }

		}

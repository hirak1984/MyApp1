package pvt.hrk.myapp1.views;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pvt.hrk.myapp1.service.ReminderService;
import pvt.hrk.myapp1.vo.ReminderVO;

@ManagedBean(name="remindersView")
@ViewScoped
public class RemindersView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7092058973082711092L;
	 private String text;
	 private Date value; 
	    
	 @ManagedProperty("#{reminderService}")
	    private ReminderService service;
	 
/*	    @PostConstruct
	    public void init() {
	    	reminders = service.getActiveReminders();
	    }
	     */
	 
	 
	    public List<ReminderVO> getReminders() {
	        return service.getActiveReminders();
	    }
	 
	    public ReminderService getService() {
			return service;
		}

		public void setService(ReminderService service) {
	        this.service = service;
	    }

	    public void delete(ReminderVO reminder) {
	    	if(reminder!=null) {
	    		service.delete(reminder);
	    	}
	    	
	        FacesMessage msg = new FacesMessage("Deleted ",text);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	 
	    public void onAddNew() {
	    	String msg = "Can't add reminder; values null";
	       if(text!=null && value!=null) {
	    	try {
				service.create(text,convertToLocalDateTimeViaMilisecond(value));
				 msg= "New Reminder added";
			} catch (Exception e) {
				 msg=e.getMessage();
			}
	    	 
	       }
	        
	        FacesMessage fm = new FacesMessage(msg);
	        FacesContext.getCurrentInstance().addMessage(null, fm);
	    }
	    private LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
	        return Instant.ofEpochMilli(dateToConvert.getTime())
	          .atZone(ZoneId.systemDefault())
	          .toLocalDateTime();
	    }
	  /*  public void onCellEdit(CellEditEvent event) {
	        Object oldValue = event.getOldValue();
	        Object newValue = event.getNewValue();
	         
	        if(newValue != null && !newValue.equals(oldValue)) {
	            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
	            FacesContext.getCurrentInstance().addMessage(null, msg);
	        }
	    }
*/
		public String getText() {
			return text;
		}

		public void setText(String text) {
			if(text==null || text.length()>200) {
	    		FacesContext.getCurrentInstance().addMessage(null, 
	    		        new FacesMessage(FacesMessage.SEVERITY_FATAL,"input must be not null and less than 200 characters", null));
	    	}else {
	    		this.text =text;	
	    	}
			
		}

		public Date getValue() {
			return value;
		}

		public void setValue(Date value) {
			this.value = value;
		}

		
}

package pvt.hrk.myapp1.views;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import pvt.hrk.myapp1.service.EditorService;

@ManagedBean(name="editorView")
public class EditorView {
	 @ManagedProperty("#{editorService}")
	    private EditorService service;
	 
    public EditorView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EditorService getService() {
		return service;
	}

	public void setService(EditorService service) {
		this.service = service;
	}

	private String text;
     
 
    public String getText() {
        return text;
    }
 
    public void setText(String text) {
        this.text = text;
    }
 
    public void submit() {
    	if(text!=null) {
    		if(text.length()>300) {
    			FacesContext.getCurrentInstance().addMessage(null, 
	    		        new FacesMessage(FacesMessage.SEVERITY_INFO,"Message exceeds max limit of 300 chars", null));
    		}else {
    			try {
    				service.save(text);
    				text=null;
    				FacesContext.getCurrentInstance().addMessage(null, 
    	    		        new FacesMessage(FacesMessage.SEVERITY_INFO,"message saved successfully", null));
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				FacesContext.getCurrentInstance().addMessage(null, 
    	    		        new FacesMessage(FacesMessage.SEVERITY_ERROR,"Couldn't save message. Error details in log", null));
    			}
    		}
    		
    	}else {
    		//do nothing
    	}
    }
}
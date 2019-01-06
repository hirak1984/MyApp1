package pvt.hrk.myapp1.service;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import pvt.hrk.myapp1.data.MessageDao;
import pvt.hrk.myapp1.model.Message;

@ManagedBean(name = "editorService")
@ApplicationScoped
public class EditorService {

	 @ManagedProperty("#{MessageDao}")
	 private MessageDao dao;
	 
	public void save(String text) throws Exception{
		if(text!=null) {
			Message msg = new Message();
			msg.setText(text);
			dao.saveOrUpdateMessage(msg);
		}
		
	}

	public MessageDao getDao() {
		return dao;
	}

	public void setDao(MessageDao dao) {
		this.dao = dao;
	}


}

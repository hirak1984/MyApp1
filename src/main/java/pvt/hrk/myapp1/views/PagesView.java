package pvt.hrk.myapp1.views;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="pages")
@ApplicationScoped
public class PagesView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5964639106446727986L;

	public PagesView() {
		super();
		
	}
	private String page="landing";

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	public void doView(String pageName) {
	    this.page = pageName;
	}
}

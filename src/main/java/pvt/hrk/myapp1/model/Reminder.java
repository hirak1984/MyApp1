package pvt.hrk.myapp1.model;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name= "TREMINDER")   
public class Reminder {

	@Id   
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;   
	private boolean complete=false;
	private String text;
	private LocalDateTime dueDate;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	public static enum WhereClause {
		Active_Only(r->!r.complete), Archived_Only(r->r.complete), All(r->true);
		Predicate<Reminder> p;
		WhereClause(Predicate<Reminder> p){
			this.p = p;
		}
		
		public Predicate<Reminder> get() {
			return p;
		}
	}
}

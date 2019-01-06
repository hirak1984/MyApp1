package pvt.hrk.myapp1.data;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pvt.hrk.myapp1.model.Reminder;
import pvt.hrk.myapp1.utils.HibernateUtils;

@ManagedBean(name = "ReminderDao")
@ApplicationScoped
public class ReminderDao {

	public ReminderDao() {
		super();
	}

	public void insertOrUpdate(Reminder reminder) {
		Session session = HibernateUtils.INSTANCE.SESSION();

		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(reminder);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public List<Reminder> findWhere(Map<String,Object> keyValuepairs) {
		Session session = HibernateUtils.INSTANCE.SESSION();
		try {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			
			CriteriaQuery<Reminder> cr = cb.createQuery(Reminder.class);
			Root<Reminder> root = cr.from(Reminder.class);
			cr = cr.select(root);
			
			if(keyValuepairs!=null && keyValuepairs.size()>0) {
				Predicate[] predicates = new Predicate[keyValuepairs.size()];
				int[] index ={0};
				keyValuepairs.forEach((k,v)->{
					predicates[index[0]]=cb.equal(root.get(k),v);
					index[0]=index[0]+1;
				});
				
				cr = cr.where(predicates);	
			}
			
			cr=cr.orderBy(
					  cb.desc(root.get("dueDate")), 
					  cb.asc(root.get("id"))
					  );

			Query<Reminder> query = session.createQuery(cr);
			List<Reminder> results = query.getResultList();
			return results;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
}

package pvt.hrk.myapp1.data;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pvt.hrk.myapp1.model.Message;
import pvt.hrk.myapp1.utils.HibernateUtils;

@ManagedBean(name = "MessageDao")
@ApplicationScoped
public class MessageDao {

	public MessageDao() {
		super();
	}

	public void saveOrUpdateMessage(Message msg) throws Exception {
		
		Session session = HibernateUtils.INSTANCE.SESSION();

		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(msg);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
}

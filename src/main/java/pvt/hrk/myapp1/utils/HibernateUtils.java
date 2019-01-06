package pvt.hrk.myapp1.utils;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public enum HibernateUtils {

	INSTANCE;
	private SessionFactory sessionFactory = null;

	private HibernateUtils() {
		try {
			File configFile = new File(System.getProperty("CONFIG_FILE"));
			sessionFactory = new Configuration().configure(configFile).buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public Session SESSION() {
		return sessionFactory.openSession();
	}

	public void shutdown() {
		sessionFactory.close();
	}
}

package spring.mvc.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.entity.Knowledge;

@Repository
public class KnowledgeDao {
	private static Logger log = LoggerFactory.getLogger(KnowledgeDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Knowledge> ftsSearchAll(String pattern) {
		log.debug("ftsSearchAll");
		List<Knowledge> list = new ArrayList<Knowledge>();
		try (Session session = sessionFactory.openSession()) {
			list = (List<Knowledge>)session.createSQLQuery("select id, ticket"
					+ ", notes, observations, updatedat, createdat, appid, appversion, clientid, datereported,developer from {h-schema}Knowledge"
					+ " where indexeddata @@ to_tsquery('english', :pattern)"
					+ " ORDER BY updatedat, createdat DESC")
					//+ " where obj.searchableidx @@ (:pattern)")
					.addEntity(Knowledge.class)	
					.setParameter("pattern", pattern)
						.list();
		}
		
		return list;
	}
	
	public Knowledge getById(Long id) {
		log.debug("getById");
		Knowledge knowledge = null;
		try (Session session = sessionFactory.openSession()) {
			List<Knowledge> list = session.createQuery("from Knowledge where id=:id")
					.setParameter("id", id).list();
			log.debug("SQL ran in DB by Id {}" , id);
			if(list.size() > 0) {
				knowledge = list.get(0);
			}
		}
		return knowledge;
	}
	
	public Knowledge save(Knowledge knowledgeParam) {
		Knowledge returnObj = null;
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			if(knowledgeParam.getId() != null && knowledgeParam.getId() > 0) {
				Knowledge knowledgeDb = getById(knowledgeParam.getId());
				if(knowledgeDb != null) {
					log.debug("There is a Knowledge in DB by Id {}" , knowledgeParam.getId());
					knowledgeDb.setUpdatedat(new Timestamp(System.currentTimeMillis()));
					knowledgeDb.setTicket(knowledgeParam.getTicket());
					knowledgeDb.setObservations(knowledgeParam.getObservations());
					knowledgeDb.setNotes(knowledgeParam.getNotes());
					knowledgeDb.setAppid(knowledgeParam.getAppid());
					knowledgeDb.setAppversion(knowledgeParam.getAppversion());
					knowledgeDb.setClientid(knowledgeParam.getClientid());
					knowledgeDb.setDeveloper(knowledgeParam.getDeveloper());
					knowledgeDb.setDatereported(knowledgeParam.getDatereported());
					log.debug("Just before updating DB by Id {}" , knowledgeParam.getId());
					session.saveOrUpdate(knowledgeDb);
					returnObj = knowledgeDb;
				} else {
					log.error("Invalid id {}" , knowledgeParam.getId());
				}
			} else {
				knowledgeParam.setCreatedat(new Timestamp(System.currentTimeMillis()));
				session.saveOrUpdate(knowledgeParam);
				returnObj = knowledgeParam;
			}
			session.getTransaction().commit();
		}
		return returnObj;
	}
}

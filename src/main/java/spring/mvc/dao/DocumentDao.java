package spring.mvc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.entity.BaseDocument;

@Repository
public class DocumentDao implements IDocumentDao<BaseDocument> {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<BaseDocument> searchAll(String pattern) {
		List<BaseDocument> list = new ArrayList<BaseDocument>();
		try (Session session = sessionFactory.openSession()) {
			list = session.createQuery("from basedocument where searchJson like :pattern")
					.setString("pattern", "%"+pattern.toUpperCase()+"%")
					.list();
		}
		return list;
	}
	
	@Override
	public List<BaseDocument> ftsSearchAll(String pattern) {
		List<BaseDocument> list = new ArrayList<BaseDocument>();
		try (Session session = sessionFactory.openSession()) {
			list = session.createSQLQuery("select obj.duKey, obj.searchJson"
					+ ", obj.docType, obj.description from {h-schema}basedocument obj"
					+ " where obj.searchableidx @@ plainto_tsquery(:pattern)")
					//+ " where obj.searchableidx @@ (:pattern)")
						.setParameter("pattern", pattern).list();
		}
		
		return list;
	}
	
	@Override
	public List<BaseDocument> searchNativeQuery(String pattern) {
		List<BaseDocument> list = new ArrayList<BaseDocument>();
		try (Session session = sessionFactory.openSession()) {
			list = session.createSQLQuery("select obj.duKey, obj.searchJson"
					+ ", obj.docType, obj.description from {h-schema}basedocument obj"
					+ " where searchJson like :pattern")
					.setString("pattern", "%"+pattern.toUpperCase()+"%")
					.list();
		}
		
		return list;
	}
	
	@Override
	public List<BaseDocument> searchSpecificDocumentType(String className, String pattern) {
		List<BaseDocument> list = new ArrayList<BaseDocument>();
		try (Session session = sessionFactory.openSession()) {
			list = session.createQuery("from " + className + " where searchJson like :pattern")
					.setString("pattern", pattern)
					.list();
		}
		return list;
	}
	
	@Override
	public List<BaseDocument> findByIds(Set<String> ids) {
		List<BaseDocument> list = new ArrayList<BaseDocument>();
		try (Session session = sessionFactory.openSession()) {
			list = session.createQuery("from basedocument where duKey in (:duKeys)")
					.setParameterList("duKeys", ids)
					.list();
		}
		return list;
	}
	
}

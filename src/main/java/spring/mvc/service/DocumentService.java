package spring.mvc.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.mvc.dao.IDocumentDao;
import spring.mvc.entity.BaseDocument;

@Service
public class DocumentService {

	@Autowired
	private IDocumentDao<BaseDocument> documentDao;
	
	public List<BaseDocument> searchNativeAll(String pattern) {
		List<BaseDocument> list = documentDao.searchNativeQuery(pattern);
		return list;
	}
	
	public List<BaseDocument> searchAll(String pattern) {
		List<BaseDocument> list = documentDao.searchAll(pattern);
		return list;
	}
	
	public List<BaseDocument> ftsSearchAll(String pattern) {
		List<BaseDocument> list = documentDao.ftsSearchAll(pattern);
		return list;
	}
	
	public List<BaseDocument> searchSpecificDocumentType(String docType, String pattern) {
		List<BaseDocument> list = documentDao.searchSpecificDocumentType(docType, pattern);
		return list;
	}
	
	public List<BaseDocument> findByIds(Set<String> ids) {
		List<BaseDocument> list = documentDao.findByIds(ids);
		return list;
	}
}

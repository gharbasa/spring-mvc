package spring.mvc.dao;

import java.util.List;
import java.util.Set;

import spring.mvc.entity.BaseDocument;

public interface IDocumentDao<T extends BaseDocument> {
	public List<T> searchAll(String pattern);
	public List<T> searchSpecificDocumentType(String className, String pattern);
	public List<T> findByIds(Set<String> ids);
	List<T> searchNativeQuery(String pattern);
	List<BaseDocument> ftsSearchAll(String pattern);
}

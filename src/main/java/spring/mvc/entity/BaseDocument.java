package spring.mvc.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "basedocument")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="docType", 
discriminatorType = DiscriminatorType.STRING)
public class BaseDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "duKey", updatable = false, nullable = false)
    private String duKey;
    
	@Column(name = "searchJson")
	private String searchJson;
	
	/**
	@JsonIgnore
	@Column(name = "searchableidx")
	private String searchableidx;
	*/
	
	@Column
	private String docType;
	
    @Column
    private String description;
 
    public String getDuKey() {
		return duKey;
	}

	public void setDuKey(String duKey) {
		this.duKey = duKey;
	}

	public String getSearchJson() {
		return searchJson;
	}

	public void setSearchJson(String searchJson) {
		this.searchJson = searchJson;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	public String getSearchableidx() {
		return searchableidx;
	}

	public void setSearchableidx(String searchableidx) {
		this.searchableidx = searchableidx;
	}*/
    
}
package spring.mvc.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="knowledge")
public class Knowledge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    
	@Column
	private String ticket ="";
	
    @Column
    private String notes = "";
 
    @Column
    private String observations ="";
    
    @Column
    private Timestamp createdat;
    
    @Column
    private Timestamp updatedat;
    
    @Column
    private String appid ="";

	@Column
    private String appversion="";
	
	@Column
    private String clientid="";
    
	@Column
    private String developer="";
	
	@Column 
	private Timestamp datereported = new Timestamp(System.currentTimeMillis());
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
		
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}
	
	public Timestamp getCreatedat() {
		return createdat;
	}

	public void setCreatedat(Timestamp createdat) {
		this.createdat = createdat;
	}

	public Timestamp getUpdatedat() {
		return updatedat;
	}

	public void setUpdatedat(Timestamp updatedat) {
		this.updatedat = updatedat;
	}
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppversion() {
		return appversion;
	}

	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	
	public Timestamp getDatereported() {
		return datereported;
	}

	public void setDatereported(Timestamp datereported) {
		this.datereported = datereported;
	}

}

package com.iif.server.x1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.hxjz.common.core.orm.BaseEntity;

@Entity
@Table(name="tcommunitynote" ,catalog="iif")

/**
 * tcommunitynote实体类
 * @Author LiuM
 * @createTime 2017
 */
public class CommunityNote extends BaseEntity implements java.io.Serializable {

	// Fields    
	private static final long serialVersionUID = -8519687262718908015L;
	private String id;
    private String projectId;
    private String noteId;
    private String userId;
    private String flag;
    
 // Constructors
    public CommunityNote() {
    	
    }
   
	public CommunityNote(String projectId, String noteId, String userId,
			String flag) {
		super();
		this.projectId = projectId;
		this.noteId = noteId;
		this.userId = userId;
		this.flag = flag;
		
	}

	@GenericGenerator(name="generator", strategy="uuid")@Id @GeneratedValue(generator="generator")
    @Column(name="fid", unique=true, nullable=false, length=50)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="noteid")
    public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	@Column(name="userid")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name="fflag")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Column(name="projectid")
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
}
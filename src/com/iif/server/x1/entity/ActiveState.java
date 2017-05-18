package com.iif.server.x1.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.hxjz.common.core.orm.BaseEntity;


/**
 * activeState entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tactivestate" ,catalog="iif"
)

public class ActiveState extends BaseEntity implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields    

     private String id;
     private String projectId;
     private String activeId;
     private String userId;
     private String type;
     private String flag;


    // Constructors

    /** default constructor */
    public ActiveState() {
    }

    
    /** full constructor */
    public ActiveState(String projectId, String activeId, String userId, String type, String flag) {
        this.projectId = projectId;
        this.activeId = activeId;
        this.userId = userId;
        this.type = type;
        this.flag = flag;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid")@Id @GeneratedValue(generator="generator")
    
    @Column(name="fid", unique=true, nullable=false, length=50)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="projectid")

    public String getProjectId() {
        return this.projectId;
    }
    
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    
    @Column(name="activeid")

    public String getActiveId() {
        return this.activeId;
    }
    
    public void setActiveId(String activeId) {
        this.activeId = activeId;
    }
    
    @Column(name="userid", length=50)

    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    @Column(name="ftype", length=50)

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Column(name="fflag")

    public String getFlag() {
        return this.flag;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
    }
   








}
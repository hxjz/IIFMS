package com.iif.server.x1.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.hxjz.common.core.orm.BaseEntity;

@Entity
@Table(name="tcommunityactive" ,catalog="iif")

/**
 * CommunityActive实体类
 * @Author LiuM
 * @createTime 2017
 */
public class CommunityActive extends BaseEntity implements java.io.Serializable {

	// Fields    
	private static final long serialVersionUID = -8519687262718908015L;
	private String id;
    private String projectId;
    private String title;
    private String article;
    private String activeImg;
    private String date;
    private String joinDate;
    private String address;
    private String cost;
    private Integer valid;
    private String createrId;
    private Timestamp createTime;
    private String updaterId;
    private Timestamp updateTime;
    private Integer isDel;

    // Constructors
    public CommunityActive() {
    }

    /** full constructor */
    public CommunityActive(String projectId, String title, String article, String activeImg, String date, String joinDate,String address, String cost, Integer valid, String createrId, Timestamp createTime, String updaterId, Timestamp updateTime, Integer isDel) {
        this.projectId = projectId;
        this.title = title;
        this.article = article;
        this.activeImg = activeImg;
        this.date = date;
        this.joinDate = joinDate;
        this.address = address;
        this.cost = cost;
        this.valid = valid;
        this.createrId = createrId;
        this.createTime = createTime;
        this.updaterId = updaterId;
        this.updateTime = updateTime;
        this.isDel = isDel;
    }
   
    @GenericGenerator(name="generator", strategy="uuid")@Id @GeneratedValue(generator="generator")
    
    @Column(name="fid", unique=true, nullable=false, length=50)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="projectid", length=50)

    public String getProjectId() {
        return this.projectId;
    }
    
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    
    @Column(name="ftitle")

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name="farticle", length=65535)

    public String getArticle() {
        return this.article;
    }
    
    public void setArticle(String article) {
        this.article = article;
    }
    
    @Column(name="factiveimg")

    public String getActiveImg() {
        return this.activeImg;
    }
    
    public void setActiveImg(String activeImg) {
        this.activeImg = activeImg;
    }
    
    @Column(name="fdate", length=50)

    public String getDate() {
        return this.date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    @Column(name="fjoindate", length=50)
    public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Column(name="faddress", length=100)

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name="fcost", length=50)

    public String getCost() {
        return this.cost;
    }
    
    public void setCost(String cost) {
        this.cost = cost;
    }
    
    @Column(name="fvalid")

    public Integer getValid() {
        return this.valid;
    }
    
    public void setValid(Integer valid) {
        this.valid = valid;
    }
    
    @Column(name="createrid", length=50)

    public String getCreaterId() {
        return this.createrId;
    }
    
    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }
    
    @Column(name="fcreatetime", length=19)

    public Timestamp getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    
    @Column(name="updaterid", length=50)

    public String getUpdaterId() {
        return this.updaterId;
    }
    
    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }
    
    @Column(name="fupdatetime", length=19)

    public Timestamp getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
    
    @Column(name="fisdel")

    public Integer getIsDel() {
        return this.isDel;
    }
    
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
   
}
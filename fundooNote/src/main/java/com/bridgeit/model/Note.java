package com.bridgeit.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "note")

public class Note implements Serializable {


	@Id
	@GeneratedValue
	@Column(name = "noteId")
	private Integer noteId;

	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
 
	@Column(name="pinned")
	private boolean pinned;
	
	@Column(name="archive")
	private boolean archive;
	
	@Column(name="color")
	private String color;
	
	@Column(name="trash")
	private boolean trash;
	 
	@Column(name="createStamp")
	private Date createStamp;
	 
	@Column(name="image")
	private String image;
	
	@Column(name="lastModifiedStamp")
	private Date lastModifiedStamp;
	
	@Column(name="remainder")
	private String remainder;
	

	public boolean isPinned() {
		return pinned;
	}

	public void setPinned(boolean pinned) {
		this.pinned = pinned;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isTrash() {
		return trash;
	}

	public void setTrash(boolean trash) {
		this.trash = trash;
	}

	public Date getCreateStamp() {
		return createStamp;
	}

	public void setCreateStamp(Date createStamp) {
		this.createStamp = createStamp;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getLastModifiedStamp() {
		return lastModifiedStamp;
	}

	public void setLastModifiedStamp(Date lastModifiedStamp) {
		this.lastModifiedStamp = lastModifiedStamp;
	}

	public String getRemainder() {
		return remainder;
	}

	public void setRemainder(String remainder) {
		this.remainder = remainder;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", title=" + title + ", description=" + description + ", pinned=" + pinned
				+ ", archive=" + archive + ", color=" + color + ", trash=" + trash + ", createStamp=" + createStamp
				+ ", image=" + image + ", lastModifiedStamp=" + lastModifiedStamp + ", remainder=" + remainder
				+ ", user=" + user + "]";
	}

	@ManyToOne
	@JoinColumn(name="id")
	User user;
	
	/*
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
*/
	public Integer getNoteId() {
		return noteId;
	}

	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	

	
}

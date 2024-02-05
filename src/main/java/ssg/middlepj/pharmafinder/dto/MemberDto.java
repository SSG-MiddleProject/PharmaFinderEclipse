package ssg.middlepj.pharmafinder.dto;

import java.io.Serializable;
import java.util.Date;

public class MemberDto implements Serializable {
	
	private int id;
	private int storeId;
	private String username;
	private String email;
	private String password;
	private short state;
	private short roll;
	private Date createdAt;
	private Date modifiedAt;
	
	public MemberDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public short getRoll() {
		return roll;
	}

	public void setRoll(short roll) {
		this.roll = roll;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", storeId=" + storeId + ", username=" + username + ", email=" + email
				+ ", password=" + password + ", state=" + state + ", roll=" + roll + ", createdAt=" + createdAt
				+ ", modifiedAt=" + modifiedAt + "]";
	}
	
}

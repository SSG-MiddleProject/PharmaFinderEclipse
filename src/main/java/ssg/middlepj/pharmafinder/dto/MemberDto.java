package ssg.middlepj.pharmafinder.dto;

import com.google.protobuf.Timestamp;

public class MemberDto {
	
	private int id;
	private int store_id;
	private String username;
	private String email;
	private String password;
	private int state; /* 0=비활성 1=활성 */
	private int roll; /* 0=약국관리자 1=약국일반 2=고객일반 */
	private Timestamp created_at;
	private Timestamp modified_at;
	
	// 기본생성자
	public MemberDto() {
		
	}
	
	public MemberDto(int id, int store_id, String username, String email, String password, int state, int roll,
			Timestamp created_at, Timestamp modified_at) {
		super();
		this.id = id;
		this.store_id = store_id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.state = state;
		this.roll = roll;
		this.created_at = created_at;
		this.modified_at = modified_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getModified_at() {
		return modified_at;
	}

	public void setModified_at(Timestamp modified_at) {
		this.modified_at = modified_at;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", store_id=" + store_id + ", username=" + username + ", email=" + email
				+ ", password=" + password + ", state=" + state + ", roll=" + roll + ", created_at=" + created_at
				+ ", modified_at=" + modified_at + "]";
	}
	
	
	
}

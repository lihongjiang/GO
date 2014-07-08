package com.netfuture.go.db.contentprovider;

public class SmsInfo {
	private Integer _id;
	private Integer thread_id;
	private String address;
	private Integer m_size;
	private Integer person;
	private Long date;
	private Integer protocol;
	private Integer read;// Ĭ��Ϊ0
	private Integer status;// Ĭ��-1
	private Integer type;
	private Integer reply_path_present;
	private String subject;
	private String body;
	private String service_center;
	private Integer locked;
	private Integer sim_id;
	private Integer error_code;
	private Integer seen;

	public SmsInfo() {
		super();
	}

	public SmsInfo(Integer _id, String address, long date, Integer type,
			String body) {
		super();
		this._id = _id;
		this.address = address;
		this.date = date;
		this.type = type;
		this.body = body;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public Integer getThread_id() {
		return thread_id;
	}

	public void setThread_id(Integer thread_id) {
		this.thread_id = thread_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getM_size() {
		return m_size;
	}

	public void setM_size(Integer m_size) {
		this.m_size = m_size;
	}

	public Integer getPerson() {
		return person;
	}

	public void setPerson(Integer person) {
		this.person = person;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Integer getProtocol() {
		return protocol;
	}

	public void setProtocol(Integer protocol) {
		this.protocol = protocol;
	}

	public Integer getRead() {
		return read;
	}

	public void setRead(Integer read) {
		this.read = read;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getReply_path_present() {
		return reply_path_present;
	}

	public void setReply_path_present(Integer reply_path_present) {
		this.reply_path_present = reply_path_present;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getService_center() {
		return service_center;
	}

	public void setService_center(String service_center) {
		this.service_center = service_center;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Integer getSim_id() {
		return sim_id;
	}

	public void setSim_id(Integer sim_id) {
		this.sim_id = sim_id;
	}

	public Integer getError_code() {
		return error_code;
	}

	public void setError_code(Integer error_code) {
		this.error_code = error_code;
	}

	public Integer getSeen() {
		return seen;
	}

	public void setSeen(Integer seen) {
		this.seen = seen;
	}

}

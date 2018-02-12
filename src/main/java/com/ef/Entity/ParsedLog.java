package com.ef.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ParsedLog")
public final class ParsedLog {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private String ip;
	 private Date date;
	 private String requestsource;
	 
	 
	 public ParsedLog() {}
	 
	 public ParsedLog(String ip, Date date, String requestsource) {
	        this.ip = ip;
	        this.date = date;
	        this.requestsource = requestsource;
    }
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRequestsource() {
		return requestsource;
	}
	public void setRequestsource(String requestsource) {
		this.requestsource = requestsource;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj instanceof ParsedLog) {
            return ((ParsedLog) obj).getDate().equals(getDate())
                    && ((ParsedLog) obj).getIp().equals(getIp());
        }

        return false;
    }

}

package com.ef.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BlockeIps")
public class BlockedIps {
	
		@Id
	    private String ip;
	    private Integer numberofhits;
	    private String message;
	    
	    public BlockedIps() {}
	    
	    public BlockedIps(String ip, Integer numberofhits) {
	        this.ip = ip;
	        this.numberofhits = numberofhits;
	    }
	    
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public Integer getNumberOfHits() {
			return numberofhits;
		}
		public void setNumberOfHits(Integer numberofhits) {
			this.numberofhits = numberofhits;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}


}

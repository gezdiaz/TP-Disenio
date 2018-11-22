package dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NUM_TICKET")
public class NumTicket {

	@Id
	@Column(name = "ID")
	String id;
	
	@Column(name = "NUM")
	Long num;

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}
	
}

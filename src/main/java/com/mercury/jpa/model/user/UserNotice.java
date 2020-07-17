package com.mercury.jpa.model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name ="MERCURY_TB_USER_NOTICE")
public class UserNotice implements Serializable {

	private static final long serialVersionUID = -838198365280976326L;

	@Id
	@Column
	private String idx;
	
	@Column
	private String userIdx;
	
	@Column
	private String userName;
	
	@Column
	private String noticeIdx;
	
	@Column
	private String noticeTitle;

}

package com.gp.framework.domain.user;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Entity
@Table(name = "super")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Super implements Serializable {

	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "end_time")
	private java.util.Date endTime;

	@Column(name = "create_time")
	private java.util.Date createTime;

	@Column(name = "update_time")
	private java.util.Date updateTime;

}

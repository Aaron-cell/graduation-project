package com.gp.framework.domain.user;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Entity
@Table(name = "user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class User  implements Serializable {

	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;

	private String username;

	private String password;

	private String name;

	private String userpic;

	public java.util.Date birthday;

	private String sex;

	private String email;

	private String phone;

	private String qq;

	@Column(name = "create_time")
	private java.util.Date createTime;

	@Column(name = "update_time")
	private java.util.Date updateTime;

}

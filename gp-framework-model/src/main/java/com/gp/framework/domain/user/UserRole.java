package com.gp.framework.domain.user;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Entity
@Table(name = "user_role")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class UserRole  implements Serializable {

	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "create_time")
	private java.util.Date createTime;

	private String creator;

}

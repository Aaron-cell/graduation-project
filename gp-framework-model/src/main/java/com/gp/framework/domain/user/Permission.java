package com.gp.framework.domain.user;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Entity
@Table(name = "permission")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Permission  implements Serializable {
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;

	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "menu_id")
	private Integer menuId;

	@Column(name = "create_time")
	private java.util.Date createTime;

}

package com.gp.framework.domain.user;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Entity
@Table(name = "menu")
public class Menu  implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String code;

	@Column(name = "menu_name")
	private String menuName;

	private String description;


	@Column(name = "create_time")
	private java.util.Date createTime;

	@Column(name = "update_time")
	private java.util.Date updateTime;

}

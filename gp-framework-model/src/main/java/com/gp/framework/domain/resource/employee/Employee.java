package com.gp.framework.domain.resource.employee;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "employee")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Employee  implements Serializable {

	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;

	private String name;

	@Column(name = "identity_card")
	private String identityCard;

	private String phone;

	private String address;

	private java.util.Date birth;
	//薪酬
	private Double salary;
	//职位
	private String position;
	//工作内容
	@Column(name = "job_content")
	private String jobContent;
	//入职时间
	@Column(name = "entry_time")
	private java.util.Date entryTime;
	//创建时间
	@Column(name = "create_time")
	private java.util.Date createTime;

	@Column(name = "update_time")
	private java.util.Date updateTime;

}

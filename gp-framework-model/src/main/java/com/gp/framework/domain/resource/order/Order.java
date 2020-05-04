package com.gp.framework.domain.resource.order;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@ToString
@Table(name = "orderform")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Order implements Serializable {

	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;

	private String name;

	private String type;

	private String handler;
	@Column(name = "desc_id")
	private String descId;

	@Column(name = "create_time")
	private java.util.Date createTime;


}

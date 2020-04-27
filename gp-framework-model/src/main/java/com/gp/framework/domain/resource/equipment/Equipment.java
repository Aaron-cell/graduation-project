package com.gp.framework.domain.resource.equipment;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "equipment")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Equipment  implements Serializable {

	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;

	@Column(name = "equipment_name")
	private String equipmentName;

	@Column(name = "equipment_number")
	private Integer equipmentNumber;

	@Column(name = "equipment_price")
	private Double equipmentPrice;

	@Column(name = "equipment_pic")
	private String equipmentPic;

	private String description;

	@Column(name = "create_time")
	private java.util.Date createTime;

	@Column(name = "update_time")
	private java.util.Date updateTime;

}

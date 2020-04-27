package com.gp.framework.domain.resource.supplier;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@ToString
@Table(name = "supplier")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Supplier  implements Serializable {

	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;

	@Column(name = "supplier_name")
	private String supplierName;

	@Column(name = "registered_address")
	private String registeredAddress;

	@Column(name = "production_address")
	private String productionAddress;

	@Column(name = "responsible_person")
	private String responsiblePerson;

	private String phone;

	private String email;

	private String description;

	@Column(name = "create_time")
	private java.util.Date createTime;

	@Column(name = "update_time")
	private java.util.Date updateTime;

}

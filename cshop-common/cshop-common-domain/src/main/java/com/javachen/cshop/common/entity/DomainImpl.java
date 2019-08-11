package com.javachen.cshop.common.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;

@MappedSuperclass
public class DomainImpl {

	@Id
	@GeneratedValue(generator = "sequenceGenerator")
	@GenericGenerator(name = "sequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		parameters = {
				@Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "ID_SEQUENCE"),
				@Parameter(name = SequenceStyleGenerator.INITIAL_PARAM, value = "1000"),
				@Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1"),
				@Parameter(name = SequenceStyleGenerator.OPT_PARAM, value = "pooled"),
		}
	)
	private Long id;

	@Version
	private int version;

	@CreationTimestamp
	private Date createTime;

	@CreationTimestamp
	private Date updateTime;

}

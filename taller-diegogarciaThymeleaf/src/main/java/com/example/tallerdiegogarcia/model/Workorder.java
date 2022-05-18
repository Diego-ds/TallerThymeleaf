package com.example.tallerdiegogarcia.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.tallerdiegogarcia.validate.WorkOrderValidation;

import java.sql.Timestamp;
import java.time.LocalDate;


/**
 * The persistent class for the workorder database table.
 * 
 */
@Entity
@NamedQuery(name="Workorder.findAll", query="SELECT w FROM Workorder w")
public class Workorder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="WORKORDER_WORKORDERID_GENERATOR",allocationSize = 1, sequenceName="WORKORDER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WORKORDER_WORKORDERID_GENERATOR")
	private Integer workorderid;

	private Timestamp duedate;
	
	@NotNull(groups= {WorkOrderValidation.class})
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate enddate;

	private Timestamp modifieddate;
	
	@Min(value=0,groups= {WorkOrderValidation.class})
	private Integer orderqty;
	
	@Min(value=0,groups= {WorkOrderValidation.class})
	private Integer scrappedqty;
	
	@NotNull(groups= {WorkOrderValidation.class})
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startdate;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productid")
	@NotNull(message="Seleccione o agregue un producto",groups= {WorkOrderValidation.class})
	private Product product;

	//bi-directional many-to-one association to Scrapreason
	@ManyToOne
	@JoinColumn(name="scrapreasonid")
	private Scrapreason scrapreason;
	
	public Workorder() {
	}

	public Integer getWorkorderid() {
		return this.workorderid;
	}

	public void setWorkorderid(Integer workorderid) {
		this.workorderid = workorderid;
	}

	public Timestamp getDuedate() {
		return this.duedate;
	}

	public void setDuedate(Timestamp duedate) {
		this.duedate = duedate;
	}

	public LocalDate getEnddate() {
		return this.enddate;
	}

	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public Integer getOrderqty() {
		return this.orderqty;
	}

	public void setOrderqty(Integer orderqty) {
		this.orderqty = orderqty;
	}

	public Integer getScrappedqty() {
		return this.scrappedqty;
	}

	public void setScrappedqty(Integer scrappedqty) {
		this.scrappedqty = scrappedqty;
	}

	public LocalDate getStartdate() {
		return this.startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Scrapreason getScrapreason() {
		return this.scrapreason;
	}

	public void setScrapreason(Scrapreason scrapreason) {
		this.scrapreason = scrapreason;
	}


}
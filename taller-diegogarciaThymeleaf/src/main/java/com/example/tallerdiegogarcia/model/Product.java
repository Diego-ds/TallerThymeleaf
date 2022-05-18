package com.example.tallerdiegogarcia.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.tallerdiegogarcia.validate.ProductValidation;

/**
 * The persistent class for the product database table.
 *
 */
@Entity
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PRODUCT_PRODUCTID_GENERATOR", allocationSize = 1, sequenceName = "PRODUCT_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_PRODUCTID_GENERATOR")
	private Integer productid;

	@Column(name = "class")
	private String class_;

	private String color;

	private Integer daystomanufacture;

	private Timestamp discontinueddate;

	private String finishedgoodsflag;

	private BigDecimal listprice;

	private String makeflag;

	private Timestamp modifieddate;
	
	@NotBlank(groups= {ProductValidation.class})
	private String name;

	private String productline;
	
	@NotBlank(groups= {ProductValidation.class})
	private String productnumber;

	private Integer reorderpoint;

	private Integer rowguid;

	private Integer safetystocklevel;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate sellenddate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate sellstartdate;
	
	@Min(value=1,groups= {ProductValidation.class})
	private Integer size;

	private BigDecimal standardcost;

	private String style;
	
	@Min(value=1,groups= {ProductValidation.class})
	private Integer weight;


	// bi-directional many-to-one association to Productsubcategory
	@ManyToOne
	@JoinColumn(name = "productsubcategoryid")
	@NotNull(message="Seleccione o agregue una subcategoria",groups= {ProductValidation.class})
	private Productsubcategory productsubcategory;
	// bi-directional many-to-one association to Workorder
	@OneToMany(mappedBy = "product")
	private List<Workorder> workorders;

	public Product() {
	}

	public Workorder addWorkorder(Workorder workorder) {
		getWorkorders().add(workorder);
		workorder.setProduct(this);

		return workorder;
	}

	public String getClass_() {
		return this.class_;
	}

	public String getColor() {
		return this.color;
	}

	public Integer getDaystomanufacture() {
		return this.daystomanufacture;
	}

	public Timestamp getDiscontinueddate() {
		return this.discontinueddate;
	}

	public String getFinishedgoodsflag() {
		return this.finishedgoodsflag;
	}

	public BigDecimal getListprice() {
		return this.listprice;
	}

	public String getMakeflag() {
		return this.makeflag;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public String getName() {
		return this.name;
	}

	public Integer getProductid() {
		return this.productid;
	}	

	public String getProductline() {
		return this.productline;
	}

	public String getProductnumber() {
		return this.productnumber;
	}

	public Productsubcategory getProductsubcategory() {
		return this.productsubcategory;
	}

	public Integer getReorderpoint() {
		return this.reorderpoint;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public Integer getSafetystocklevel() {
		return this.safetystocklevel;
	}

	public LocalDate getSellenddate() {
		return this.sellenddate;
	}

	public LocalDate getSellstartdate() {
		return this.sellstartdate;
	}

	public Integer getSize() {
		return this.size;
	}

	public BigDecimal getStandardcost() {
		return this.standardcost;
	}

	public String getStyle() {
		return this.style;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public List<Workorder> getWorkorders() {
		return this.workorders;
	}


	public Workorder removeWorkorder(Workorder workorder) {
		getWorkorders().remove(workorder);
		workorder.setProduct(null);

		return workorder;
	}

	
	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setDaystomanufacture(Integer daystomanufacture) {
		this.daystomanufacture = daystomanufacture;
	}

	public void setDiscontinueddate(Timestamp discontinueddate) {
		this.discontinueddate = discontinueddate;
	}

	public void setFinishedgoodsflag(String finishedgoodsflag) {
		this.finishedgoodsflag = finishedgoodsflag;
	}

	public void setListprice(BigDecimal listprice) {
		this.listprice = listprice;
	}

	public void setMakeflag(String makeflag) {
		this.makeflag = makeflag;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}


	public void setProductline(String productline) {
		this.productline = productline;
	}



	public void setProductnumber(String productnumber) {
		this.productnumber = productnumber;
	}


	public void setProductsubcategory(Productsubcategory productsubcategory) {
		this.productsubcategory = productsubcategory;
	}

	public void setReorderpoint(Integer reorderpoint) {
		this.reorderpoint = reorderpoint;
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

	public void setSafetystocklevel(Integer safetystocklevel) {
		this.safetystocklevel = safetystocklevel;
	}

	public void setSellenddate(LocalDate sellenddate) {
		this.sellenddate = sellenddate;
	}

	public void setSellstartdate(LocalDate sellstartdate) {
		this.sellstartdate = sellstartdate;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public void setStandardcost(BigDecimal standardcost) {
		this.standardcost = standardcost;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public void setWorkorders(List<Workorder> workorders) {
		this.workorders = workorders;
	}

}
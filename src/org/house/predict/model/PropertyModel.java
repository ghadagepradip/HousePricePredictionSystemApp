package org.house.predict.model;
import java.util.*;
public class PropertyModel {

	private int id;
	private String name;
	private int nbed;
	private int nbath;
	private AreaMasterModel areamodel;
	private AreaSquareFeetModel sqmodel;
	private List<AminityModel> list;
	private DealModel dmodel;
	public DealModel getDmodel() {
		return dmodel;
	}
	public void setDmodel(DealModel dmodel) {
		this.dmodel = dmodel;
	}
	public List<AminityModel> getList() {
		return list;
	}
	public void setList(List<AminityModel> list) {
		this.list = list;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNbed() {
		return nbed;
	}
	public void setNbed(int nbed) {
		this.nbed = nbed;
	}
	public int getNbath() {
		return nbath;
	}
	public void setNbath(int nbath) {
		this.nbath = nbath;
	}
	public AreaMasterModel getAreamodel() {
		return areamodel;
	}
	public void setAreamodel(AreaMasterModel areamodel) {
		this.areamodel = areamodel;
	}
	public AreaSquareFeetModel getSqmodel() {
		return sqmodel;
	}
	public void setSqmodel(AreaSquareFeetModel sqmodel) {
		this.sqmodel = sqmodel;
	}
	
}

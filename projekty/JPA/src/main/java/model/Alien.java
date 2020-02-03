package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the alien database table.
 * 
 */
@Entity
@NamedQuery(name="Alien.findAll", query="SELECT a FROM Alien a")
public class Alien implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer aid;

	private String aname;

	private String tech;

	public Alien() {
	}

	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public String getAname() {
		return this.aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getTech() {
		return this.tech;
	}

	public void setTech(String tech) {
		this.tech = tech;
	}

	@Override
	public String toString() {
		return "Alien [aid=" + aid + ", aname=" + aname + ", tech=" + tech + "]";
	}
	

}
package weather_app.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String region;
	
	@Column(nullable = false)
	private String country;
	
	@Column(nullable = false)
	private double lat;
	
	@Column(nullable = false)
	private double lon;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
	private List<SavedLocation> savedLocation;

	public Location() {
		super();
	}

	public Location(Integer id, String name, String region, String country, double lat, double lon,
			List<SavedLocation> savedLocation) {
		super();
		this.id = id;
		this.name = name;
		this.region = region;
		this.country = country;
		this.lat = lat;
		this.lon = lon;
		this.savedLocation = savedLocation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public List<SavedLocation> getSavedLocation() {
		return savedLocation;
	}

	public void setSavedLocation(List<SavedLocation> savedLocation) {
		this.savedLocation = savedLocation;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", region=" + region + ", country=" + country + ", lat=" + lat
				+ ", lon=" + lon + ", savedLocation=" + savedLocation + "]";
	}
}
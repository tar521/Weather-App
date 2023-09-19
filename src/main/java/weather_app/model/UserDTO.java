package weather_app.model;

import weather_app.util.Constants;

public class UserDTO {
	
	private Integer id;
	private String username;
	private int toleranceChange;
	private String trackedZipcode;
	
	public UserDTO() {}
	public UserDTO(User user, String zipcode) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.toleranceChange = Constants.TOLERANCES[user.getTolerance().ordinal()];
		this.trackedZipcode = zipcode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getToleranceChange() {
		return toleranceChange;
	}
	public void setToleranceChange(int toleranceChange) {
		this.toleranceChange = toleranceChange;
	}
	public String getTrackedZipcode() {
		return trackedZipcode;
	}
	public void setTrackedZipcode(String trackedZipcode) {
		this.trackedZipcode = trackedZipcode;
	}
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", toleranceChange=" + toleranceChange
				+ ", trackedZipcode=" + trackedZipcode + "]";
	}

}

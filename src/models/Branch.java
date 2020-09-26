package models;

public class Branch {

	public String getLibID() {
		return LibID;
	}
	public void setLibID(String libID) {
		LibID = libID;
	}
	public String getLibName() {
		return LibName;
	}
	public void setLibName(String libName) {
		LibName = libName;
	}
	public String getLibLocation() {
		return LibLocation;
	}
	public void setLibLocation(String libLocation) {
		LibLocation = libLocation;
	}
	
	public Branch(String id, String name, String location) {
		this.LibID = id;
		this.LibName = name;
		this.LibLocation = location;
	}
	
	String LibID;
	String LibName;
	String LibLocation;
}

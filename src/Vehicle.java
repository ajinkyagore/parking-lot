public class Vehicle {
  private VehicleType vehicleType;
  private String registrationNo;

  private String color; // Ideally it should be enum

  public Vehicle(VehicleType vehicleType, String registrationNo, String color) {
    this.vehicleType = vehicleType;
    this.registrationNo = registrationNo;
    this.color = color;
  }

  public VehicleType getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  public String getRegistrationNo() {
    return registrationNo;
  }

  public void setRegistrationNo(String registrationNo) {
    this.registrationNo = registrationNo;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }
}

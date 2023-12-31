import java.util.Objects;

public class ParkingSpot {
  private int id;
  private VehicleType slotType;
  private Vehicle parkedVehicle;

  public ParkingSpot(int id, VehicleType slotType) {
    this.id = id;
    this.slotType = slotType;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public VehicleType getSlotType() {
    return slotType;
  }

  public void setSlotType(VehicleType slotType) {
    this.slotType = slotType;
  }

  public Vehicle getParkedVehicle() {
    return parkedVehicle;
  }

  public void setParkedVehicle(Vehicle parkedVehicle) {
    this.parkedVehicle = parkedVehicle;
  }

  public boolean isAvailable() {
    return Objects.isNull(parkedVehicle);
  }
}

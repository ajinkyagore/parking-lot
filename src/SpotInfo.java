public class SpotInfo {
  private int floorId;
  private int slotId;
  private VehicleType vehicleType;

  public SpotInfo(int floorId, int slotId, VehicleType vehicleType) {
    this.floorId = floorId;
    this.slotId = slotId;
    this.vehicleType = vehicleType;
  }

  public int getFloorId() {
    return floorId;
  }

  public void setFloorId(int floorId) {
    this.floorId = floorId;
  }

  public int getSlotId() {
    return slotId;
  }

  public void setSlotId(int slotId) {
    this.slotId = slotId;
  }

  public VehicleType getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }
}

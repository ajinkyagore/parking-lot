import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ParkingLot {

  private final String id;
  private final Map<Integer, ParkingFloor> floors;
  AvailableSpotFinderStrategy spotFinderStrategy;

  public ParkingLot(
      String id,
      Map<Integer, ParkingFloor> floors,
      AvailableSpotFinderStrategy spotFinderStrategy) {
    this.id = id;
    this.floors = floors;
    this.spotFinderStrategy = spotFinderStrategy;
    initializeSpotFinderStrategy();
  }

  private void initializeSpotFinderStrategy() {
    List<SpotInfo> availableSpots =
        floors.values().stream().flatMap(ParkingFloor::getSpotInfos).collect(Collectors.toList());
    spotFinderStrategy.addEmptySpots(availableSpots);
  }

  public void parkVehicle(Vehicle vehicle) {
    Optional<SpotInfo> spotInfo = spotFinderStrategy.getNextAvailableSpot(vehicle.getVehicleType());
    if (spotInfo.isPresent()) {
      int floorId = spotInfo.get().getFloorId();
      int spotId = spotInfo.get().getSlotId();
      floors.get(floorId).parkVehicle(spotId, vehicle);
      System.out.println("Parked vehicle. Ticket ID: " + generateTicket(floorId, spotId));
    } else {
      System.out.println("Parking Lot Full");
    }
  }

  private String generateTicket(int floorId, int spotId) {
    StringBuilder builder = new StringBuilder(id);
    builder.append("_");
    builder.append(floorId);
    builder.append("_");
    builder.append(spotId);
    return builder.toString();
  }

  public void unparkVehicle(String ticketId) {
    String[] tokens = ticketId.split("_");
    boolean valid = tokens.length == 3;
    int floorId = Integer.parseInt(tokens[1]);
    int spotId = Integer.parseInt(tokens[2]);
    if (!id.equals(tokens[0])
        || !floors.containsKey(floorId)
        || !floors.get(floorId).isOccupiedSpot(spotId)) {
      valid = false;
    }
    Vehicle unparkedVehicle = floors.get(floorId).unparkVehicle(spotId);
    if (Objects.isNull(unparkedVehicle)) {
      valid = false;
    }
    if (!valid) {
      System.out.println("Invalid Ticket");
    } else {
      spotFinderStrategy.addEmptySpot(
          new SpotInfo(floorId, spotId, unparkedVehicle.getVehicleType()));
      System.out.println(
          "Unparked vehicle with Registration Number: "
              + unparkedVehicle.getRegistrationNo()
              + " and Color: "
              + unparkedVehicle.getColor());
    }
  }

  public void display(DisplayType displayType, VehicleType vehicleType) {
    floors.values().forEach(parkingFloor -> parkingFloor.display(displayType, vehicleType));
  }
}

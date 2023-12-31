import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParkingFloor {
  private final int id;
  private final Map<Integer, ParkingSpot> slots;
  private final Map<VehicleType, Integer> slotCountByVehicleType;
  private final Map<VehicleType, Set<Integer>> occupiedSlotIds;

  public ParkingFloor(int id) {
    this(id, new HashMap<>());
  }

  public ParkingFloor(int id, Map<Integer, ParkingSpot> slots) {
    this.id = id;
    this.slots = slots;
    occupiedSlotIds = new HashMap<>();
    for (VehicleType vehicleType : VehicleType.values()) {
      occupiedSlotIds.put(vehicleType, new HashSet<>());
    }
    slotCountByVehicleType =
        slots.values().stream()
            .collect(
                Collectors.groupingBy(
                    ParkingSpot::getSlotType, Collectors.reducing(0, e -> 1, Integer::sum)));
  }

  public int getId() {
    return id;
  }

  public void display(DisplayType displayType, VehicleType vehicleType) {
    switch (displayType) {
      case FREE_COUNT:
        int freeCount =
            slotCountByVehicleType.get(vehicleType) - occupiedSlotIds.get(vehicleType).size();
        System.out.println(
            "No. of free slots for " + vehicleType + " on Floor " + id + ": " + freeCount);
        break;
      case FREE_SLOTS:
        String freeSlots =
            slots.values().stream()
                .filter(slot -> slot.getSlotType() == vehicleType)
                .map(ParkingSpot::getId)
                .filter(spotId -> !occupiedSlotIds.get(vehicleType).contains(spotId))
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        System.out.println("Free slots for " + vehicleType + " on Floor " + id + ": " + freeSlots);
        break;
      case OCCUPIED_SLOTS:
        String occupiedSlots =
            occupiedSlotIds.get(vehicleType).stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        System.out.println(
            "Occupied slots for " + vehicleType + " on Floor " + id + ": " + occupiedSlots);
        break;
      default:
        throw new RuntimeException("Invalid display type");
    }
  }

  public void addSlot(ParkingSpot slot) {
    slots.put(slot.getId(), slot);
  }

  public void parkVehicle(int id, Vehicle vehicle) {
    occupiedSlotIds.get(slots.get(id).getSlotType()).add(id);
    slots.get(id).setParkedVehicle(vehicle);
  }

  public Vehicle unparkVehicle(int id) {
    if (!slots.containsKey(id) || slots.get(id).isAvailable()) {
      return null;
    }
    occupiedSlotIds.get(slots.get(id).getSlotType()).remove(id);
    Vehicle vehicle = slots.get(id).getParkedVehicle();
    slots.get(id).setParkedVehicle(null);
    return vehicle;
  }

  public Stream<SpotInfo> getSpotInfos() {
    return slots.values().stream().map(slot -> getSpotInfo(id, slot));
  }

  private SpotInfo getSpotInfo(int floorId, ParkingSpot parkingSpot) {
    return new SpotInfo(floorId, parkingSpot.getId(), parkingSpot.getSlotType());
  }

  public boolean isOccupiedSpot(int spotId) {
    return slots.containsKey(spotId) && !slots.get(spotId).isAvailable();
  }
}

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;

public abstract class AvailableSpotFinderStrategy {
  private final Map<VehicleType, PriorityQueue<SpotInfo>> availableSpots;

  public AvailableSpotFinderStrategy() {
    availableSpots = new HashMap<>();
    for (VehicleType vehicleType : VehicleType.values()) {
      availableSpots.put(vehicleType, new PriorityQueue<>(getSpotComparator()));
    }
  }

  public abstract Comparator<SpotInfo> getSpotComparator();

  public void addEmptySpots(List<SpotInfo> spotInfos) {
    spotInfos.forEach(this::addEmptySpot);
  }

  public void addEmptySpot(SpotInfo parkingSpot) {
    availableSpots.get(parkingSpot.getVehicleType()).offer(parkingSpot);
  }

  public Optional<SpotInfo> getNextAvailableSpot(VehicleType vehicleType) {
    if (!availableSpots.get(vehicleType).isEmpty()) {
      return Optional.of(availableSpots.get(vehicleType).poll());
    }
    return Optional.empty();
  }
}

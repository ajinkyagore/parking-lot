import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DefaultParkingLotFactory implements ParkingLotFactory {

  private static final int TRUCK_COUNT = 1;
  private static final int BIKE_COUNT = 2;

  @Override
  public ParkingLot getParkingLot(String id, int floorCount, int slotsPerFloor) {
    Map<Integer, ParkingFloor> floors =
        IntStream.rangeClosed(1, floorCount)
            .mapToObj(floorId -> getFloor(floorId, slotsPerFloor))
            .collect(Collectors.toMap(ParkingFloor::getId, Function.identity()));
    return new ParkingLot(id, floors, new DefaultSpotFinderStrategy());
  }

  private ParkingFloor getFloor(int id, int slotCount) {
    Map<Integer, ParkingSpot> slots =
        IntStream.rangeClosed(1, slotCount)
            .mapToObj(this::getParkingSlot)
            .collect(Collectors.toMap(ParkingSpot::getId, Function.identity()));
    return new ParkingFloor(id, slots);
  }

  private ParkingSpot getParkingSlot(int slotId) {
    return new ParkingSpot(slotId, getVehicleType(slotId));
  }

  private VehicleType getVehicleType(int slotId) {
    if (slotId <= TRUCK_COUNT) return VehicleType.TRUCK;
    else if (slotId <= BIKE_COUNT + TRUCK_COUNT) return VehicleType.BIKE;
    else return VehicleType.CAR;
  }
}

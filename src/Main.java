import java.util.Objects;
import java.util.Scanner;

public class Main {
  private static ParkingLot parkingLot = null;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ParkingLotFactory factory = new DefaultParkingLotFactory();
    String[] tokens;
    Command command;
    // TODO: Read from file as well
    //        FileReader fileReader = new FileReader("");
    //        BufferedReader reader = new BufferedReader(fileReader);

    while (scanner.hasNext()) {
      tokens = scanner.nextLine().trim().split(" ");
      command = Command.fromText(tokens[0]);
      switch (command) {
        case CREATE_PARKING_LOT:
          if (tokens.length != 4) {
            throw new RuntimeException("Invalid number of parameters to create parking lot");
          }
          parkingLot =
              factory.getParkingLot(
                  tokens[1],
                  Integer.parseInt(tokens[2]),
                  Integer.parseInt(tokens[3])); // Add a factory
          break;
        case PARK_VEHICLE:
          validateIfParkingLotExist();
          if (tokens.length != 4) {
            throw new RuntimeException("Invalid number of parameters for park vehicle");
          }
          VehicleType vehicleType = VehicleType.valueOf(tokens[1]);
          parkingLot.parkVehicle(new Vehicle(vehicleType, tokens[2], tokens[3]));
          break;
        case UNPARK_VEHICLE:
          validateIfParkingLotExist();
          if (tokens.length != 2) {
            throw new RuntimeException("Invalid number of parameters for unpark vehicle");
          }
          parkingLot.unparkVehicle(tokens[1]);
          break;
        case DISPLAY:
          if (tokens.length != 3) {
            throw new RuntimeException("Invalid number of parameters for display");
          }
          parkingLot.display(DisplayType.fromText(tokens[1]), VehicleType.valueOf(tokens[2]));
          break;
        case EXIT:
          break;
        default:
          throw new RuntimeException("Invalid Command");
      }
    }
  }

  private static void validateIfParkingLotExist() {
    if (Objects.isNull(parkingLot)) {
      throw new RuntimeException("Parking Lot does not exist");
    }
  }
}

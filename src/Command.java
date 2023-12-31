public enum Command {
  CREATE_PARKING_LOT("create_parking_lot"),
  PARK_VEHICLE("park_vehicle"),
  UNPARK_VEHICLE("unpark_vehicle"),
  DISPLAY("display"),
  EXIT("exit");

  private String text;

  Command(String text) {
    this.text = text;
  }

  public static Command fromText(String text) {
    return Command.valueOf(text.toUpperCase());
  }
}

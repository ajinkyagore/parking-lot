public enum DisplayType {
  FREE_COUNT,
  FREE_SLOTS,
  OCCUPIED_SLOTS;

  public static DisplayType fromText(String text) {
    return DisplayType.valueOf(text.toUpperCase());
  }
}

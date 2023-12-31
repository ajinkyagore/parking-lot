import java.util.Comparator;

public class DefaultSpotFinderStrategy extends AvailableSpotFinderStrategy {
  public DefaultSpotFinderStrategy() {
    super();
  }

  @Override
  public Comparator<SpotInfo> getSpotComparator() {
    return (spotInfo1, spotInfo2) -> {
      if (spotInfo1.getFloorId() == spotInfo2.getFloorId())
        return Integer.compare(spotInfo1.getSlotId(), spotInfo2.getSlotId());
      return Integer.compare(spotInfo1.getFloorId(), spotInfo2.getFloorId());
    };
  }
}

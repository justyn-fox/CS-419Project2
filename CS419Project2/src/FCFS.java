import java.util.Comparator;
import java.util.List;

/**
 * This class will implement the First Come First Serve 
 * disk scheduling algorithm.
 * 
 * @author ...
 */
public class FCFS implements IDiskAlgorithm {

	@Override
	public int calculateDistance(List<DiskRequest> requests, int headPosition) {
		requests.sort(Comparator.comparingInt(DiskRequest::getTimeOfArrival));
		int totalDistance = 0;

		for (DiskRequest request : requests) {
			totalDistance = Math.abs(headPosition - request.getTrack());
			System.out.println("Request arrived @ " + request.getTimeOfArrival() + "was executed.");
		}
		return totalDistance;
	}
}


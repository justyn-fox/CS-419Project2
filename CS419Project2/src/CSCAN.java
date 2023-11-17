import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class will implement the C-SCAN 
 * disk scheduling algorithm.
 * 
 * @author ...
 */
public class CSCAN implements IDiskAlgorithm {

	@Override
	public int calculateDistance(List<DiskRequest> requests, int headPosition) {
		requests.sort(Comparator.comparingInt(DiskRequest::getTimeOfArrival));

		List<DiskRequest> left = new ArrayList<>();
		List<DiskRequest> right = new ArrayList<>();
		for (DiskRequest request : requests) {
			if (request.getTimeOfArrival() < headPosition) {
				left.add(request);
			} else {
				right.add(request);
			}
		}

		int current_count = 0;
		int distance, current_track;
		for (DiskRequest request : right) {
			current_track = request.getTrack();
			distance = Math.abs(current_track - headPosition);
			current_count += distance;
			headPosition = current_track;
			System.out.println("Time of Arrival: " + request.getTimeOfArrival());
		}

		headPosition = 0;
		current_count += headPosition;

		for (DiskRequest request : left) {
			current_track = request.getTrack();
			distance = Math.abs(current_track - headPosition);
			current_count += distance;
			headPosition = current_track;
			System.out.println("Time of Arrival: " + request.getTimeOfArrival());
		}

		return current_count;
	}
}

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

		int seek_count = 0;
		int distance, cur_track;
		for (DiskRequest request : right) {
			cur_track = request.getTrack();
			distance = Math.abs(cur_track - headPosition);
			seek_count += distance;
			headPosition = cur_track;
			System.out.println("Time of Arrival: " + request.getTimeOfArrival());
		}

		headPosition = 0;
		seek_count += headPosition;

		for (DiskRequest request : left) {
			cur_track = request.getTrack();
			distance = Math.abs(cur_track - headPosition);
			seek_count += distance;
			headPosition = cur_track;
			System.out.println("Time of Arrival: " + request.getTimeOfArrival());
		}

		return seek_count;
	}
}

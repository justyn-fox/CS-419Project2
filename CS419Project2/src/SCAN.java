import java.util.*;

/**
 * This class will implement the SCAN
 * disk scheduling algorithm.
 *
 * @author ...
 */
public class SCAN implements IDiskAlgorithm {

	@Override
	public int calculateDistance(List<DiskRequest> requests, int headPosition) {
		requests.sort(Comparator.comparingInt(DiskRequest::getTimeOfArrival));

		List<DiskRequest> left = new ArrayList<>();
		List<DiskRequest> right = new ArrayList<>();
		for (DiskRequest request : requests) {
			if (request.getTrack() <= headPosition) {
				left.add(request);
			} else {
				right.add(request);
			}
		}

		int current_count = 0;
		int distance, currentTrack;
		for (DiskRequest request : left) {
			currentTrack = request.getTrack();
			distance = Math.abs(currentTrack - headPosition);
			current_count += distance;
			headPosition = currentTrack;
			System.out.println("Process executed: " + request.getTrack());
		}

		Collections.reverse(right);

		for (DiskRequest request : right) {
			currentTrack = request.getTrack();
			distance = Math.abs(currentTrack - headPosition);
			current_count += distance;
			headPosition = currentTrack;
			System.out.println("Process executed: " + request.getTrack());
		}

		return current_count;
	}
}

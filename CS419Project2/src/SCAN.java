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

		int seek_count = 0;
		int distance, cur_track;
		for (DiskRequest request : left) {
			cur_track = request.getTrack();
			distance = Math.abs(cur_track - headPosition);
			seek_count += distance;
			headPosition = cur_track;
			System.out.println("Process executed: " + request.getTrack());
		}

		Collections.reverse(right);

		for (DiskRequest request : right) {
			cur_track = request.getTrack();
			distance = Math.abs(cur_track - headPosition);
			seek_count += distance;
			headPosition = cur_track;
			System.out.println("Process executed: " + request.getTrack());
		}

		return seek_count;
	}
}

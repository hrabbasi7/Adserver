package tek;

/**
 * @author Haroon
 */
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

public class DateUtil {

	/**
	 * If the current time is greater than a campaign's creation time +
	 * duration, then the server's response should be an error indicating that
	 * no active ad campaigns exist for the specified partner.
	 * 
	 * @param creationDateTime
	 * @param campaignDuration
	 * @return
	 */
	public static boolean campaingDuration(LocalDateTime creationDateTime, Long campaignDuration) {
		LocalDateTime time1 = creationDateTime;
		Duration durationSeconds = Duration.ofSeconds(campaignDuration);
		time1 = time1.plus(durationSeconds);
		LocalDateTime time2 = LocalDateTime.now();
		System.out.println("creationTime: " + time1 + " Now: " + time2);
		Duration duration = Duration.between(time1, time2);
		System.out.println("Duration: " + duration.getSeconds());
		return (duration.getSeconds() > 0) ? true : false;
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * // Creating Durations // System.out.println("--- Examples --- ");
	 * 
	 * // Duration oneHours = Duration.ofHours(1); //
	 * System.out.println(oneHours.getSeconds() + " seconds"); // // Duration
	 * oneHours2 = Duration.of(1, ChronoUnit.HOURS); //
	 * System.out.println(oneHours2.getSeconds() + " seconds");
	 * 
	 * // Duration oneSec = Duration.ofSeconds(0); //
	 * System.out.println(oneSec.getSeconds() + " seconds"); // // Duration
	 * oneSec2 = Duration.of(3, ChronoUnit.SECONDS); //
	 * System.out.println(oneSec2.getSeconds() + " seconds");
	 * 
	 * // Test Duration.between
	 * System.out.println("\n--- Duration.between --- ");
	 * 
	 * LocalDateTime oldDate = LocalDateTime.of(2017, Month.JUNE, 6, 14, 15,
	 * 55); LocalDateTime newDate = LocalDateTime.of(2016, Month.NOVEMBER, 9,
	 * 10, 21, 56);
	 * 
	 * // newDate = oldDate.plus(oneSec);
	 * 
	 * // System.out.println(oldDate); // System.out.println(newDate);
	 * 
	 * System.out.println("Result:: " + campaingDuration(oldDate, 120L));
	 * 
	 * // count seconds between dates // Duration duration =
	 * Duration.between(oldDate, newDate);
	 * 
	 * // System.out.println(duration.getSeconds() + " seconds");
	 * 
	 * }
	 */
}

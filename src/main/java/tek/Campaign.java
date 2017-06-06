package tek;
/**
 * @author Haroon
 */
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Campaign {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long partnerId;
	private Long duration;
	private String adContent;
	private LocalDateTime creationDateTime;

	public Campaign() {
	}

	public Campaign(Long partnerId, Long duration, String adContent) {
		this.partnerId = partnerId;
		this.duration = duration;
		this.adContent = adContent;
		this.creationDateTime = LocalDateTime.now();
	}

	public Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getAdContant() {
		return adContent;
	}

	public void setAdContant(String adContent) {
		this.adContent = adContent;
	}

	public LocalDateTime getCreationTime() {
		return creationDateTime;
	}

	public void setCreationTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	@Override
	public String toString() {
		return "Campaign [id=" + id + ", partnerId=" + partnerId + ", duration=" + duration + ", adContent=" + adContent
				+ ", creationDateTime=" + creationDateTime + "]";
	}

	public int compareTo(Campaign d2) {
		return (this.id > d2.id) ? -1 : (this.id < d2.id) ? 1 : 0;
	}
}

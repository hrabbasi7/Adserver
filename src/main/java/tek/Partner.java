package tek;

/**
 * @author Haroon
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Partner {
	
//	@OneToMany(mappedBy = "partner")
//	private Set<Campaign> campaign = new HashSet<>();
//
//	public Set<Campaign> getCampaign() {
//		return campaign;
//	}
//
//	public void setCampaign(Set<Campaign> campaign) {
//		this.campaign = campaign;
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String partnerName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
}

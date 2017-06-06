package tek;
/**
 * @author Haroon
 */
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class AdController {

	/**
	 * 
	 * @param Post request 
	 * {
	 *    "partner_id": "unique_string_representing_partner',
	 *    "duration":
	 *            "int_representing_campaign_duration_in_seconds_from_now"
	 *    "ad_content": "string_of_content_to_display_as_ad"
	 * }
	 * @return JSON Response with appropirate Header and Messages
	 */
	@RequestMapping(value = "/ad", method = { RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<?> addCampaign(WebRequest request) {
		Long partnerId = new Long(request.getParameter("partner_id"));
		Long duration = new Long(request.getParameter("duration"));
		String adContent = request.getParameter("ad_content");
		Campaign campaign = new Campaign(partnerId, duration, adContent);

		// List<Partner> partnerList =
		// partnerRepository.findById(campaign.getPartnerId());
		List<Campaign> campaignList = campaignRepository.findByPartnerId(campaign.getPartnerId());

		if (campaignList.isEmpty()) {

			campaignRepository.save(campaign);

			return new ResponseEntity(new CustomErrorType(
					"New Partner Campaign created for Partner ID : " + campaign.getPartnerId() + " Sucessfully"),
					HttpStatus.CREATED);

		} else {
			// create a comparator object using a Lambda expression
			Comparator<Campaign> compareCampaign = (d1, d2) -> d1.compareTo(d2);

			// Sort the Collection in this case 'campaignList' in reverse order
			Collections.sort(campaignList, Collections.reverseOrder(compareCampaign));

			for (Campaign temp : campaignList) {
				if (DateUtil.campaingDuration(temp.getCreationTime(), temp.getDuration())) {
					campaignRepository.save(campaign);
					return new ResponseEntity(
							new CustomErrorType(
									"Campaign created for Partner ID : " + campaign.getPartnerId() + " Sucessfully"),
							HttpStatus.CREATED);
				}
			}
		}
		return new ResponseEntity(new CustomErrorType(
				"Unable to create Campaign. A Partner ID : " + campaign.getPartnerId() + " Campaign already exsit"),
				HttpStatus.CONFLICT);
	}

	/**
	 * Get Request to return All Active Campaigns related to the Partner
	 * 
	 * @param partnerId
	 * @return List of Active Campgains
	 */
	@RequestMapping(value = "/ad/{partner_id}")
	@ResponseBody
	public ResponseEntity<?> getCampaign(@PathVariable("partner_id") Long partnerId) {
		List<Campaign> campaignList = campaignRepository.findByPartnerId(partnerId);
		if (campaignList.isEmpty()) {
			return new ResponseEntity(new CustomErrorType("No Campaign Found for Partner ID : " + partnerId),
					HttpStatus.NOT_FOUND);
		} else {
			for (Campaign temp : campaignList) {
				if (DateUtil.campaingDuration(temp.getCreationTime(), temp.getDuration())) {
					return new ResponseEntity(
							new CustomErrorType("No Active Campaign Found for Partner id : " + temp.getPartnerId()),
							HttpStatus.NOT_FOUND);
				}
			}
		}
		return new ResponseEntity<>(campaignList, HttpStatus.OK);
	}

	private final PartnerRepository partnerRepository;
	private final CampaignRepository campaignRepository;

	@Autowired
	public AdController(PartnerRepository partnerRepository, CampaignRepository campaignRepository) {
		this.partnerRepository = partnerRepository;
		this.campaignRepository = campaignRepository;
	}

	/**
	 * Default Method called as parameters are not correct
	 * 
	 * @return
	 */
	// @RequestMapping()
	// @ResponseBody
	// public String defaultMethod() {
	// return "Parameters Empty so default method called. Set Parameters
	// accrodingly";
	// }

	/**
	 * Fall back Method
	 *
	 * @return
	 */
	// @RequestMapping("*")
	// @ResponseBody
	// public String fallbackMethod() {
	// return "404 : fallback method called ";
	// }
}

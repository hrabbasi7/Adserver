package tek;

/**
 * @author Haroon
 */

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "campaign", path = "campaign")
public interface CampaignRepository extends PagingAndSortingRepository<Campaign, Long> {

	List<Campaign> findByPartnerId(@Param("partnerId") Long partnerId);
//	List<Campaign> findByDuration(@Param("duration") Long duration);
	
}

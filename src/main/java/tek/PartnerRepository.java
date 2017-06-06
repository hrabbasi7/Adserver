package tek;

/**
 * @author Haroon
 */

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "partner", path = "partner")
public interface PartnerRepository extends PagingAndSortingRepository<Partner, Long> {

	List<Partner> findById(@Param("Id") Long Id);

}

package com.miracle.claims.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.miracle.claims.beans.Claim;
@Repository
public interface ClaimsRepository extends MongoRepository<Claim, String> {

	public Claim deleteByServiceProviderClaimId(long serverProviderClaimId);
	public Claim findByServiceProviderClaimId(long serverProviderClaimId);
	@Query("{facility_id : ?0}")
	public List<Claim> findByFacilityId(String facilityId);
	
	@Query(value= "{}",fields="{claim_status : 1}")
	public List<Claim> findClaimsbyStatus();
	
	@Query("{claim_status : ?0}")
	public List<Claim> findByStatus(String claimStatus);
	
	@Query("{claim_type : ?0}")
	public List<Claim> findByType(String claimType);
	
	@Query("{document_type : ?0}")
	public List<Claim> findByDocType(String claimType);
	
	public Claim findByCreatorId(String creatorId);
	
	@Query("{claim_id: ?0}")
	public Claim findByCustomerClaimId(String claimId);
	
	@Query("{create_date :?0}")
	public List<Claim> findByCreatedDate(String createdDate);
	
	@Query("{closed_date :?0}")
	public List<Claim> findByClosedDate(String closedDate);
//	@Query("{claimedAmount: ?0, claimStatus: ?1}")
//	public List<Claim> findByClaimedAmountandStatus(String claimedAmount, String claimStatus);
		

}
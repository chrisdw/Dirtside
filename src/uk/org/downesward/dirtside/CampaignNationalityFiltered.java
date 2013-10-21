package uk.org.downesward.dirtside;

/**
 * Defines a list that can be filtered down on campaign and nationality.
 * 
 * @author chrisdw
 *
 */
public interface CampaignNationalityFiltered {
	Integer getCampaignId();
	void setCampaignId(Integer campaignId);
	Integer getNationalityId();
	void setNationalityId(Integer nationalityId);
}

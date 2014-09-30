package modelLogic;

import java.util.List;

import modelData.Contribution;
import dao.ContributionDAO;

public class GetContributionListLogic{
	public List<Contribution> execute(){
		ContributionDAO dao = new ContributionDAO();
		List<Contribution> contributionList = dao.findAll();
		return contributionList;
	}
}

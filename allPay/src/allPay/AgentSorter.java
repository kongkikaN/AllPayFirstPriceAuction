package allPay;

import java.util.Comparator;

class AgentSorterById implements Comparator<Agent>{
	public int compare(Agent a, Agent b) {
		
		int returnVal = 0;
		if (a.getId() < b.getId()) {
			returnVal = -1;
		}
		else if (a.getId() > b.getId()) {
			returnVal = 1;
		}
		else {
			returnVal = 0;
		}
		return returnVal;
	}
}

class AgentSorterByBid implements Comparator<Agent>{
	
public int compare(Agent a, Agent b) {
		
		int returnVal = 0;
		if (a.getBid() < b.getBid()) {
			returnVal = -1;
		}
		else if (a.getBid() > b.getBid()) {
			returnVal = 1;
		}
		else {
			returnVal = 0;
		}
		
		return returnVal;
	}
}
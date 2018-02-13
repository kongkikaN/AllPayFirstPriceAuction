package allPay;


public class Agent {
	
	int id;
	int budget;
	int utility;
	int bid;
	//int [] prizeHeWins;
	
	//the agent decides by his own his utility
	public Agent(int id, int budget, int bid) {
		this.budget = budget;
		this.id = id;
		this.setUtility(this.bid, 0);
		this.utility = this.getUtility();
		//System.out.println(this.id + "'s" + "utility is " + this.getUtility());
		this.bid = bid;
		//this.prizeHeWins = new int[] {0, 0};	//0 if he doesn't win the i'th prize, 1 if he wins the i'th prize
	}
	
	//geters and setters
	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		if (bid <= budget) {
			this.bid = bid;
		}
		else {
			this.bid = 0;
		}
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUtility() {
		return utility;
	}

	public void setUtility(int value, int priceOfBids) {
		if(((value - priceOfBids) > 0) && this.getBudget() > priceOfBids) {
			this.utility = value - priceOfBids;
		}
		else {
			this.utility = 0;
			this.bid = 0;
		}
	}
	
	public void setUtility(int k) {
		this.utility = k;
	}
}

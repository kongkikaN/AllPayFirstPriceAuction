package allPay;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class IterativeBestResponse {
	
	public static void iterBestResp(Prize p, ArrayList<Agent> agents, String s) {
		int v1 = p.getV1();
		int v2 = p.getV2();
		
		int day = 0;
		//create another list of agents
		int i = 0;
		while(true) {
			day++;
			//create a copy of agents
			ArrayList <Agent> agentsCopy = agents;
			
			//while nothing has changed
			//pick an agent
			//index through agents
			Agent agent = agents.get(i%agents.size());
			
			int winningPrize = winning(agent, agentsCopy);
			int agentId = agent.getId();
			//System.out.println("Winning prize is " + winningPrize + " for agent id " + agentId);
			
			agentsCopy = sortAgents(agentsCopy);
			
			//if he is winning first prize
			//set his bid equal to second winning prize bid, change his utility
			if (winningPrize == 0) {
				
				if(agent.getBudget() >= v1) {
					if(agentId < agentsCopy.get(1).getId()) {
						agent.setBid(agentsCopy.get(1).getBid());
						agent.setUtility(v1, agent.getBid());
					}
					else {
						agent.setBid(agentsCopy.get(1).getBid() + 1);
						agent.setUtility(v1, agent.getBid());
					}
				}
			}
			else if(winningPrize == 1) {
				int u1, u2, bid1, bid2;
				//keep winning second prize or go for the better one?
				if (agent.getBudget() >= v1) {
					if (agentId > agentsCopy.get(0).getId()) {
						//raise bid by one
						bid1 = agentsCopy.get(0).getBid() + 1;
						u1 = v1 - bid1;
					}
					else {
						bid1 = agentsCopy.get(0).getBid();
						u1 = v1 - bid1;
					}
					
					bid2 = agentsCopy.get(2).getBid();
					u2 = v2 - bid2;
					
					if (u1 >= u2) {
						agent.setBid(bid1);
						agent.setUtility(u1);
					}
					else {
						agent.setBid(bid2);
						agent.setUtility(u2);
					}
				}
			}
			else {	//check utility for second prize
				int secondPrizeWinningAgentId = agentsCopy.get(1).getId();
				int secondPrizeWinningAgentBid = agentsCopy.get(1).getBid();
				
				int bid1 = 0, bid2 = 0, u1 = 0, u2 = 0;
				
				if (agent.getId() > secondPrizeWinningAgentId) {
					//tie breaking rules
					bid1 = secondPrizeWinningAgentBid + 1;
					u1 = v2 - bid1;
				}
				else {
					bid1 = secondPrizeWinningAgentBid;
					u1 = v2 - bid1;
				}
				
				//if you still aren't winning anything
				bid2 = 0;
				u2 = 0;
				if (u1 > u2) {
					agent.setBid(bid1);
					agent.setUtility(u1);
				}
				else {
					agent.setBid(bid2);
					agent.setUtility(u2);
				}
			}
			printAgents(agents, i, s);
			i++;
			
			
			
			if (day == 5000) {
				break;
			}
		}
	}
	
	public static int winning(Agent agent, ArrayList <Agent> agentsCopy) {
		agentsCopy = sortAgents(agentsCopy);
		int index = agentsCopy.indexOf(agent);
		if(index == 0)
			return 0;		//if he is winning first prize
		else if (index == 1)
			return 1;		//if he is winning second prize
		else return 2;		//if he is winning no prize
	}
	
	
	/*
	 * prints agents
	 */
	public static void printAgents(ArrayList<Agent> agents, int i, String s) {
		
		String str = i + " ";
		for (Agent a : agents) {
			str += a.getBid() + " ";
		}
		
		File case1 = new File(s+".txt");
		try {
			if (!case1.exists()) {
				case1.createNewFile();
			}
			PrintWriter out = new PrintWriter(new FileWriter(case1, true));
			
			out.println(str);
			out.close();
		}
		catch(IOException ex){
			System.out.println("Error!");
		}
	}
	/*
	 * sorts agents first by id, then by Bid
	 * returns ArrayList of agents
	 */
	public static ArrayList<Agent> sortAgents(ArrayList<Agent> agents){
		Collections.sort(agents, new AgentSorterById());
		Collections.sort(agents, new AgentSorterByBid());
		return agents;
	}
}

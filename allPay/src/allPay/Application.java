package allPay;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Application {
	public static int n = 4; //number of agents
	public static void main(String[] args) {
		
		
		//test cases:
		ArrayList <Prize> prize = new ArrayList<Prize>();
		ArrayList <Agent> agents = new ArrayList<Agent>();
		
		prize.add(new Prize(999, 1));
		prize.add(new Prize(900, 100));
		prize.add(new Prize(800, 200));
		prize.add(new Prize(700, 300));
		prize.add(new Prize(600, 400));
		prize.add(new Prize(501, 499));
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Input a number 0 - 3 \n");
		System.out.println("0 -> for all agents : budget = 1000, bid = 0 on start");
		System.out.println("1 -> for all agents : budget = 1000, bid = random [0, 1000]");
		System.out.println("2 -> for agent1 : budget = 1000, bid = 0, for others : budget = 1000/2, bid = 0");
		System.out.println("3 -> for agent1 : budget = 1000, bid = 0, for others : budget = 1000/4, bid = 0");
		System.out.println("4 -> for agent1 : budget = 1000, bid = 0, for others : budget = 1000/4, bid = random");
		int s = in.nextInt();
		
		//for each test case -> run the iterative best response
		for (int i = 0; i < prize.size(); i++) {
			//create workspace
			agents = initAgents(s);
			
			System.out.println("Test Case : " + i);
			IterativeBestResponse.iterBestResp(prize.get(i), agents, "testCase"+i);
			
			//clear workspace for next test case
			agents.clear();
		}
	}
	
	//create workspace - initialize agents
			public static ArrayList<Agent> initAgents(int s){
				ArrayList <Agent> generatedAgents = new ArrayList<Agent>();
				Random r = new Random();
				
				if (s == 0) {
					//initialize agents with budget = 1000 and bid = 0
					for (int j = 0; j < n; j++) {
						generatedAgents.add(new Agent(j, 1000, 0));
					}
					return generatedAgents;
				}
				if (s == 1) {
					//initialize agents with budget = 1000 and random bid
					for (int j = 0; j < n; j++) {
						generatedAgents.add(new Agent(j, 1000, r.nextInt(1000)));
					}
					return generatedAgents;
				}
				if (s == 2) {
					/*
					 * agent1 -> budget = 1000, bid = 0;
					 * agent2 -> budget = 500, bid = 0;
					 * agent3 -> budget = 500, bid = 0;
					 * agent4 -> budget = 500, bid = 0;
					 */
					System.out.println("asdasdasdsd");
					for (int j = 0; j < n; j++) {
						if (j == 0)
							generatedAgents.add(new Agent(j, 1000, 0));
						else
							generatedAgents.add(new Agent(j, 1000/2, 0));
					}
					return generatedAgents;
				}
				if (s == 3) {
					/*
					 * agent1 -> budget = 1000, bid = 0;
					 * agent2 -> budget = 250, bid = 0;
					 * agent3 -> budget = 250, bid = 0;
					 * agent4 -> budget = 250, bid = 0;
					 */
					for (int j = 0; j < n; j++) {
						if (j == 0)
							generatedAgents.add(new Agent(j, 1000, 0));
						else
							generatedAgents.add(new Agent(j, 1000/4, 0));
					}
					return generatedAgents;
				}
				if (s == 4) {
					/*
					 * agent1 -> budget = 1000, bid = random[0, 1000];
					 * agent2 -> budget = 250, bid = [0, 250];
					 * agent3 -> budget = 250, bid = [0, 250];
					 * agent4 -> budget = 250, bid = [0, 250];
					 */
					for (int j = 0; j < n; j++) {
						if (j == 0)
							generatedAgents.add(new Agent(j, 1000, r.nextInt(1000)));
						else
							generatedAgents.add(new Agent(j, 1000/4, r.nextInt(1000)));
					}
					return generatedAgents;
				}
				return null;
			}
}

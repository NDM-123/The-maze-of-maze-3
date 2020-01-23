package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import gameClient.Fruit;
import gameClient.Robot;


class testRobotAndFruitAndGraph {
	public game_service game;
	public DGraph gr;
	@Test
	void test() {         //test constructor fruit
		this.game = Game_Server.getServer(2);
		Iterator<String> f_iter = game.getFruits().iterator();
		while(f_iter.hasNext()) {
			Fruit fr = new Fruit(f_iter.next());
		}
	}

	@Test
	void test2() {         //test constructor robot
		this.game = Game_Server.getServer(6);
		List<String> robots = game.getRobots();
		for(int i=0;i<robots.size();i++) {      
			String robot_json = robots.get(i);
			Robot rob = new Robot(robot_json);
		}
	}
	@Test
	void test3() {         //test constructor init graph from game
		this.game = Game_Server.getServer(5);
		String g = game.getGraph();	
		this.gr = new DGraph();
		this.gr.init(g);
	}
	
	
	
	
}

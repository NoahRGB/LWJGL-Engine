package main;

import engine.Engine;

public class Main {
	public void startGame() {
		Engine engine = new Engine(500, 500, "Testing");
		engine.start();
	}
	
	public static void main(String[] args) {
		new Main().startGame();
	}

}
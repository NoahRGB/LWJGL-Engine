package engine;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import engine.Engine;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWErrorCallback;

@SuppressWarnings("unused")
public class Engine implements Runnable {

	private Renderer renderer = new Renderer();
	private Window window;
	
	private final Thread gameLoopThread;
	
	public Engine(int width, int height, String title) {
		this.gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
		this.renderer = new Renderer();
		this.window = new Window(width, height, title);
	}
	
	public void start() {
		gameLoopThread.start();
	}
	
	@Override
	public void run() {
		init();
		gameLoop();
		dispose();
	}
	
	public void gameLoop() {
		while ( !window.shouldClose() ) {
			update();
			render();
		}
	}
	
	private void update()  {
		
	}

	private void render() {
		renderer.render();
		window.update();
	}

	private void init() {
		//glfw
		GLFWErrorCallback.createPrint(System.err).set();
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");
			
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); 
		
		window.create();
		
		GL.createCapabilities();
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
	}
	
	public void dispose() {
		window.destroy();
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
}

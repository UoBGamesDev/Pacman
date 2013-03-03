import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Main extends BasicGame
{

	private List<GameComponent> gameComponents = new ArrayList<GameComponent>();
	private List<GameComponent> toDelete = new ArrayList<GameComponent>();
	private static GameContainer gc;
	private static int update = 0;
	private GameState gameState = GameState.Menu;
	
	public static GameContainer getGc()
	{
		return gc;
	}

	public static final int TileSize = 20;

	public Main()
	{
		super("Pacman");
	}

	@Override
	public void init(GameContainer gc) throws SlickException
	{
		Main.gc = gc;
	}
	
	public void loadGame() throws SlickException
	{
		TiledMap map = new TiledMap("content/map.tmx");
		gameComponents.clear();
		toDelete.clear();
		
		WallCreator.loadWalls(this, map);
		BlobSpawner.addBlobs(map, this);
		CollisionChecker collisionChecker = new CollisionChecker(this);
		gameComponents.add(new MapRender(map));
		CharacterSpawner.spawnCharacters(this, collisionChecker, map);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		switch(gameState)
		{
		case Game:
			
			update++;
			for (GameComponent component : gameComponents)
			{
				component.update(gc, delta);
			}
			
			for(GameComponent delete : toDelete)
			{
				gameComponents.remove(delete);
			}
			
			toDelete.clear();
			break;
		case Menu:
			
			if(gc.getInput().isKeyPressed(Input.KEY_ENTER))
			{
				loadGame();
				gameState = GameState.Game;
			}
			
			break;
		
		}


	}

	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		switch(gameState)
		{
		case Game:
			for (GameComponent component : gameComponents)
			{
				component.render(gc, g);
			}
			break;
		case Menu:
			g.drawString("Press Enter to Start", 10, 10);
			break;
		default:
			break;
		
		}

	}

	public static void main(String[] args) throws SlickException
	{
		AppGameContainer app = new AppGameContainer(new Main());

		app.setShowFPS(false);
		app.setDisplayMode(600, 400, false);
		app.start();
	}

	public void addGameComponent(GameComponent gc)
	{
		gameComponents.add(gc);
	}
	
	public List<GameComponent> getGameComponents()
	{
		return gameComponents;
	}
	
	public static int getUpdate()
	{
		return update;
	}
	
	public void removeComponent(GameComponent g)
	{
		toDelete.add(g);
	}
	
	public void restart()
	{
		gameState = GameState.Menu;
	}

}
package map;

import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

/**
 * Map instance
 */
public class Map implements TileBasedMap {

    private String name;
    private TiledMap map;


    public Map(TiledMap tiledMap, String mapName) {
        // TODO: Many settings doesn't loaded
        this.map = tiledMap;
        this.name = mapName;
    }

    /**
     * Get the width of the tile map. The slightly odd name is used
     * to distinguish this method from commonly used names in game map.
     *
     * @return The number of tiles across the map
     */
    @Override
    public int getWidthInTiles() {
        return map.getWidth();
    }

    /**
     * Get the height of the tile map. The slightly odd name is used
     * to distinguish this method from commonly used names in game map.
     *
     * @return The number of tiles down the map
     */
    @Override
    public int getHeightInTiles() {
        return map.getHeight();
    }

    /**
     * Notification that the path finder visited a given tile. This is
     * used for debugging new heuristics.
     *
     * @param x The x coordinate of the tile that was visited
     * @param y The y coordinate of the tile that was visited
     */
    @Override
    public void pathFinderVisited(int x, int y) {

    }

    /**
     * Check if the given location is blocked, i.e. blocks movement of
     * the supplied mover.
     *
     * @param context The context describing the path finding at the time of this request
     * @param tx      The x coordinate of the tile we're moving to
     * @param ty      The y coordinate of the tile we're moving to
     * @return True if the location is blocked
     */
    @Override
    public boolean blocked(PathFindingContext context, int tx, int ty) {
        return false;
    }

    /**
     * Get the cost of moving through the given tile. This can be used to
     * make certain areas more desirable. A simple and valid implementation
     * of this method would be to return 1 in all cases.
     *
     * @param context The context describing the path finding at the time of this request
     * @param tx      The x coordinate of the tile we're moving to
     * @param ty      The y coordinate of the tile we're moving to
     * @return The relative cost of moving across the given tile
     */
    @Override
    public float getCost(PathFindingContext context, int tx, int ty) {
        return 1.0f;
    }

}

package game.entity.monster;

import java.util.Random;

import game.entity.types.Level;
import game.entity.types.Tile;
import game.util.Direction;
import game.util.Mover;
import game.view.gfx.SpriteLoader;
/**
 * @author Adrian Roberts (adrian.roberts@student.uib.no)
 * 
 */
public class HøyreMonster extends AbstractMonster {
	private Direction startDir;
	public HøyreMonster(Level level, SpriteLoader loader, int column, int row) {
		super(column, row, level, loader, new Random().nextInt(6));
		startDir = Direction.NORTH;
	}

	@Override
	public void tick() {		/* Gå tilfeldig retning hver tick 44. tick
	 * Dere bør gi deres monstre mer underholdende kommandoer. */


		/* Bare gjør noe hver nte tick. */
		int nteTick = 44;
		if((tickcounter++ % nteTick) != 0){
			return;
		}
		//System.out.println("TICK " + tickCounter/nteTick);


		if(facingDirection == null){
			setDirection(startDir);

		}

		for(int i = 0; i < Direction.values().length; i++){
			//System.out.println(facingDirection + " dir " );
			int[] pos = Mover.position(facingDirection, getColumn(), getRow());
			Tile place = level.tileAt(pos[Mover.COLUMN], pos[Mover.ROW]);

			if(null != place && place.isWalkable() && !place.isLethal()){
				this.move(facingDirection, level);
				//System.out.println("moved in d: " + facingDirection);
				break;
			}
			else {
				facingDirection = Mover.turnRight(facingDirection);
			}
		}

	}


	@Override
	public byte getPriority() {
		return 0;
	}

}

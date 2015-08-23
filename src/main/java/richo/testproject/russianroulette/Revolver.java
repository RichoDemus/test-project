package richo.testproject.russianroulette;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by Richo on 2014-12-06.
 */
public class Revolver
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final Queue<Bullet> cylinder;
	private final int cylinderSize;
	private final Random random = new Random();

	public Revolver(int cyliderSize)
	{
		this.cylinderSize = cyliderSize;
		cylinder = new LinkedList<>();
		reload();
	}

	public void reload()
	{
		cylinder.clear();
		cylinder.add(new LiveBullet());
		while(cylinder.size() < cylinderSize)
		{
			cylinder.add(new DudBullet());
		}
	}

	public void spin()
	{
		final int loopCount = random.nextInt(10000);
		for(int i = 0; i < loopCount; i++)
		{
			rotateCylinderOneStep();
		}
	}

	private void rotateCylinderOneStep()
	{
		final Bullet bullet = cylinder.poll();
		cylinder.add(bullet);
	}

	public boolean fire()
	{
		final Bullet bullet = cylinder.poll();
		cylinder.add(bullet);
		return bullet.fire();
	}
}

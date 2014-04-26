package richo.testproject.jmx;

/**
 * MBean interface
 */
public interface TestMBean
{
	/**
	 * Set Integer value
	 */
	void setInteger(int value);

	/**
	 * Get Integer value
	 * @return the value
	 */
	int getInteger();

	/**
	 * Doubles the Integer
	 * @return the result
	 */
	int doDoubleInteger();
}

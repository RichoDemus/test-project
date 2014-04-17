package richo.testproject.jms;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by tjerngrr on 2014-04-17.
 */
class Counters
{
	static final AtomicLong numberOfSentMessages = new AtomicLong();
	static final AtomicLong numberOfReceivedMessages = new AtomicLong();
	static final AtomicLong numberOfReceives = new AtomicLong();
}

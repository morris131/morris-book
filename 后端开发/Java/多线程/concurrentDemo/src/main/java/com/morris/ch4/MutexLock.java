package com.morris.ch4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MutexLock implements Lock {
	
	private Syn syn = new Syn();
	
	private static class Syn extends AbstractQueuedSynchronizer {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected boolean isHeldExclusively() {
			return getState() == 1;
		}
		
		@Override
		protected boolean tryAcquire(int arg) {
			if(compareAndSetState(0, 1)) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}
		
		
		@Override
		protected boolean tryRelease(int arg) {
			if(0 == getState()) {
				throw new IllegalMonitorStateException();
			}
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
		}
		
		Condition newCondition() {
			return new ConditionObject();
		}
		
	}

	@Override
	public void lock() {
		syn.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		syn.acquireInterruptibly(1);

	}

	@Override
	public boolean tryLock() {
		return syn.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return syn.tryAcquireNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		syn.release(1);
	}

	@Override
	public Condition newCondition() {
		return syn.newCondition();
	}

}

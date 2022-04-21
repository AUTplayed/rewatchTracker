package codes.fepi.logic;

public enum ActivityTracker {
	INSTANCE;

	private volatile long lastChanged = 0;
	private volatile long lastChecked = 0;

	public synchronized void changed() {
		lastChanged = System.currentTimeMillis();
	}

	public synchronized boolean hasChanges() {
		if (lastChanged > lastChecked) {
			lastChecked = lastChanged;
			return true;
		}
		return false;
	}

}

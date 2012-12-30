public class SimpleHashMap<K, V> {

	private int DEFAULT_BUCKET_COUNT = 64;

	private AnEntry<K, V>[] buckets;

	@SuppressWarnings("unchecked")
	public SimpleHashMap() {
		buckets = new AnEntry[DEFAULT_BUCKET_COUNT];
	}

	public V get(K key) {
		throwIfKeyNull(key);

		AnEntry<K, V> entry = buckets[bucketIndexForKey(key)];
		while (entry != null && !key.equals(entry.getKey())) {
			entry = entry.getNext();
		}
		return entry != null ? entry.getValue() : null;
	}

	public void put(K key, V value) {
		throwIfKeyNull(key);

		int bucketIndex = bucketIndexForKey(key);
		AnEntry<K, V> entry = buckets[bucketIndex];

		if (entry != null) {
			while (true) {
				if (key.equals(entry.getKey())) {
					entry.setValue(value);
					break;
				} else if (entry.getNext() == null) {
					entry.setNext(new AnEntry<K, V>(key, value));
					break;
				}
				entry = entry.getNext();
			}
		} else {
			// nothing there at all; just shove the new entry in
			buckets[bucketIndex] = new AnEntry<K, V>(key, value);
		}
	}

	private int bucketIndexForKey(K key) {
		int bucketIndex = key.hashCode() % buckets.length;
		return bucketIndex;
	}

	private void throwIfKeyNull(K key) {
		if (key == null) {
			throw new IllegalArgumentException("key may not be null");
		}
	}
}
package healthy;
public class IntegerArray
{
    protected int countPresent;
    protected int maximumGrowth;
    protected Integer[] baseArray;
    public IntegerArray(int size, int growth) {
        maximumGrowth = growth;
        baseArray = new Integer[size];
    }
        public IntegerArray(int size) {
        this(size, Integer.MAX_VALUE);
    }
    // Implementation method to increase the underlying array size.
    protected void growArray(int required) {
        int size = Math.max(required,
            baseArray.length + Math.min(baseArray.length, maximumGrowth));
        Integer[] grown = new Integer[size];
        System.arraycopy(baseArray, 0, grown, 0, baseArray.length);
        baseArray = grown;
    }
    // Append a value to the collection.
    public int add(Integer value) {
        int index = countPresent++;
        if (countPresent > baseArray.length) {
            growArray(countPresent);
        }
        baseArray[index] = value;
        return index;
    }
    
    // Insert a value into the collection.
    public void add(int index, Integer value) {
        if (index >= 0 && index <= countPresent) {
            if (++countPresent > baseArray.length) {
                growArray(countPresent);
            }
            if (index < countPresent) {
                System.arraycopy(baseArray, index, baseArray, index + 1,
                    countPresent - index - 1);
            }
            baseArray[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException("Invalid index value");
        }
    }
    // Remove a value from the collection.
    public void remove(int index) {
        if (index >= 0 && index < countPresent) {
            if (index < --countPresent){
                System.arraycopy(baseArray, index + 1, baseArray, index,
                    countPresent - index);
                baseArray[countPresent] = null;
            }
        } else {
            throw new ArrayIndexOutOfBoundsException("Invalid index value");
        }
    }
    // Make sure we have at least a specified capacity.
    public void ensureCapacity(int min) {
        if (min > baseArray.length) {
            growArray(min);
        }
    }
    // Set the collection empty.
    public void clear() {
        countPresent = 0;
    }
    // Get number of values in collection.
    public int size() {
        return countPresent;
    }
    // Set the size of the collection.
    public void setSize(int count) {
        if (count > baseArray.length) {
            growArray(count);
        } else if (count < countPresent) {
            for (int i = count; i < countPresent; i++) {
                baseArray[i] = null;
            }
        }
        countPresent = count;
    }
    // Get value from the collection.
    public Integer get(int index) {
        if (index < countPresent) {
            return baseArray[index];
        } else {
            throw new ArrayIndexOutOfBoundsException("Invalid index value");
        }
    }
    // Set the value at a position in the collection.
    public void set(int index, Integer value) {
        if (index < countPresent) {
            baseArray[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException("Invalid index value");
        }
    }
    // Convert to an array.
    public Integer[] buildArray() {
        Integer[] copy = new Integer[countPresent];
        System.arraycopy(baseArray, 0, copy, 0, countPresent);
        return copy;
    }
}
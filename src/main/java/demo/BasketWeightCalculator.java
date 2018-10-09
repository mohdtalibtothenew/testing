package demo;

/**
 * @author Dhanendra Kumar
 */
public class BasketWeightCalculator {

    private int totalWeight = 0;

    public void addItem(int itemWeight) {
        totalWeight = totalWeight + itemWeight;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    // Added a new method for the test case
    public boolean greater()
    {
        if(totalWeight>0)
            return true;
        else
            return false;
    }
}

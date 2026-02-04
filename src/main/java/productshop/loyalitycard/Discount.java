package productshop.loyalitycard;

import java.util.ArrayList;
import java.util.List;

public class Discount {

    private List<Action> currentActions;

    private Discount() {
        currentActions = new ArrayList<>();
    }

    private static class DiscountHolder {
        private static final Discount INSTANCE = new Discount();
    }

    public static Discount getInstance() {
        return DiscountHolder.INSTANCE;
    }

    public List<Action> getCurrentActions() {
        return currentActions;
    }
    public void addAction(Action action) {
        currentActions.add(action);
    }
}

package cat.copernic.rodriguez.albert.content;

import java.util.ArrayList;
import java.util.List;

public class MountainUtils {
    public static final List<Mountain> MOUNTAIN_ITEMS = new ArrayList<>();
    public static final String MOUNTAIN_ID_KEY = "item_id";
    private static final int COUNT = 3;
    public static class Mountain {
        public final String mountain_title;
        public final String details;

        private Mountain(String content, String details) {
            this.mountain_title = content;
            this.details = details;
        }
    }

    private static void addItem(Mountain item) {
        MOUNTAIN_ITEMS.add(item);
    }
    static {
        for (int i = 0; i < COUNT; i++) {
            addItem(createMountainAtPosition(i));
        }
    }

    private static Mountain createMountainAtPosition(int position) {
        String newTitle;
        String newDetail;
        switch (position) {
            case 0:
                newTitle = "Aneto";
                newDetail = "Aneto is not in my country, but is near, so some catalans go to there because it's a very beautiful mountain.";
                break;
            case 1:
                newTitle = "Pedraforca";
                newDetail = "It's a very emblematic mountain of Catalonia, because a lot of poems talk about it, and it has a special morphology.";
                break;
            default:
                newTitle = "Sant Llorenç del Munt";
                newDetail = "It's the most visited mountain of my county.";
                break;
        }
        return new Mountain(newTitle, newDetail);
    }
}
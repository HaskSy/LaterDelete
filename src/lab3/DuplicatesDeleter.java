package lab3;

public class DuplicatesDeleter<L> {

    public DuplicatesDeleter() {
    }

    public void deleteDuplicates(Object object) {
        if (object instanceof MarkerCollection) {
            MarkerCollection<L> collection = (MarkerCollection<L>) object;
            L objectType = collection.first();
            if (!(objectType instanceof Comparable)) {
                throw new IllegalArgumentException();
            }
            int sizeEncounter = 0;
            for (int i = 1; i < collection.size();i++) {
                boolean add = false;
                L data = collection.retrieve(i);
                Comparable<L> comparableData = (Comparable<L>) data;
                for (int j = i + 1; j < collection.size(); j++) {
                    L clonedData = collection.retrieve(j);
                    if (comparableData.compareTo(clonedData) == 0 && j != collection.size() - 1) {
                        add = false;
                        break;
                    }
                        add = true;
                }
                if (add) {
                    collection.insert(data, sizeEncounter);
                    sizeEncounter++;
                }
            }
            collection.delete(sizeEncounter, collection.size());

        }

    }
}

package entity.bookType;

import java.util.Comparator;

public class BookNameComparator implements Comparator<BookType> {
    @Override
    public int compare(BookType o1, BookType o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

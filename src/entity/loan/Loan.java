package entity.loan;


import entity.bookType.Audiobook;
import entity.bookType.Book;
import entity.bookType.EBook;

import java.time.LocalDate;
import java.util.List;

public class Loan {
    private static int loanNumber = 1;
    private final int loanId;
    private List<Book> books;
    private List<EBook> eBooks;
    private List<Audiobook> audioBooks;
    //    private List<BookType> loanProducts;
    private LocalDate dateOfLoan;
    private int customerId; // ??

    public Loan(List<Book> books, List<EBook> eBooks, List<Audiobook> audioBooks, LocalDate dateOfLoan, int customerId) {
        this.loanId = loanNumber;
        loanNumber++;
        this.books = books;
        this.eBooks = eBooks;
        this.audioBooks = audioBooks;
        this.dateOfLoan = dateOfLoan;
        this.customerId = customerId;
    }

    public Loan() {
        this.loanId = loanNumber;
        loanNumber++;
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void addEBook(EBook ebook){
        eBooks.add(ebook);
    }

    public void addAudioBook(Audiobook audiobook){
        audioBooks.add(audiobook);
    }

    public int getLoanId() {
        return loanId;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<EBook> getEBooks() {
        return eBooks;
    }

    public void setEBooks(List<EBook> eBooks) {
        this.eBooks = eBooks;
    }

    public List<Audiobook> getAudioBooks() {
        return audioBooks;
    }

    public void setAudioBooks(List<Audiobook> audioBooks) {
        this.audioBooks = audioBooks;
    }

    public LocalDate getDateOfLoan() {
        return dateOfLoan;
    }

    public void setDateOfLoan(LocalDate dateOfLoan) {
        this.dateOfLoan = dateOfLoan;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        StringBuilder loan = new StringBuilder("\n\t ****** Loan " + loanId + " ******" +
                "\n\t -> Date Of Loan: " + dateOfLoan);
        if (books.size() != 0){
            loan.append("\n\n\t\t\t BOOKS: ");
            for (Book book : books) {
                loan.append("\n\t").append(book);
//                String concatString = "\n\t" + book;
//                loan += concatString;
            }
        }

        if (audioBooks.size() != 0){
            loan.append("\n\n\t\t\t AUDIOBOOKS: ");
            for (Audiobook audiobook : audioBooks) {
                loan.append("\n\t").append(audiobook);
            }
        }

        if (eBooks.size() != 0){
            loan.append("\n\n\t\t\t EBOOKS: ");
            for (EBook ebook : eBooks) {
                loan.append("\n\t").append(ebook);
            }
        }
        return loan.toString();
    }
}

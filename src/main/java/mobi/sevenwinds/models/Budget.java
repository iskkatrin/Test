package mobi.sevenwinds.models;

@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int year;
    private int month;
    private int amount;
    private String type;

    private Author author; // Привязка к классу Author

    public Budget(int year, int month, int amount, String type, Author author) {
        this.year = year;
        this.month = month;
        this.amount = amount;
        this.type = type;
        this.author = author;
    }
    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public Author getAuthor() {
        return author;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}

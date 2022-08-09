package GUI.Entities;

import javax.persistence.*;

@Entity
@Table(name = "av")
public class Vocabulary {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "word")
    private String word;
    @Column(name = "html")
    private String detailString;

    public Vocabulary() {

    }

    public Vocabulary(String word, String detailString) {
        this.word = word;
        this.detailString = detailString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDetailString() {
        return detailString;
    }

    public void setDetailString(String detail) {
        this.detailString = detail;
    }

    @Override
    public String toString() {
        return word;
    }
}

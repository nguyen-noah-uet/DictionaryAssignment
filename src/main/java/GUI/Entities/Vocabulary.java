package GUI.Entities;

import javax.persistence.*;

@Entity
@Table(name = "av")
public class Vocabulary {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    @Column(name = "word")
    private String word;
    @Column(name = "html")
    private String detailString;

    public Vocabulary() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Vocabulary(String word, String detailString) {
        this.word = word;
        this.detailString = detailString;
    }


    @Override
    public String toString() {
        return word;
    }
}

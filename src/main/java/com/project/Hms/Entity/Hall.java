package com.project.Hms.Entity;
import javax.persistence.*;

@Entity
public class Hall {
    @Id
    @SequenceGenerator(
            name = "hall_sequence",
            sequenceName = "hall_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hall_sequence"
    )

    private Long hallId;
    private String hallName;
    private String hallGender;
    private Long hallCapacity;

//    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Wing> wings;

    public Hall() {
    }

    public Hall(Long hallId, String hallName, String hallGender) {
        this.hallId = hallId;
        this.hallName = hallName;
        this.hallGender = hallGender;
    }

    public Hall(String hallName, String hallGender) {
        this.hallName = hallName;
        this.hallGender = hallGender;
    }



}

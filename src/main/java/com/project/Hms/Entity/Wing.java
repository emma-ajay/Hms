package com.project.Hms.Entity;

import javax.persistence.*;

@Entity
public class Wing {
    @Id
    @SequenceGenerator(
            name = "wing_sequence",
            sequenceName = "wing_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wing_sequence"
    )

    private Long wingId;
    private String wingName;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "hallId",
//            nullable = false)
//    @JsonIgnore
//    private Hall hall;
//



    public Wing() {
    }

    public Wing(Long wingId, String wingName) {
        this.wingId = wingId;
        this.wingName = wingName;

    }

    public Wing(String wingName) {
        this.wingName = wingName;

    }



}

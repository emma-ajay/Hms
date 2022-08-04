package com.project.Hms.Entity;
import javax.persistence.*;

@Entity
public class Room {
    @Id
    @SequenceGenerator(
            name = "room_sequence",
            sequenceName = "room_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence"
    )

    private Long roomId;
    private String roomNumber;
    private Boolean isFull;
    private Boolean isReserved;



    public Room() {
    }


}

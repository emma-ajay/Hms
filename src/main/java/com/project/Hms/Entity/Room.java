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

    public Room(Long roomId, String roomNumber, Boolean isFull, Boolean isReserved) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.isFull = isFull;
        this.isReserved = isReserved;
    }

    public Room(String roomNumber, Boolean isFull, Boolean isReserved) {
        this.roomNumber = roomNumber;
        this.isFull = isFull;
        this.isReserved = isReserved;
    }
}

package testRoomProject.entity;

import javax.persistence.*;

@Entity
@Table(name="room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status", nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    public BulbStatus status;

    public enum BulbStatus {
        OFF, ON

    }

    public Room() {
    }

    public Room(BulbStatus status) {
        this.status = status;
    }

    public Room(Long id, BulbStatus type) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BulbStatus getStatus() {
        return status;
    }

    public void setStatus(BulbStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}


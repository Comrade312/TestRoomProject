package testRoomProject.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import testRoomProject.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface RoomsRepo extends JpaRepository<Room, Long> {

    Room findById(String username);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Room room set room.status =:status where room.id =:roomId")
    void updateRoomStatus(@Param("roomId") Long roomId, @Param("status") Room.BulbStatus status);



}

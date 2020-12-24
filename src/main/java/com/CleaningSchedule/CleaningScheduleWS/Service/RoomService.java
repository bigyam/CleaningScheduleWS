package com.CleaningSchedule.CleaningScheduleWS.Service;

import com.CleaningSchedule.CleaningScheduleWS.DTO.RoomDTO;
import com.CleaningSchedule.CleaningScheduleWS.Entities.Room;
import com.CleaningSchedule.CleaningScheduleWS.Repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<RoomDTO> getAllRooms() {
        List<Room> roomList = new ArrayList<>();
        roomRepository.findAll().forEach(roomList::add);
        return roomList.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public RoomDTO getRoom(Integer id) {
        Room room = roomRepository.findById(id).get(); //TODO: get() needs a ispresent check
        return this.convertEntityToDto(room);
    }

    public void addRoom(RoomDTO roomDTO) throws Exception{
        if(roomDTO.getId() == null) {
            roomRepository.save(this.convertDtoToEntity(roomDTO));
        } else {
            throw new Exception("Is not new room");
        }
    }

    public RoomDTO updateRoom(RoomDTO roomDTO) {
        Optional<Room> roomOptional = roomRepository.findById(roomDTO.getId());
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            room.setComplexity(roomDTO.getComplexity());
            room.setRoomName(roomDTO.getName());
            roomRepository.save(room);
            return roomDTO;
        } else {
            return null;
        }
    }

    public void deleteRoom(RoomDTO roomDTO) {
        roomRepository.delete(this.convertDtoToEntity(roomDTO));
    }

    public void deleteRoomId(Integer roomId) {
        Optional<Room> roomOptional =  roomRepository.findById(roomId);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            roomRepository.delete(room);
        }
    }
    public RoomDTO convertEntityToDto(Room roomEntity) {
        return RoomDTO.builder()
                .id(roomEntity.getId())
                .name(roomEntity.getRoomName())
                .complexity(roomEntity.getComplexity())
                .build();
    }

    public Room convertDtoToEntity(RoomDTO roomDTO) {
        return Room.builder()
                .id(roomDTO.getId() != null ? roomDTO.getId() : null)
                .roomName(roomDTO.getName())
                .complexity(roomDTO.getComplexity())
                .build();
    }
}

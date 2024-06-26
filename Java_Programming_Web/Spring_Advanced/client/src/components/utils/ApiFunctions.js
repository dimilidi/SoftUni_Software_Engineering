import axios from "axios";

export const api = axios.create({
  baseUrl: "http://localhost:8080",
});

// This function adds a new room to the database
export async function addRoom(photo, roomType, roomPrice) {
  const formData = new FormData();
  formData.append("photo", photo);
  formData.append("roomType", roomType);
  formData.append("roomPrice", roomPrice);

  const response = await api.post(
    "http://localhost:8080/rooms/add/new-room",
    formData
  );

  if (response.data == 201) {
    return true;
  } else {
    return false;
  }
}

// This function gets all room types from the database
export async function getRoomTypes() {
  try {
    const response = await axios.get("http://localhost:8080/rooms/room/types");

    return response.data;
  } catch (error) {
    throw new Error("Error fetching room types");
  }
}

// This function gets all rooms from the database
export async function getAllRooms() {
  try {
    const result = await api.get("http://localhost:8080/rooms/all-rooms");
    return result.data;
  } catch (error) {
    throw new Error("Error fetching rooms!");
  }
}

/* This function deletes a room by the Id */
export async function deleteRoom(roomId) {
  try {
    const result = await api.delete(
      `http://localhost:8080/rooms/delete/room/${roomId}`
    );
    return result.data;
  } catch (error) {
    throw new Error(`Error deleting room ${error.message}`);
  }
}

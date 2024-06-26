import React, { useEffect } from 'react'
import { useState } from 'react'
import { getAllRooms } from '../utils/ApiFunctions'
import { deleteRoom } from '../utils/ApiFunctions'
import { Col } from "react-bootstrap"
import RoomFilter from '../common/RoomFilter'
import RoomPaginator from '../common/RoomPaginator'
import { FaEdit, FaEye, FaPlus, FaTrashAlt } from "react-icons/fa"
import { Link } from "react-router-dom"

function ExistingRooms() {

    const [rooms, setRooms] = useState([])
    const [currentPage, setCurrentPage] = useState(1)
    const [roomsPerPage, setRoomsPerPage] = useState(8)
    const [isLoading, setIsLoading] = useState(false)
    const [filteredRooms, setFilteredRooms] = useState([{ id: "", roomType: "", roomPrice: "" }])
    const [selectedRoomType, setSelectedRoomType] = useState("")
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    useEffect(() => {
        fetchRooms()
    }, [])

    const fetchRooms = async () => {
        setIsLoading(true)

        try {
            const result = await getAllRooms()
            setRooms(result)
            setIsLoading(false)
        } catch (error) {
            setErrorMessage(error.message)
        }
    }

    useEffect(() => {
        if (selectedRoomType === "") {
            setFilteredRooms(rooms)
        } else {
            const filtered = rooms.filter((room) => room.roomType === selectedRoomType)
            setFilteredRooms(filtered)
        }
        setCurrentPage(1)
    }, [rooms, selectedRoomType])

    const handleDelete = async (roomId) => {
        console.log("DELETE ENTERED");
        try {
            const result = await deleteRoom(roomId)
            if (result === "") {
                setSuccessMessage(`Room No ${roomId} was deleted`)
                fetchRooms()
                console.log("DELETED");
            } else {
                console.error(`Error deleting room : ${result.message}`)
            }
        } catch (error) {
            console.log(error.message);
            setErrorMessage(error.message)
        }
        setTimeout(() => {
            setSuccessMessage("")
            setErrorMessage("")
        }, 3000)
    }


    const calculateTotalPages = (filteredRooms, roomsPerPage, rooms) => {
        const totalRooms = filteredRooms.lenght > 0 ? filteredRooms.length : rooms.length

        return Math.ceil(totalRooms / roomsPerPage)
    }

    const handlePaginationClick = (pageNumber) => {
        setCurrentPage(pageNumber)
    }

    const indexOfLastRoom = currentPage * roomsPerPage
    const indexOfFirstRoom = indexOfLastRoom - roomsPerPage
    const currentRooms = filteredRooms.slice(indexOfFirstRoom, indexOfLastRoom)


    return (
        <>
            {isLoading && (
                <p>Loading existing rooms</p>
            )}

            <section className='mt-5 mb-5 container'>
                <div className='d-flex justify-content-center mt-5 mb-3'>
                    <h2>Existing Rooms</h2>
                </div>
                <Col md={6} className='mb-3 md-0'>
                    <RoomFilter data={rooms} setFilteredRooms={setFilteredRooms} />
                </Col>

                <table className="table table-bordered table-hover">
                    <thead>
                        <tr className="text-center">
                            <th>ID</th>
                            <th>Room Type</th>
                            <th>Room Price</th>
                            <th>Actions</th>
                        </tr>
                    </thead>

                    <tbody>
                        {currentRooms.map((room) => (
                            <tr key={room.id} className="text-center">
                                <td>{room.id}</td>
                                <td>{room.roomType}</td>
                                <td>{room.roomPrice}</td>
                                <td className="gap-2">
                                    {/* <Link to={`/edit-room/${room.id}`} className="gap-2">
                                    <span className="btn btn-info btn-sm">
                                        <FaEye />
                                    </span>
                                    <span className="btn btn-warning btn-sm ml-5">
                                        <FaEdit />
                                    </span>
                                </Link> */}
                                    <button
                                        className="btn btn-danger btn-sm ml-5"
                                        onClick={() => handleDelete(room.id)}>
                                        <FaTrashAlt />
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>

                <RoomPaginator
                    currentPage={currentPage}
                    totalPages={calculateTotalPages(filteredRooms, roomsPerPage, rooms)}
                    onPageChange={handlePaginationClick}
                />

            </section>

        </>
    )
}

export default ExistingRooms
import React, { useEffect, useState } from 'react'
import LuongService from '../../service/LuongService';
import { useNavigate } from "react-router-dom";
export const HomeLuong = () => {
    const [luongs, setLuongs] = useState([]);
    const [months, setMonths] = useState("");
    useEffect(() => {
        loadluongs();
    }, []);

    const loadluongs = async () => {
        try {
            const data = await LuongService.getLuong();
            setLuongs(data);
        } catch (error) {
            console.error('Error loading luongs:', error.message);
        }
    };
    const navigate = useNavigate();
    const handleTK = () => {
        navigate(`/tkluong/${months}`)
    }
  return (
    <div className="container">
        <h2 className='fs-2 text-center mb-3'>Thống kê lương nhân viên</h2>
        <div className="row mb-3">
                <div className="col">
                    <input 
                        type="text" 
                        className="form-control" 
                        placeholder="Nhập tháng muốn thống kê (vd : 4)"
                        value={months}
                        onChange={(e) => setMonths(e.target.value)}
                        max="12" min="1"
                    />
                </div>
                <div className="col-auto">
                    <button className="btn btn-outline-primary" onClick={handleTK}>Thống kê</button>
                </div>
            </div>
        <table className='table table-striped'>
            <thead>
                <tr className='text-center'>
                    <td>ID nhân viên</td>
                    <td>Họ tên</td>
                    <td>Số điện thoại</td>
                    <td>Địa chỉ</td>
                    <td>Chức vụ</td>
                    <td>Lương cơ bản</td>
                </tr>
            </thead>
            <tbody>
                {luongs.map((luong) => (
                    <tr key={luong.idnv} className='text-center'>
                        <td>{luong.idnv}</td>
                        <td>{luong.hoten}</td>
                        <td>{luong.sdt}</td>
                        <td>{luong.diachi}</td>
                        <td>{luong.chucvu}</td>
                        <td>{luong.luongcoban}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    </div>
  )
}

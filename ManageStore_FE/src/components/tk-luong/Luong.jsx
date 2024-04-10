import React, { useEffect, useState } from 'react'
import { useParams } from "react-router-dom";
import LuongService from '../../service/LuongService';
export const Luong = () => {
    const [luongs, setLuongs] = useState([]);
    const params = useParams();
    const month = params.month;
    useEffect(() => {
        loadluongs(month);
    }, [month]);

    const loadluongs = async (month) => {
        try {
            const luong = await LuongService.getTKLuong(month);
            setLuongs(luong);
        } catch (error) {
            console.error('Error loading luongs:', error.message);
        }
    };
    const calculateTotalSalary = (luong) => {
        const otSalary = luong.gioot < 0 ? 0 : (luong.luongcoban / 26) * 1.5 * luong.gioot;
        const totalSalary = (luong.luongcoban / 26) * luong.songaydilam + luong.thuong + otSalary;
        return totalSalary.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
    };

  return (
    <div className='container'>
    <h2 className='text-center fs-1 mb-3'>Thống kê lương của nhân viên</h2>
    <table className='table table-striped'>
        <thead>
            <tr className='text-center'>
                <td>ID nhân viên</td>
                <td>Họ tên</td>
                <td>Số điện thoại</td>
                <td>Địa chỉ</td>
                <td>Chức vụ</td>
                <td>Lương cơ bản</td>
                <td>Thưởng</td>
                <td>Số ngày đi làm</td>
                <td>Số giờ tăng ca</td>
                <td>Tổng lương</td>
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
                    <td>{luong.thuong}</td>
                    <td>{luong.songaydilam}</td>
                    <td>{luong.gioot}</td>
                    <td>{calculateTotalSalary(luong)}</td>
                </tr>
            ))}
        </tbody>
    </table>
</div>
  )
}

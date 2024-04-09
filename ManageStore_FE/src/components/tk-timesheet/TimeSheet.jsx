import React, { useEffect, useState } from 'react'
import TimeSheetService from '../../service/TimeSheetService';
import { useParams } from "react-router-dom";
export const TimeSheet = () => {
    const [employees, setEmployees] = useState([]);
    const params = useParams();
    const month = params.month;
    useEffect(() => {
        loadEmployees(month);
    }, [month]);

    const loadEmployees = async (month) => {
        try {
            const employee = await TimeSheetService.getTKTimeSheet(month);
            setEmployees(employee);
        } catch (error) {
            console.error('Error loading employees:', error.message);
        }
    };
  return (
    <div className='container'>
        <h2 className='text-center fs-1 mb-3'>Thống kê giờ làm việc của nhân viên</h2>
        <table className='table table-striped'>
            <thead>
                <tr className='text-center'>
                    <td>ID nhân viên</td>
                    <td>Họ tên</td>
                    <td>Số điện thoại</td>
                    <td>Địa chỉ</td>
                    <td>Chức vụ</td>
                    <td>Số ngày đi làm</td>
                    <td>Số giờ tăng ca</td>
                </tr>
            </thead>
            <tbody>
                {employees.map((employee) => (
                    <tr key={employee.idnhanvien} className='text-center'>
                        <td>{employee.idnhanvien}</td>
                        <td>{employee.ten}</td>
                        <td>{employee.sdt}</td>
                        <td>{employee.diachi}</td>
                        <td>{employee.chucvu}</td>
                        <td>{employee.songaydilam}</td>
                        <td>{employee.gioot}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    </div>
  )
}

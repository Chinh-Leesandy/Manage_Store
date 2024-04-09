import React, { useEffect, useState } from 'react'
import NghiPhepService from '../../service/NghiPhepService';
import { useParams } from "react-router-dom";
export const NghiPhep = () => {
    const [employees, setEmployees] = useState([]);
    const params = useParams();
    const month = params.month;
    useEffect(() => {
        loadEmployees(month);
    }, [month]);

    const loadEmployees = async (month) => {
        try {
            const employee = await NghiPhepService.getTKNghiPhep(month);
            setEmployees(employee);
        } catch (error) {
            console.error('Error loading employees:', error.message);
        }
    };
  return (
    <div className='container'>
        <h2 className='text-center fs-1 mb-3'>Thống kê nghỉ phép của nhân viên</h2>
        <table className='table table-striped'>
            <thead>
                <tr className='text-center'>
                    <td>ID nhân viên</td>
                    <td>Họ tên</td>
                    <td>Chức vụ</td>
                    <td>Số ngày đã nghỉ</td>
                </tr>
            </thead>
            <tbody>
                {employees.map((employee) => (
                    <tr key={employee.idnv} className='text-center'>
                        <td>{employee.idnv}</td>
                        <td>{employee.ten}</td>
                        <td>{employee.chucvu}</td>
                        <td>{employee.songaynghi}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    </div>
  )
}

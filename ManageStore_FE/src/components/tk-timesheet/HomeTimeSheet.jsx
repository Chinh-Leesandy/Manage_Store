import React, { useEffect, useState } from 'react'
import { useNavigate } from "react-router-dom";
import TimeSheetService from '../../service/TimeSheetService';
export const HomeTimeSheet = () => {
    const [employees, setEmployees] = useState([]);
    const [months, setMonths] = useState("");

    useEffect(() => {
        loadEmployees();
    }, []);

    const loadEmployees = async () => {
        try {
            const data = await TimeSheetService.getEmployeeTimeKeeping();
            setEmployees(data);
        } catch (error) {
            console.error('Error loading employees:', error.message);
        }
    };
    const navigate = useNavigate();
    const handleTK = () => {
        navigate(`/tkgiolam/${months}`)
    }
  return (
    <div className="container">
        <h2 className='fs-2 text-center mb-3'>Thống kê giờ làm việc nhân viên</h2>
        <div className="row mb-3">
                <div className="col">
                    <input 
                        type="text" 
                        className="form-control" 
                        placeholder="Nhập tháng muốn thống kê (vd : 4)"
                        value={months}
                        onChange={(e) => setMonths(e.target.value)}
                    />
                </div>
                <div className="col-auto">
                    <button className="btn btn-outline-primary" onClick={handleTK}>Thống kê</button>
                </div>
            </div>
        <table className='table table-striped'>
            <thead>
                <tr className='text-center'>
                    <td>ID</td>
                    <td>ID nhân viên</td>
                    <td>Họ tên</td>
                    <td>Chức vụ</td>
                    <td>Thời gian vào</td>
                    <td>Thời gian ra</td>
                </tr>
            </thead>
            <tbody>
                {employees.map((employee) => (
                    <tr key={employee.id} className='text-center'>
                        <td>{employee.id}</td>
                        <td>{employee.idnhanvien}</td>
                        <td>{employee.ten}</td>
                        <td>{employee.chucvu}</td>
                        <td>{employee.thoigianvao}</td>
                        <td>{employee.thoigianra}</td>
                    </tr>
                ))}
            </tbody>
        </table>
    </div>
  )
}

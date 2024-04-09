import React, { useEffect, useState } from 'react';
import EmployeeService from '../../service/EmployeeService';
import { useNavigate } from "react-router-dom";

export const HomeEmployee = () => {
    const [employees, setEmployees] = useState([]);
    const [showPasswords, setShowPasswords] = useState({});

    useEffect(() => {
        loadEmployees();
    }, []);

    const loadEmployees = async () => {
        try {
            const employeeList = await EmployeeService.getEmployees();
            setEmployees(employeeList);
        } catch (error) {
            console.error('Error loading employees:', error.message);
        }
    };

    const togglePasswordVisibility = (id) => {
        setShowPasswords((prevShowPasswords) => {
            const passwordShown = !!prevShowPasswords[id];
            return { ...prevShowPasswords, [id]: !passwordShown };
        });
    };

    const navigate = useNavigate();
    const handleView = (id) => {
        navigate(`/employee/${id}`)
    }
    const handleAdd = () => {
        if (window.confirm("Bạn có chắc chắn muốn tạo nhân viên mới không?")) {
          navigate("/employee/-1");
        }
      };

      const handleDelete = async(e) => {
        if (window.confirm(`Bạn có chắc chắn muốn xóa nhân viên ${e.hoten} không?`)) {
            try {
               await EmployeeService.deleteEmployee(e.id);
            } catch (error) {
                console.error('Error delete employee:', error.message);
            }
        }
      };
    return (
        <div className='container'>
            <h2 className='fs-2 text-center'>Quản lý nhân viên</h2>
            <hr />
            <div className="d-flex justify-content-end mb-2">
                <button onClick={() => handleAdd()} className='btn btn-outline-success'>Thêm nhân viên</button>
            </div>
            <table className="table table-striped">
                <thead>
                    <tr className='text-center'>
                        <td>ID</td>
                        <td>Họ tên</td>
                        <td>Số điện thoại</td>
                        <td>Email</td>
                        <td>Địa chỉ</td>
                        <td>Chức vụ</td>
                        <td>Username</td>
                        <td>Password</td>
                        <td>Ngày bắt đầu làm việc</td>
                        <td>Action</td>
                    </tr>
                </thead>
                <tbody>
                    {employees.map((employee) => (
                        <tr key={employee.id} className='text-center'>
                            <td>{employee.id}</td>
                            <td>{employee.hoten}</td>
                            <td>{employee.sdt}</td>
                            <td>{employee.email}</td>
                            <td>{employee.diachi}</td>
                            <td>{employee.chucvu}</td>
                            <td>{employee.username}</td>
                            <td>
                                <button
                                    type="button"
                                    className=" btn btn-primary btn-sm"
                                    onClick={() => togglePasswordVisibility(employee.id)}
                                >
                                  Show
                                </button>
                                {showPasswords[employee.id] && (
                                    <div className="mt-2">
                                        <span>{employee.password}</span>
                                    </div>
                                )}
                            </td>
                            <td>{employee.ngaybatdaulam}</td>
                            <td>
                                <div className="d-flex justify-content-between">
                                    <button onClick={() => handleView(employee.id)} className='btn btn-outline-warning'>Edit</button>
                                    <button onClick={() => handleDelete(employee)} className='btn btn-outline-danger'>Delete</button>
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};
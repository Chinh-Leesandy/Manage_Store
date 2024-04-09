import React, { useEffect, useState } from 'react'
import EmployeeService from '../../service/EmployeeService';
import { useParams, useNavigate } from "react-router-dom";
export const Employee = () => {
    const [employee, setEmployee] = useState({});
    const params = useParams();
    const id = params.id;
    const navigate = useNavigate();
    useEffect(() => {
        loadEmployee(id);
    }, [id]);
    const loadEmployee = async (id) => {
        try {
            const employee = await EmployeeService.getEmployeeById(id);
            setEmployee(employee);
        } catch (error) {
            console.error('Error loading employees:', error.message);
        }
    };
    const handleAddOrUpdate = async () => {
        try {
          if (id < 0) {
            await EmployeeService.addEmployee(employee);
          } else {
            await EmployeeService.updateEmployee(employee);
          }
          navigate('/employee'); 
        } catch (error) {
          console.error('Error:', error.message);
        }
      }
  return (
    <div className='container'>
        {id<0 ? (
            <h2 className='fs-2 text-center'>Thêm nhân viên mới</h2>
        ):(
            <h2 className='fs-2 text-center'>Cập nhập thông tin nhân viên</h2>
        )}
        <div className="row g-3 col-7 mx-auto p-3">
        <div className="col-md-6">
        <label htmlFor="hoten" className="form-label">Họ tên:</label>
        <input type="text" className="form-control" id="hoten" name="hoten" value={employee.hoten} onChange={(e) => setEmployee({...employee, hoten: e.target.value})} required />
      </div>
      <div className="col-md-6">
        <label htmlFor="sdt" className="form-label">Số điện thoại:</label>
        <input type="text" className="form-control" id="sdt" name="sdt" value={employee.sdt} onChange={(e) => setEmployee({...employee, sdt: e.target.value})} required />
      </div>
      <div className="col-md-6">
        <label htmlFor="email" className="form-label">Email:</label>
        <input type="email" className="form-control" id="email" name="email" value={employee.email} onChange={(e) => setEmployee({...employee, email: e.target.value})} required />
      </div>
      <div className="col-md-6">
        <label htmlFor="diachi" className="form-label">Địa chỉ:</label>
        <input type="text" className="form-control" id="diachi" name="diachi" value={employee.diachi} onChange={(e) => setEmployee({...employee, diachi: e.target.value})} required />
      </div>
      <div className="col-12">
        <label htmlFor="chucvu" className="form-label">Chức vụ:</label>
        <input type="text" className="form-control" id="chucvu" name="chucvu" value={employee.chucvu} onChange={(e) => setEmployee({...employee, chucvu: e.target.value})} required />
      </div>
      <div className="col-md-6">
        <label htmlFor="username" className="form-label">Username:</label>
        <input type="text" className="form-control" id="username" name="username" value={employee.username} onChange={(e) => setEmployee({...employee, username: e.target.value})} required />
      </div>
      <div className="col-md-6">
        <label htmlFor="password" className="form-label">Password:</label>
        <input type="password" className="form-control" id="password" name="password" value={employee.password} onChange={(e) => setEmployee({...employee, password: e.target.value})} required />
      </div>
      <div className="col-12">
        <label htmlFor="ngaybatdaulv" className="form-label">Ngày bắt đầu làm việc: </label>
        <input type="text" className="form-control" id="ngaybatdaulv" name="ngaybatdaulv" value={employee.ngaybatdaulam} onChange={(e) => setEmployee({...employee, ngaybatdaulam: e.target.value})} required />
      </div>
      </div>
      <div className="d-grid gap-1 col-4 mx-auto">
        <button onClick={handleAddOrUpdate} className="btn btn-outline-success">
          {id < 0 ? 'Thêm' : 'Sửa'}
        </button>
      </div>
    </div>
  )
}

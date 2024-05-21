import React, { useEffect, useState } from 'react'
import EmployeeService from '../../service/EmployeeService';
import { useParams, useNavigate, useLocation } from "react-router-dom";
import { errorToast, successToast } from '../../util/toastily';
export const Employee = () => {
    const [employee, setEmployee] = useState({});
    const params = useParams();
    const id = params.id;
    const navigate = useNavigate();
    const [validation, setValidation] = useState({});
    const location = useLocation();
    const employees = location.state.employees;
    useEffect(() => {
        loadEmployee(id);
    }, [id]);
    const loadEmployee = async (id) => {
        try {
            const employee = await EmployeeService.getEmployeeById(id);
            setEmployee(employee);
        } catch (error) {
            console.error('Error loading employee:', error.message);
        }
    };
    const validateForm = () => {
      const newvalidate = {};
  
      if (employee.hoten === '' || !employee.hoten) {
          newvalidate.hoten = "Tên nhân viên không được để trống.";
      } else if (!/^[a-zA-Z\sÀ-ỹ]+$/.test(employee.hoten)) {
        newvalidate.hoten = "Tên nhân viên chỉ được chứa chữ cái và khoảng trắng.";
      }
      if (employee.sdt === '' || !employee.sdt) {
          newvalidate.sdt = "Số điện thoại không được để trống.";
      } else if (!/^\+?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/.test(employee.sdt)) {
          newvalidate.sdt = "Số điện thoại không hợp lệ.";
      } else if (employees.some(e => e.sdt === employee.sdt && e.id !== id)) {
        newvalidate.sdt = "Số điện thoại đã tồn tại.";
      }
      if (employee.ngaybatdaulam === '' || !employee.ngaybatdaulam) {
          newvalidate.ngaybatdaulam = "Ngày bắt đầu làm việc không được để trống.";
      }
      if (employee.email === '' || !employee.email) {
          newvalidate.email = "Email nhân viên không được để trống.";
      } else if (!/^[^\s@]+@gmail\.com$/.test(employee.email)) {
          newvalidate.email = "Email phải kết thúc bằng '@gmail.com'.";
      } else if (employees.some(e => e.email === employee.email && e.id !== id)) {
        newvalidate.email = "Email đã tồn tại.";
      }
      if (employee.diachi === '' || !employee.diachi) {
          newvalidate.diachi = "Địa chỉ nhân viên không được để trống.";
      }
      if (employee.chucvu === '' || !employee.chucvu) {
          newvalidate.chucvu = "Chức vụ nhân viên không được để trống.";
      } else if (!/^[a-zA-Z\sÀ-ỹ]+$/.test(employee.chucvu)) {
        newvalidate.chucvu = "Chức vụ nhân viên chỉ được chứa chữ cái và khoảng trắng.";
      }
      if (employee.username === '' || !employee.username) {
          newvalidate.username = "Tài khoản đăng nhập nhân viên không được để trống.";
      } else if (employees.some(e => e.username === employee.username && e.id !== id)) {
        newvalidate.username = "Tài khoản đăng nhập đã tồn tại.";
      }
      if (employee.password === '' || !employee.password) {
          newvalidate.password = "Mật khẩu nhân viên không được để trống.";
      } else if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*\W).{8,}$/.test(employee.password)) {
          newvalidate.password = "Mật khẩu có ít nhất 8 kí tự, phải có chữ viết hoa, chữ thường, chữ số và kí tự đặc biệt";
      }
      setValidation(newvalidate);
      return Object.keys(newvalidate).length === 0;
  };
  
    const handleAddOrUpdate = async () => {
      if(validateForm()){
        try {
          if (id < 0) {
            await EmployeeService.addEmployee(employee);
            successToast("Thêm nhân viên thành công");
          } else {
            await EmployeeService.updateEmployee(employee);
            successToast("Cập nhập nhân viên thành công");
          }
          setTimeout(() => {
            navigate('/employee'); 
          }, 1000);
        } catch (error) {
          console.error('Error:', error.message);
          if(id < 0) {
            errorToast("Bạn thêm nhân viên thất bại");
          }
          else {
            errorToast("Bạn cập nhập nhân viên thất bại");
          }
        }
      } else {
        if(id < 0) {
          errorToast("Bạn thêm nhân viên thất bại");
        }
        else {
          errorToast("Bạn cập nhập nhân viên thất bại");
        }
      }
    }
    const handleBack = () => {
      if(window.confirm(`Bạn có chắc chắn muốn xóa hủy không?`)){
        try {
          navigate('/employee'); 
       } catch (error) {
           errorToast("Bạn hủy không thành công")
       }
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
            <label htmlFor="hoten" className="form-label">Họ tên:<span style={{ color: 'red', marginLeft: '5px' }}>*</span></label>
            <input type="text" className={validation.hoten ? 'form-control is-invalid' : 'form-control'} id="hoten" name="hoten" value={employee.hoten} onChange={(e) => setEmployee({...employee, hoten: e.target.value})} required />
            {validation.hoten && <div className="invalid-feedback validation-hoten">{validation.hoten}</div>}
          </div>
          <div className="col-md-6">
            <label htmlFor="sdt" className="form-label">Số điện thoại:<span style={{ color: 'red', marginLeft: '5px' }}>*</span></label>
            <input type="text" className={validation.sdt ? 'form-control is-invalid' : 'form-control'} id="sdt" name="sdt" value={employee.sdt} onChange={(e) => setEmployee({...employee, sdt: e.target.value})} required />
            {validation.sdt && <div className="invalid-feedback validation-sdt">{validation.sdt}</div>}
          </div>
          <div className="col-md-6">
            <label htmlFor="email" className="form-label">Email:<span style={{ color: 'red', marginLeft: '5px' }}>*</span></label>
            <input type="email" className={validation.email ? 'form-control is-invalid' : 'form-control'} id="email" name="email" value={employee.email} onChange={(e) => setEmployee({...employee, email: e.target.value})} required />
            {validation.email && <div className="invalid-feedback validation-email">{validation.email}</div>}
          </div>
          <div className="col-md-6">
            <label htmlFor="diachi" className="form-label">Địa chỉ:<span style={{ color: 'red', marginLeft: '5px' }}>*</span></label>
            <input type="text" className={validation.diachi ? 'form-control is-invalid' : 'form-control'} id="diachi" name="diachi" value={employee.diachi} onChange={(e) => setEmployee({...employee, diachi: e.target.value})} required />
            {validation.diachi && <div className="invalid-feedback validation-diachi">{validation.diachi}</div>}
          </div>
          <div className="col-12">
            <label htmlFor="chucvu" className="form-label">Chức vụ:<span style={{ color: 'red', marginLeft: '5px' }}>*</span></label>
            <input type="text" className={validation.chucvu ? 'form-control is-invalid' : 'form-control'} id="chucvu" name="chucvu" value={employee.chucvu} onChange={(e) => setEmployee({...employee, chucvu: e.target.value})} required />
            {validation.chucvu && <div className="invalid-feedback validation-chucvu">{validation.chucvu}</div>}
          </div>
          <div className="col-md-6">
            <label htmlFor="username" className="form-label">Username:<span style={{ color: 'red', marginLeft: '5px' }}>*</span></label>
            <input type="text" className={validation.username ? 'form-control is-invalid' : 'form-control'} id="username" name="username" value={employee.username} onChange={(e) => setEmployee({...employee, username: e.target.value})} required />
            {validation.username && <div className="invalid-feedback validation-username">{validation.username}</div>}
          </div>
          <div className="col-md-6">
            <label htmlFor="password" className="form-label">Password:<span style={{ color: 'red', marginLeft: '5px' }}>*</span></label>
            <input type="password" className={validation.password ? 'form-control is-invalid' : 'form-control'} id="password" name="password" value={employee.password} onChange={(e) => setEmployee({...employee, password: e.target.value})} required />
            {validation.password && (<div className="invalid-feedback validation-password">{validation.password}</div>)}
          </div>
          <div className="col-12">
            <label htmlFor="ngaybatdaulv" className="form-label">Ngày bắt đầu làm việc:<span style={{ color: 'red', marginLeft: '5px' }}>*</span> </label>
            <input type="date" className={validation.ngaybatdaulv ? 'form-control is-invalid' : 'form-control'} id="ngaybatdaulv" name="ngaybatdaulv" value={employee.ngaybatdaulam} onChange={(e) => setEmployee({...employee, ngaybatdaulam: e.target.value})} required />
            {validation.ngaybatdaulam && <div className="invalid-feedback validation-date">{validation.ngaybatdaulam}</div>}
          </div>
        </div>
        <div className="d-flex col-3 mx-auto justify-content-around">
            <button onClick={handleAddOrUpdate} className="btn btn-outline-success px-5">
              {id < 0 ? 'Thêm' : 'Sửa'}
            </button>
            <button onClick={handleBack} className='btn btn-outline-warning px-5'>Cancel</button>
        </div>
    </div>
  )
}

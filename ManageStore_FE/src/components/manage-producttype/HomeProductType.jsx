import React, { useEffect, useState } from 'react'
import { useNavigate } from "react-router-dom";
import ProductTypeService from '../../service/ProductTypeService';
import { errorToast, successToast } from '../../util/toastily';
export const HomeProductType = () => {
    const [productTypes, setProductTypes] = useState([]);
    useEffect(() => {
        loadProductTypes();
    }, []);

    const loadProductTypes = async () => {
        try {
            const data = await ProductTypeService.getProductType();
            setProductTypes(data);
        } catch (error) {
            console.error('Error loading productType:', error.message);
        }
    };
    const navigate = useNavigate();
    const handleView = (id) => {
        navigate(`/productType/${id}`)
    }
    const handleAdd = () => {
        if (window.confirm("Bạn có chắc chắn muốn tạo loại sản phẩm mới không?")) {
          navigate("/productType/-1");
        }
      };

      const handleDelete = async(e) => {
        if (window.confirm(`Bạn có chắc chắn muốn xóa nhân viên ${e.ten} không?`)) {
            try {
               await ProductTypeService.deleteProductType(e.id);
               successToast("Bạn đã xóa thành công loại mặt hàng")
               setTimeout(() => {
                navigate('/productType'); 
            }, 1000);
            } catch (error) {
                console.error('Error delete productType:', error.message);
                errorToast("Bạn đã xóa không thành công loại mặt hàng")
            }
        }
      };
  return (
    <div className='container'>
            <h2 className='fs-2 text-center'>Quản lý loại mặt hàng</h2>
            <hr />
            <div className="d-flex justify-content-end mb-2">
                <button onClick={() => handleAdd()} className='btn btn-outline-success'>Thêm loại mặt hàng</button>
            </div>
            <table className="table table-striped">
                <thead>
                    <tr className='text-center'>
                        <td>ID</td>
                        <td>Tên loại mặt hàng</td>
                        <td>Nhà cung cấp</td>
                        <td>Thời gian nhập</td>
                        <td>Số lượng loại</td>
                        <td>Vị trí trưng bày</td>
                        <td>Action</td>
                    </tr>
                </thead>
                <tbody>
                    {productTypes.map((productType) => (
                        <tr key={productType.id} className='text-center'>
                            <td>{productType.id}</td>
                            <td>{productType.ten}</td>
                            <td>{productType.ncc}</td>
                            <td>{productType.thoigiannhap}</td>
                            <td>{productType.soluong}</td>
                            <td>{productType.vitri}</td>
                            <td>
                                <div className="d-flex justify-content-around">
                                    <button onClick={() => handleView(productType.id)} className='btn btn-outline-warning'>Edit</button>
                                    <button onClick={() => handleDelete(productType)} className='btn btn-outline-danger'>Delete</button>
                                </div>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
  )
}

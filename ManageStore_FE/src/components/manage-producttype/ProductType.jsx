import React, { useEffect, useState } from 'react'
import { useParams, useNavigate } from "react-router-dom";
import ProductTypeService from '../../service/ProductTypeService';
export const ProductType = () => {
    const [productType, setProductType] = useState({});
    const params = useParams();
    const id = params.id;
    const navigate = useNavigate();
    useEffect(() => {
        loadProductType(id);
    }, [id]);
    const loadProductType = async (id) => {
        try {
            const data = await ProductTypeService.getProductTypeId(id);
            setProductType(data);
        } catch (error) {
            console.error('Error loading productType:', error.message);
        }
    };
    const handleAddOrUpdate = async () => {
        try {
          if (id < 0) {
            await ProductTypeService.addProductType(productType);
          } else {
            await ProductTypeService.updateProductType(productType);
          }
          navigate('/productType'); 
        } catch (error) {
          console.error('Error:', error.message);
        }
      }
  return (
    <div className='container'>
        {id<0 ? (
            <h2 className='fs-2 text-center'>Thêm loại mặt hàng mới</h2>
        ):(
            <h2 className='fs-2 text-center'>Cập nhập thông tin loại mặt hàng</h2>
        )}
        <div className="row g-3 col-7 mx-auto p-3">
        <div className="col-md-6">
        <label htmlFor="ten" className="form-label">Tên loại mặt hàng:</label>
        <input type="text" className="form-control" id="ten" name="ten" value={productType.ten} onChange={(e) => setProductType({...productType, ten: e.target.value})} required />
      </div>
      <div className="col-md-6">
        <label htmlFor="ncc" className="form-label">Nhà cung cấp:</label>
        <input type="text" className="form-control" id="ncc" name="ncc" value={productType.ncc} onChange={(e) => setProductType({...productType, ncc: e.target.value})} required />
      </div>
      <div className="col-md-6">
        <label htmlFor="thoigiannhap" className="form-label">Thời gian nhập:</label>
        <input type="date" className="form-control" id="thoigiannhap" name="thoigiannhap" value={productType.thoigiannhap} onChange={(e) => setProductType({...productType, thoigiannhap: e.target.value})} required />
      </div>
      <div className="col-md-6">
        <label htmlFor="soluong" className="form-label">Số lượng loại :</label>
        <input type="text" className="form-control" id="soluong" name="soluong" value={productType.soluong} onChange={(e) => setProductType({...productType, soluong: e.target.value})} required />
      </div>
      <div className="col-12">
        <label htmlFor="vitri" className="form-label">Vị trí trưng bày:</label>
        <input type="text" className="form-control" id="vitri" name="vitri" value={productType.vitri} onChange={(e) => setProductType({...productType, vitri: e.target.value})} />
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

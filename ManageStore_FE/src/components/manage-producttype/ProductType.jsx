import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from "react-router-dom";
import ProductTypeService from '../../service/ProductTypeService';
import { errorToast, successToast } from '../../util/toastily';

export const ProductType = () => {
  const [productType, setProductType] = useState({
    ten: '',
    ncc: '',
    thoigiannhap: '',
    soluong: '',
    vitri: ''
  });
  const [validation, setValidation] = useState({});
  const params = useParams();
  const id = params.id;
  const navigate = useNavigate();
  const [productTypes, setProductTypes] = useState([]);

  useEffect(() => {
    if (id >= 0) {
      loadProductType(id);
    }
    loadProductTypes();
  }, [id]);

  const loadProductType = async (id) => {
    try {
      const data = await ProductTypeService.getProductTypeId(id);
      setProductType(data);
    } catch (error) {
      console.error('Error loading productType:', error.message);
    }
  };

  const loadProductTypes = async () => {
    try {
      const data = await ProductTypeService.getProductType();
      setProductTypes(data);
    } catch (error) {
      console.error('Error loading productTypes:', error.message);
    }
  };

  const validateForm = () => {
    const newValidate = {};
    if (productType.ten === '' || !productType.ten) {
      newValidate.ten = "Tên loại mặt hàng không được để trống.";
    } else if (!/^[a-zA-Z\sÀ-ỹ]+$/.test(productType.ten)) {
      newValidate.ten = "Tên loại mặt hàng chỉ được chứa chữ cái và khoảng trắng.";
    }

    if (productType.ncc === '' || !productType.ncc) {
      newValidate.ncc = "Tên nhà cung cấp không được để trống.";
    } else if (!/^[a-zA-Z\sÀ-ỹ]+$/.test(productType.ncc)) {
      newValidate.ncc = "Tên nhà cung cấp chỉ được chứa chữ cái và khoảng trắng.";
    }

    if (productType.thoigiannhap === '' || !productType.thoigiannhap) {
      newValidate.thoigiannhap = "Thời gian nhập không được để trống.";
    }

    if (productType.soluong === '' || !productType.soluong) {
      newValidate.soluong = "Số lượng loại mặt hàng không được để trống.";
    } else if (isNaN(productType.soluong) || productType.soluong < 1 || productType.soluong > 100 || productType.soluong.includes('.')) {
      newValidate.soluong = "Số lượng loại mặt hàng là số nguyên nằm trong khoảng từ 1 đến 100.";
    }
    setValidation(newValidate);
    return Object.keys(newValidate).length === 0;
  };

  const handleAddOrUpdate = async () => {
    if (validateForm()) {
      try {
        if (id < 0) {
          const existingProductType = productTypes.find(
            pt => pt.ten === productType.ten && pt.ncc === productType.ncc && pt.thoigiannhap === productType.thoigiannhap
          );
          if (existingProductType) {
            const updatedProductType = {
              ...existingProductType,
              soluong: parseInt(existingProductType.soluong) + parseInt(productType.soluong)
            };
            await ProductTypeService.updateProductType(updatedProductType);
            successToast("Bạn đã thêm thành công loại mặt hàng");
          } else {
            await ProductTypeService.addProductType(productType);
            successToast("Bạn đã thêm thành công loại mặt hàng");
          }
        } else {
          await ProductTypeService.updateProductType(productType);
          successToast("Bạn đã cập nhật thành công loại mặt hàng");
        }
        setTimeout(() => {
          navigate('/productType');
        }, 1000);
      } catch (error) {
        console.error('Error:', error.message);
        errorToast(id < 0 ? "Bạn thêm không thành công loại mặt hàng" : "Bạn cập nhật không thành công loại mặt hàng");
      }
    } else {
      errorToast(id < 0 ? "Bạn thêm không thành công loại mặt hàng" : "Bạn cập nhật không thành công loại mặt hàng");
    }
  };

  const handleBack = () => {
    if (window.confirm(`Bạn có chắc chắn muốn hủy không?`)) {
      try {
        navigate('/productType');
      } catch (error) {
        errorToast("Bạn hủy không thành công");
      }
    }
  };

  return (
    <div className='container'>
      {id < 0 ? (
        <h2 className='fs-2 text-center'>Thêm loại mặt hàng mới</h2>
      ) : (
        <h2 className='fs-2 text-center'>Cập nhật thông tin loại mặt hàng</h2>
      )}
      <div className="row g-3 col-7 mx-auto p-3">
        <div className="col-md-6">
          <label htmlFor="ten" className="form-label">Tên loại mặt hàng:<span style={{ color: 'red', marginLeft: '5px' }}>*</span></label>
          <input type="text" className={validation.ten ? 'form-control is-invalid' : 'form-control'} id="ten" name="ten" value={productType.ten} onChange={(e) => setProductType({...productType, ten: e.target.value})} required/>
          {validation.ten && <div className="invalid-feedback validation-ten">{validation.ten}</div>}
        </div>
        <div className="col-md-6">
          <label htmlFor="ncc" className="form-label">Nhà cung cấp:<span style={{ color: 'red', marginLeft: '5px' }}>*</span></label>
          <input type="text" className={validation.ncc ? 'form-control is-invalid' : 'form-control'} id="ncc" name="ncc" value={productType.ncc} onChange={(e) => setProductType({...productType, ncc: e.target.value})} required/>
          {validation.ncc && <div className="invalid-feedback validation-ncc">{validation.ncc}</div>}
        </div>
        <div className="col-md-6">
          <label htmlFor="thoigiannhap" className="form-label">Thời gian nhập:<span style={{ color: 'red', marginLeft: '5px' }}>*</span></label>
          <input type="date" className={validation.thoigiannhap ? 'form-control is-invalid' : 'form-control'} id="thoigiannhap" name="thoigiannhap" value={productType.thoigiannhap} onChange={(e) => setProductType({...productType, thoigiannhap: e.target.value})} required/>
          {validation.thoigiannhap && <div className="invalid-feedback validation-thoigiannhap">{validation.thoigiannhap}</div>}
        </div>
        <div className="col-md-6">
          <label htmlFor="soluong" className="form-label">Số lượng loại:<span style={{ color: 'red', marginLeft: '5px' }}>*</span></label>
          <input type="number" className={validation.soluong ? 'form-control is-invalid' : 'form-control'} id="soluong" name="soluong" value={productType.soluong} onChange={(e) => setProductType({...productType, soluong: e.target.value})} required/>
          {validation.soluong && <div className="invalid-feedback validation-soluong">{validation.soluong}</div>}
        </div>
        <div className="col-12">
          <label htmlFor="vitri" className="form-label">Vị trí trưng bày:</label>
          <input type="text" className="form-control" id="vitri" name="vitri" value={productType.vitri} onChange={(e) => setProductType({...productType, vitri: e.target.value})} />
        </div>
      </div>
      <div className="d-flex col-3 mx-auto justify-content-around">
        <button onClick={handleAddOrUpdate} className="btn btn-outline-success px-5">
          {id < 0 ? 'Thêm' : 'Sửa'}
        </button>
        <button onClick={handleBack} className='btn btn-outline-warning px-5'>Cancel</button>
      </div>
    </div>
  );
};

import React, { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import ProductTypeService from '../../service/ProductTypeService';
import { errorToast, successToast } from '../../util/toastily';

export const HomeProductType = () => {
    const [productTypes, setProductTypes] = useState([]);
    const [searchKeyword, setSearchKeyword] = useState('');
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        loadProductTypes();
    }, []);

    const loadProductTypes = async () => {
        try {
            const data = await ProductTypeService.getProductType();
            setProductTypes(data);
        } catch (error) {
            console.error('Error loading productType:', error.message);
        } finally {
            setIsLoading(false);
        }
    };

    const navigate = useNavigate();

    const handleView = (id) => {
        navigate(`/productType/${id}`);
    };

    const handleAdd = () => {
        navigate("/productType/-1");
    };

    const handleDelete = async (e) => {
        if (window.confirm(`Bạn có chắc chắn muốn xóa loại mặt hàng ${e.ten} không?`)) {
            try {
                await ProductTypeService.deleteProductType(e.id);
                successToast("Bạn đã xóa thành công loại mặt hàng");
                setTimeout(() => {
                    navigate('/productType'); 
                }, 1000);
                window.location.reload();
            } catch (error) {
                console.error('Error delete productType:', error.message);
                errorToast("Bạn đã xóa không thành công loại mặt hàng");
            }
        }
    };

    const handleSearchChange = (e) => {
        setSearchKeyword(e.target.value);
    };

    const filteredProductTypes = productTypes.filter(productType => {
        return productType.ten.toLowerCase().includes(searchKeyword.toLowerCase());
    });

    if (isLoading) {
        return (
            <div className="d-flex justify-content-center" style={{marginTop: '50vh'}}>
                <div className="spinner-border" role="status">
                    <span className="visually-hidden">Loading...</span>
                </div>
            </div>
        );
    }

    return (
        <div className='container'>
            <h2 className='fs-2 text-center'>Quản lý loại mặt hàng</h2>
            <hr />
            <div className="d-flex flex-column flex-md-row justify-content-between mb-2">
                <button onClick={() => handleAdd()} className='btn btn-outline-success mb-2 mb-md-0'>Thêm loại mặt hàng</button>
                <div className="col-12 col-md-4">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Tìm kiếm theo tên loại mặt hàng"
                        value={searchKeyword}
                        onChange={handleSearchChange}
                    />
                </div>
            </div>
            <div className='table-responsive'>
                <table className="table table-striped rounded-2">
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
                        {filteredProductTypes.map((productType) => (
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
        </div>
    );
};

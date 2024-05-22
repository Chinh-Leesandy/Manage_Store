import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

export const TKHoaDonThang = () => {
  const location = useLocation();
  const invoices = location.state.invoices;
  const [filteredInvoices, setFilteredInvoices] = useState(invoices);
  const [searchTerm, setSearchTerm] = useState('');
  const [sortOrder, setSortOrder] = useState('');

  useEffect(() => {
    applyFiltersAndSorting();
  }, [searchTerm, sortOrder]);

  const totalAmountTk = filteredInvoices.reduce((total, items) => total + items.invoiceProducts.reduce((sum, item) => sum + item.amount, 0), 0);
  const totalPriceTk = filteredInvoices.reduce((total, items) => total + items.invoiceProducts.reduce((sum, item) => sum + item.totalPrice, 0), 0);

  const formatCurrency = (value) => {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
  };

  const navigate = useNavigate();
  const handleView = (invoiceProducts) => {
    navigate('/tkhoadonkh', { state: { invoiceProducts } });
  };

  const applyFiltersAndSorting = () => {
    let data = invoices;

    if (searchTerm) {
      data = data.filter(item =>
        item.customer.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
        item.customer.phone.includes(searchTerm)
      );
    }
    if (sortOrder) {
      data = data.sort((a, b) => {
        const totalA = a.invoiceProducts.reduce((sum, item) => sum + item.totalPrice, 0);
        const totalB = b.invoiceProducts.reduce((sum, item) => sum + item.totalPrice, 0);
        return sortOrder === 'asc' ? totalA - totalB : totalB - totalA;
      });
    }

    setFilteredInvoices(data);
  };

  return (
    <div className='container'>
      <h2 className='text-center'>Danh Sách Hóa Đơn Theo Tháng</h2>
      <div className='row mb-3 mt-3'>
        <div className='col'>
          <input
            type='text'
            className='form-control'
            placeholder='Search by customer name or phone'
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </div>
        <div className='col'>
          <select
            className='form-control'
            value={sortOrder}
            onChange={(e) => setSortOrder(e.target.value)}
          >
            <option value=''>Chọn sort</option>
            <option value='asc'>Tổng tiền tăng dần</option>
            <option value='desc'>Tổng tiền giảm dần</option>
          </select>
        </div>
      </div>
      <p><span className='strong'>Tổng doanh thu:</span> {formatCurrency(totalPriceTk)}</p>
      <p><span className='strong'>Tổng sản phẩm:</span> {totalAmountTk}</p>
      <table className='table table-striped'>
        <thead>
          <tr className='text-center'>
            <td>Tên khách hàng</td>
            <td>Số điện thoại</td>
            <td>Tổng sản phẩm</td>
            <td>Tổng tiền</td>
            <td>Action</td>
          </tr>
        </thead>
        <tbody>
          {filteredInvoices.map((items, index) => (
            <tr key={index} className='text-center'>
              <td>{items.customer.name}</td>
              <td>{items.customer.phone}</td>
              <td>{items.invoiceProducts.reduce((sum, item) => sum + item.amount, 0)}</td>
              <td>{formatCurrency(items.invoiceProducts.reduce((sum, item) => sum + item.totalPrice, 0))}</td>
              <td>
                <button type='button' className='btn btn-outline-warning' onClick={() => handleView(items.invoiceProducts)}>Xem chi tiết</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

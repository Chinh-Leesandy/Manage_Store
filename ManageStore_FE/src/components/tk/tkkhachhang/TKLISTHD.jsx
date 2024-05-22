import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

export const TKLISTHD = () => {
  const location = useLocation();
  const invoices = location.state.invoices;
  const [filteredInvoices, setFilteredInvoices] = useState(invoices);
  const [filterMonth, setFilterMonth] = useState('');
  const [filterYear, setFilterYear] = useState('');

  useEffect(() => {
    applyDateFilter();
  }, [filterMonth, filterYear]);

  const totalAmountTk = filteredInvoices.reduce((total, items) => total + items.invoiceProducts.reduce((sum, item) => sum + item.amount, 0), 0);
  const totalPriceTk = filteredInvoices.reduce((total, items) => total + items.invoiceProducts.reduce((sum, item) => sum + item.totalPrice, 0), 0);

  const formatCurrency = (value) => {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
  };

  const formatDateTime = (dateTime) => {
    const date = new Date(dateTime);
    const formattedDate = date.toLocaleDateString('sv-SE');
    const formattedTime = date.toTimeString().split(' ')[0];
    return `${formattedDate} ${formattedTime}`;
  };

  const navigate = useNavigate();
  const handleView = (invoiceProducts) => {
    navigate('/hoadonct', { state: { invoiceProducts } });
  };

  const applyDateFilter = () => {
    let filtered = invoices;

    if (filterMonth) {
      filtered = filtered.filter(invoice => new Date(invoice.time).getMonth() + 1 === parseInt(filterMonth));
    }

    if (filterYear) {
      filtered = filtered.filter(invoice => new Date(invoice.time).getFullYear() === parseInt(filterYear));
    }

    setFilteredInvoices(filtered);
  };

  return (
    <div className='container'>
      <h2 className='text-center'>Danh Sách Hóa Đơn Theo Khách Hàng</h2>
      <div className='row mb-3 mt-3'>
        <div className='col'>
          <select
            className='form-control'
            value={filterMonth}
            onChange={(e) => setFilterMonth(e.target.value)}
          >
            <option value=''>Chọn tháng</option>
            {[...Array(12).keys()].map(i => (
              <option key={i + 1} value={i + 1}>{i + 1}</option>
            ))}
          </select>
        </div>
        <div className='col'>
          <input
            type='number'
            className='form-control'
            placeholder='Chọn năm'
            value={filterYear}
            onChange={(e) => setFilterYear(e.target.value)}
          />
        </div>
      </div>
      <p><span className='strong'>Tổng doanh thu:</span> {formatCurrency(totalPriceTk)}</p>
      <p><span className='strong'>Tổng sản phẩm:</span> {totalAmountTk}</p>
      <table className='table table-striped'>
        <thead>
          <tr className='text-center'>
            <td>Tên khách hàng</td>
            <td>Số điện thoại</td>
            <td>Thời gian</td>
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
              <td>{formatDateTime(items.time)}</td>
              <td>{items.invoiceProducts.reduce((sum, item) => sum + item.amount, 0)}</td>
              <td>{formatCurrency(items.invoiceProducts.reduce((sum, item) => sum + item.totalPrice, 0))}</td>
              <td>
                <button type="button" className="btn btn-outline-warning" onClick={() => handleView(items.invoiceProducts)}>Xem chi tiết</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

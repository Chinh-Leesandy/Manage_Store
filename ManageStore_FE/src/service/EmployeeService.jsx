import axios from 'axios';

const API_URL = 'http://localhost:8080'; // Địa chỉ URL của API Spring Boot

const EmployeeService = {
  getEmployees: async () => {
    try {
      const response = await axios.get(`${API_URL}/employee`);
      return response.data;
    } catch (error) {
      throw Error('Error while fetching employees');
    }
  },

  getEmployeeById: async (id) => {
    try {
      const response = await axios.get(`${API_URL}/employee/${id}`);
      return response.data;
    } catch (error) {
      throw Error('Error while fetching employee by ID');
    }
  },

  addEmployee: async (employee) => {
    try {
      const response = await axios.post(`${API_URL}/addEmployee`, employee);
      return response.data;
    } catch (error) {
      throw Error('Error while adding employee');
    }
  },

  updateEmployee: async (employee) => {
    try {
      const response = await axios.put(`${API_URL}/updateEmployee`, employee);
      return response.data;
    } catch (error) {
      throw Error('Error while updating employee');
    }
  },

  deleteEmployee: async (id) => {
    try {
      const response = await axios.delete(`${API_URL}/deleteEmploye/${id}`);
      return response.data;
    } catch (error) {
      throw Error('Error while deleting employee');
    }
  }
};

export default EmployeeService;

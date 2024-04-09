import axios from 'axios';

const API_URL = 'http://localhost:8080';


const NghiPhepService = {
  getNghiPhep: async () => {
    try {
      const response = await axios.get(`${API_URL}/nghiphep`);
      return response.data;
    } catch (error) {
      throw new Error(error.message);
    }
  },
    getTKNghiPhep: async (id) => {
        try {
          const response = await axios.get(`${API_URL}/tknghiphep/${id}`);
          return response.data;
        } catch (error) {
          throw new Error(error.message);
        }
      }
}

export default NghiPhepService